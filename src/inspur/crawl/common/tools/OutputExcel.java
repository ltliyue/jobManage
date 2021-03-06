package inspur.crawl.common.tools;

import inspur.crawl.requirement.pojo.RequireMent;
import inspur.crawl.requirement.pojo.RequireMentSplit;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.util.Region;

public class OutputExcel {

	/**
	 * 
	 * @param bm
	 *            名称
	 * @param celllist
	 *            数据
	 * 
	 * @param fialList
	 *            列名
	 * @param list4
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void excel(String bm, List<Map<String, Object>> celllist, List<String> fialList, List<String> list4, HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String fileName = bm + ".xls";
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = "表:" + bm;

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建列标头LIST

		// 计算该报表的列数
		int number = fialList.size() - 1;
		// ==================================================================
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		// cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		// 普通单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		// style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

		// ------------------------------------------------------------------
		// 另一个字体样式
		HSSFFont columnHeadFont = wb.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		// 工作表名
		String worksheet = "表" + bm;

		HSSFSheet sheet = wb.createSheet(worksheet);
		int hs = fialList.size();
		sheet.addMergedRegion(new Region(0, (short) (1), 0, (short) (hs - 1)));
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		ExcelExport exportExcel = new ExcelExport(wb, sheet);

		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, number);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = null;

		for (int i = 0; i < hs; i++) {
			cell1 = row1.createCell(i);
			cell1.setCellStyle(columnHeadStyle);
			cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));
		}
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		int kj = 0;
		for (Map<String, Object> map : celllist) {
			row = sheet.createRow(kj + 2);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(map.get("TASK_ID") + ""));
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(map.get("EXTRACT_TIME") + ""));
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(map.get("TASK_URL") + ""));
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(map.get("FETCH_TIME") + ""));
			int jj = 4;
			for (String s : list4) {
				cell = row.createCell(jj);
				cell.setCellValue(new HSSFRichTextString(map.get(s.toUpperCase()) + ""));
				cell.setCellStyle(style);
				jj++;
			}

			kj++;
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			list.clear();
		}
	}

	/**
	 * 
	 * @param bm
	 *            名称
	 * @param celllist
	 *            数据
	 * 
	 * @param fialList
	 *            列名
	 * @param list4
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void excel_requirment(String bm, List<RequireMent> celllist, List<String> fialList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String fileName = bm + ".xls";
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = bm + "表";

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建列标头LIST

		// 计算该报表的列数
		int number = fialList.size() - 1;
		// ==================================================================
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		// cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		// 普通单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		// style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

		// ------------------------------------------------------------------
		// 另一个字体样式
		HSSFFont columnHeadFont = wb.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		// 工作表名
		String worksheet = "表" + bm;

		HSSFSheet sheet = wb.createSheet(worksheet);
		int hs = fialList.size();
		sheet.addMergedRegion(new Region(0, (short) (1), 0, (short) (hs - 1)));
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		ExcelExport exportExcel = new ExcelExport(wb, sheet);

		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, number);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = null;

		for (int i = 0; i < hs; i++) {
			cell1 = row1.createCell(i);
			cell1.setCellStyle(columnHeadStyle);
			cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));
		}
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		int kj = 0;
		for (RequireMent requireMent : celllist) {
			row = sheet.createRow(kj + 2);

			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(kj + 1 + ""));

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProperties()));

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getType()));

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getName()));

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getContent()));

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProposeUser()));

			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProposeTime()));

			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getRequestTime()));

			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getStatus()));

			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getReason()));

			kj++;
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			list.clear();
		}
	}

	/**
	 * 
	 * @param bm
	 *            名称
	 * @param celllist
	 *            数据
	 * 
	 * @param fialList
	 *            列名
	 * @param list4
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void excel_requirmentSplit(String bm, List<RequireMentSplit> celllist, List<String> fialList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String fileName = bm + ".xls";
		fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		// 定义单元格报头
		String worksheetTitle = bm + "表";

		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建列标头LIST

		// 计算该报表的列数
		int number = fialList.size() - 1;
		// ==================================================================
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		// cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		// 普通单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		// style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		style.setWrapText(true);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

		// ------------------------------------------------------------------
		// 另一个字体样式
		HSSFFont columnHeadFont = wb.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 列头的样式
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		// 工作表名
		String worksheet = "表" + bm;

		HSSFSheet sheet = wb.createSheet(worksheet);
		int hs = fialList.size();
		sheet.addMergedRegion(new Region(0, (short) (1), 0, (short) (hs - 1)));
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		ExcelExport exportExcel = new ExcelExport(wb, sheet);

		// 创建报表头部
		exportExcel.createNormalHead(worksheetTitle, number);
		// 定义第一行
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell1 = null;

		for (int i = 0; i < hs; i++) {
			cell1 = row1.createCell(i);
			cell1.setCellStyle(columnHeadStyle);
			cell1.setCellValue(new HSSFRichTextString(fialList.get(i).toString()));
		}
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(1);
		int kj = 0;
		for (RequireMentSplit requireMent : celllist) {
			row = sheet.createRow(kj + 2);

			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(kj + 1 + ""));

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getName()));

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getContent()));

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProperties()));

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getIsalive()));
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getJobFrequency()));
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getJobDegree()));

			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getType()));

			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getCustomer()));

			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProposeUser()));

			cell = row.createCell(10);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getProposeTime()));

			cell = row.createCell(11);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getRequestTime()));
			
			cell = row.createCell(12);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getHeader()));
			
			cell = row.createCell(13);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getStatus()));

			cell = row.createCell(14);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getPlanTime()));

			cell = row.createCell(15);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getJobRange()));

			cell = row.createCell(16);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getIsmatch()));
			
			cell = row.createCell(17);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getIscollection()));
			
			cell = row.createCell(18);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(requireMent.getIsreport()));

			if (requireMent.getPlantform()==null||requireMent.getPlantform().equals("") || requireMent.getPlantform().equals("1")) {
				cell = row.createCell(19);
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString("任务平台"));
			} else {
				cell = row.createCell(19);
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString("采集平台"));
			}

			kj++;
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		} finally {
			list.clear();
		}
	}
}
