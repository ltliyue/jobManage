var repeatShopID,vInterval,repeatGoodsID;
var i=0;
var j=0;

// 按钮控制 ////////////////////////////////////////////////////////

start();

$('#pause_btn').click(function(){
	$('#goto_btn').show();
	$(this).hide();
	clearInterval(repeatShopID);
	clearInterval(vInterval);
	clearInterval(repeatGoodsID);
});
$('#goto_btn').click(function(){
	$('#pause_btn').show();
	$(this).hide();
	start();
});
$('#start_btn').click(function(){
	$('#pause_btn').show();
	$('#progress').anim_progressbar();
});
function start(){
	$('#start_btn').hide();
	repeatShopID = setInterval(jQueryAddShopData,1000);
	vInterval = setInterval(progressbarFN,100);
	repeatGoodsID = setInterval(jQueryAddGoodsData,200);
};


// 网店动态 ////////////////////////////////////////////////////////
function jQueryAddShopData(){
	dataListNum = 7;	// 页面上可显示的总条目
	var a=new Array(
new Array('义乌市豆挞电子商务商行','14155.7','鞋包配饰','浙江义乌','http://ywdota.1688.com','2015.1'),
new Array('义乌市美可电子商务有限公司','24401.9','鞋包配饰','浙江义乌','http://shop1394124999239.1688.com','2015.1'),
new Array('义乌市浩子电子商务商行','5279.5','鞋包配饰','浙江义乌','http://ywhzsh.1688.com','2015.1'),
new Array('义乌市双宏饰品有限公司','47765','鞋包配饰','浙江义乌','http://ywshsp01.1688.com','2015.1'),
new Array('义乌市葵黛电子商务商行','13964.1','鞋包配饰','浙江义乌','http://shop1403715054088.1688.com','2015.1'),
new Array('广州市白云区三元里韩之丽化妆品','126490.32','鞋包配饰','广东广州','http://shop1351790058658.1688.com','2015.1'),
new Array('义乌市哈萌电子商务商行','3605.5','鞋包配饰','浙江义乌','http://shop1413543130569.1688.com','2015.1'),
new Array('义乌市雅漫电子商务商行','23796.8','电子电工','浙江义乌','http://yazhiman730.1688.com','2015.1'),
new Array('义乌盈源对联批发','47635','电子电工','浙江义乌','http://hypfw.1688.com','2015.1'),
new Array('义乌市杰禄电子商务商行','32759.5','化工精细','浙江义乌','http://18989401270.1688.com','2015.1'),
new Array('义乌市创造电子商务商行','4669.2','机械五金','浙江义乌','http://shop1397753775919.1688.com','2015.1'),
new Array('义乌市学广电子商务商行','33914.58','化工精细','浙江义乌','http://ywxgtoys.1688.com','2015.1'),
new Array('义乌市瑟索电子商务商行','5362','食品农业','浙江义乌','http://redsunswall.1688.com','2015.1'),
new Array('义乌市彪法饰品厂','5815','照明仪表安防','浙江义乌','http://ywbiaofasp.1688.com','2015.1'),
new Array('义乌市荣华抛光材料商行','230','数码家电','浙江义乌','http://ywronghua.1688.com','2015.1'),
new Array('东阳市奥得固胶业有限公司','9642','数码家电','浙江义乌','http://hd15958946255.1688.com','2015.1'),
new Array('深圳市百宝源电子商务有限公司','34406.35','数码家电','广东深圳','http://baibaoyuan2688.1688.com','2015.1'),
new Array('永康市东城蒙特里安家居用品厂','23600','数码家电','浙江义乌','http://ykmtla.1688.com','2015.1'),
new Array('义乌市佰诺美发用品商行','23898','数码家电','浙江义乌','http://shop1405011246031.1688.com','2015.1'),
new Array('王广明（个体经营）','11994.4','冶金钢材','浙江义乌','http://mjkryp.1688.com','2015.1'),
new Array('永康市晨诺休闲用品制造厂','10320','冶金钢材','浙江永康','http://chennuoxiuxian.1688.com','2015.1'),
new Array('永康市永阳工贸有限公司','480','美妆日化','浙江永康','http://wangzhounao.1688.com','2015.1'),
new Array('义乌市齐冠电子商务商行','6140.4','美妆日化','浙江义乌','http://shop1400605465863.1688.com','2015.1'),
new Array('永康市盛诺杯业有限公司','7600','美妆日化','浙江永康','http://shengnuobeiye.1688.com','2015.1'),
new Array('永康市鑫烁异型铆钉厂','28111.07','美妆日化','浙江永康','http://xinshuomd.1688.com','2015.1'),
new Array('黄社清','30','食品农业','浙江永康','http://guhenglock.1688.com','2015.1'),
new Array('浙江科华金属制品有限公司','1800','鞋包配饰','浙江永康','http://zjkehua.1688.com','2015.1'),
new Array('义乌市绿意工艺品有限公司','11097.1','冶金钢材','浙江义乌','http://ecolife888.1688.com','2015.1'),
new Array('西瓜西美衣批发','125.8','食品农业','浙江义乌','http://shop1404451954214.1688.com','2015.1'),
new Array('义乌市跃峰鞋行','9520','纺织','浙江义乌','http://wangliuying168.1688.com','2015.1'),
new Array('松阳白马家居生活馆','2872','纺织','浙江义乌','http://shop1404838817530.1688.com','2015.1'),
new Array('义乌市杨丹电子商务商行','336','家纺家装','浙江义乌','http://YWgardencenter.1688.com','2015.1'),
new Array('义乌市俏_电子商务商行','8148.8','家纺家装','浙江义乌','http://qiaolafushi.1688.com','2015.1'),
new Array('义乌市源端电子商务商行','20735.2','家纺家装','浙江义乌','http://shop1398186097335.1688.com','2015.1'),
new Array('义乌市嘉拓服饰厂','9396.8','家纺家装','浙江义乌','http://jt4000327758.1688.com','2015.1'),
new Array('金华市婺城区风铃草服饰商行','325','机械五金','浙江金华','http://shop1390409627345.1688.com','2015.1'),
new Array('义乌市古古电子商务商行','26776.9','美妆日化','浙江义乌','http://shop1357407723115.1688.com','2015.1'),
new Array('义乌市莱尚电子商务商行','16028.5','日用百货','浙江义乌','http://15ls.1688.com','2015.1'),
new Array('楼俊杰(个体经营)','176391.6','化工精细','浙江金华','http://yiwuloujunjie.1688.com','2015.1'),
new Array('金华市金东区惠香饰品加工厂','6760.8','美妆日化','浙江金华','http://huixiangjingpin.1688.com','2015.1'),
new Array('永康市唐先镇宇乐五金塑料制品厂','0','美妆日化','浙江永康','http://zjykhengji.1688.com','2015.1'),
new Array('余姚市子康光学仪器厂','30391.9','橡胶塑料','浙江余姚','http://skytrax888.1688.com','2015.1'),
new Array('金华市舒达文具印业有限公司','27622','化工精细','浙江金华','http://jhsdwjyy.1688.com','2015.1'),
new Array('义乌市你我他饰品厂','17649','童装母婴','浙江义乌','http://nwtfactory.1688.com','2015.1'),
new Array('金华市婺城区英之家日用百货商行','7515.28','童装母婴','浙江金华','http://zyh19088.1688.com','2015.1'),
new Array('金华市鼎丰工具制造厂','3610.5','童装母婴','浙江义乌','http://dingfenglb.1688.com','2015.1'),
new Array('义乌市北群电子商务商行','15584','童装母婴','浙江义乌','http://shop1395075460331.1688.com','2015.1'),
new Array('义乌市志茂电子商务商行','11165.74','童装母婴','浙江义乌','http://shop1384880030747.1688.com','2015.1'),
new Array('义乌麒敏饰品有限公司','7129.4','童装母婴','浙江义乌','http://ywqc158.1688.com','2015.1'),
new Array('武义县鹏飞电器有限公司','1903.6','童装母婴','浙江武义','http://kanglongcup.1688.com','2015.1'),
new Array('义乌市蝶芙电子商务商行','9483.5','童装母婴','浙江义乌','http://520diefy.1688.com','2015.1'),
new Array('义乌市点俏电子商务商行','20220','童装母婴','浙江义乌','http://yiwudianqiao.1688.com','2015.1'),
new Array('义乌市美调电子商务商行','1712','童装母婴','浙江义乌','http://shop1407808868423.1688.com','2015.1'),
new Array('义乌市简蝶饰品厂','5535.06','童装母婴','浙江义乌','http://jinyesp.1688.com','2015.1'),
new Array('义乌市健阮电子商务商行','14778.24','童装母婴','浙江义乌','http://ywrongchen.1688.com','2015.1'),
new Array('义乌市威莱日用品有限公司','61705','童装母婴','浙江义乌','http://fendou.1688.com','2015.1'),
new Array('湖州织里霖洲童装经营部','154','童装母婴','浙江湖州','http://linzhoutz.1688.com','2015.1'),
new Array('湖州织里刘天兵童装商行','1090','童装母婴','浙江湖州','http://shop1403283404688.1688.com','2015.1'),
new Array('湖州织里巴奴卡伦童装商行','32416.3','童装母婴','浙江湖州','http://bluecory.1688.com','2015.1'),
new Array('湖州织里韩品尚童装商行','20637.5','童装母婴','浙江湖州','http://6588888.1688.com','2015.1'),
new Array('湖州织里悦琳服饰有限公司','27505','童装母婴','浙江湖州','http://yltzkids.1688.com','2015.1'),
new Array('庐江县乐可迪龙商贸有限公司','1038.6','童装母婴','浙江湖州','http://lekedl.1688.com','2015.1'),
new Array('湖州织里拍拍童装商行','3488','童装母婴','浙江湖州','http://pptzshanghang.1688.com','2015.1'),
new Array('吴兴任诗滑家纺针织服装厂','9612','鞋包配饰','浙江湖州','http://shop1411749603024.1688.com','2015.1'),
new Array('王建芬(个体经营)','1196','纺织','浙江湖州','http://babc0307.1688.com','2015.1'),
new Array('湖州织里酷蛋壳服饰有限公司','44483','纺织','浙江湖州','http://kudanke.1688.com','2015.1'),
new Array('义乌市快鸟服装厂','18579.2','纺织','浙江义乌','http://ywwmfz.1688.com','2015.1'),
new Array('永康市多得电器厂','74115','纺织','浙江义乌','http://shop1409157746434.1688.com','2015.1'));
	addDom = $('#shopTable').find('tbody').find('tr:first');
	$tip = $(['<tr style="color:red;">',
			  '<td>' + a[i][0] + '</td>',
			  '<td>' + a[i][2] + '</td>',
			  '<td>' + a[i][3] + '</td>',
			  '<td>' + a[i][4] + '</td>',
			  //'<td>' + a[i][5] + '</td>',
			  //'<td>' + a[i][5] + '</td>',
			  '</tr>'].join(''));
	$('#shopTable').find('tbody').find('tr').css('color','#000');
	addDom.before($tip);
	listNum = $('#shopTable').find('tbody').find('tr').length;
	if(listNum > dataListNum){
		$('#shopTable').find('tbody').find('tr:gt('+ (dataListNum-1) + ')').remove();
	}
	if(++i==a.length){
	  i=0;
	}
};

function jQueryAddGoodsData(){
	dataListNum = 2;	// 页面上可显示的总条目
	var b=new Array(
new Array('现货批发2014新款 皮皮淘 帆布爱心高背圆窝 绿/橘色 大小号','http://detail.1688.com/offer/40919844230.html','35','3','2015/2/23 10:29'),
new Array('得乐不锈钢狗毛刷 圆头自洁钢针刷 狗狗洁毛宠物梳子M006','http://detail.1688.com/offer/39436509122.html','15','5','2015/2/5 20:05'),
new Array('2014新款现货批发 德丰紫心套装四腿宠物棉衣 狗狗衣服混批','http://detail.1688.com/offer/39426178242.html','5.2','40','2015/2/1 21:08'),
new Array('第二代软胶发光LED宠物吊坠 闪光圆形狗牌子 宠物用品批发','http://detail.1688.com/offer/39418355024.html','4','10','2015/2/20 10:41'),
new Array('现货批发2014新款 皮皮淘四色条纹宠物窝三件套狗窝','http://detail.1688.com/offer/40701901835.html','69','1','2015/2/20 20:05'),
new Array('皮皮淘现货批发 0.8cm宽 万达真牛皮铜钩牵引绳 1.5/2.0m两种规格','http://detail.1688.com/offer/40626456080.html','22','2','2015/2/4 11:28'),
new Array('现货批发2014新款 皮皮淘大圆点包边宠物窝三件套 褐色/紫色','http://detail.1688.com/offer/40615011534.html','73','2','2015/2/17 15:13'),
new Array('树脂点点式宠物厕所 围栏母狗厕所 宠物用品批发 特价','http://detail.1688.com/offer/39435681722.html','19','2','2015/2/4 11:28'),
new Array('现货批发皮皮淘 新款糖果色棉绳编织球 小号 6cm','http://detail.1688.com/offer/40919580726.html','1.8','2','2015/2/11 14:08'),
new Array('现货批发2014新款宠物秋冬装 小熊四腿绒衣 红/蓝色','http://detail.1688.com/offer/40867067114.html','15','4','2015/2/13 19:33'),
new Array('新款 现货批发 英国米字旗配腰带四脚宠物衣服 宠物冬装批发','http://detail.1688.com/offer/40340489422.html','23.5','5','2015/2/14 13:18'),
new Array('尾货清仓 狗狗衣服 泰迪超人网眼清凉宠物背心 混色不选号','http://detail.1688.com/offer/40325571106.html','5','10','2015/2/25 9:00'),
new Array('新款 德丰宠物衣服 加厚雪鹿绒棉衣宠物衣服 四脚狗狗衣服 秋冬','http://detail.1688.com/offer/40322435091.html','26','2','2015/2/16 12:26'),
new Array('义乌现货批发 宠物用品 新宠之康 猫用除蚤圈 1.5*29cm','http://detail.1688.com/offer/40162027666.html','3.5','5','2015/2/15 12:30'),
new Array('批发供应不锈钢骨头型宠物牌 骨头狗牌 可混批-小号3*2CM','http://detail.1688.com/offer/39454828661.html','1','30','2015/2/7 15:39'),
new Array('皮皮淘2.5寸奶味小结骨 4支装 真奶味宠物打结骨 宠物零食奶味','http://detail.1688.com/offer/39452333149.html','3.2','5','2015/2/4 15:43'),
new Array('现货批发皮皮淘宠物细纹圆绳 1.2*120CM 配2.0胸背套','http://detail.1688.com/offer/39442552582.html','6.5','3','2015/2/17 14:24'),
new Array('现货批发宠物用品 搪胶发声万圣节南瓜 9*6cm','http://detail.1688.com/offer/39583067383.html','2.5','4','2015/2/23 10:29'),
new Array('2014新款秋冬装 宠物伴郎装 铁锈黄/蓝色 4款大小','http://detail.1688.com/offer/39442056700.html','17.5','2','2015/2/28 23:50'),
new Array('厂家直销义乌糖果色化妆包 水饺包 赠品化妆包 礼品包8004','http://detail.1688.com/offer/1263335235.html','2.9','38','2015/2/28 14:56'),
new Array('靓丽 完美手提 韩版绣格行缝格子 化妆包 化妆箱 收纳包8016','http://detail.1688.com/offer/1073480069.html','5.8','5','2015/2/20 13:01'),
new Array('专业生产绣格棱形糖果色双层加厚韩版化妆包批发洗漱包（小号）','http://detail.1688.com/offer/1284611677.html','4.2','8','2015/2/2 22:43'),
new Array('糖果色休闲款帆布饺子包 男女通用单肩包斜挎包学生包 时尚布包','http://detail.1688.com/offer/1250742279.html','11','3','2015/2/23 18:17'),
new Array('20色高级防水手拎手提包帆布包尼龙包小包便当包妈咪包女包饭盒包','http://detail.1688.com/offer/37007240871.html','6.5','10','2015/2/2 22:43'),
new Array('优质推荐大量色丁料便当式手提化妆包 时尚百搭收纳化妆包8018','http://detail.1688.com/offer/1073441053.html','4','200','2015/2/9 16:47'),
new Array('厂家直销 A58信纸批发 条式信笺 文体超市办公用品批发 草稿纸','http://detail.1688.com/offer/40592108653.html','0.98','10','2015/2/22 14:39'),
new Array('明星精品 EXO 集体 16K 全彩页 笔记本 练习本','http://detail.1688.com/offer/41541822573.html','2.5','5','2015/2/23 13:30'),
new Array('明星 TFBOYS 王俊凯 王源 易烊千玺 纸质书签 18包一套','http://detail.1688.com/offer/41491680327.html','18','1','2015/2/2 13:39'),
new Array('优质推荐大量色丁料便当式手提化妆包 时尚百搭收纳化妆包8018','http://detail.1688.com/offer/1073441053.html','4.2','1','2015/2/9 10:10'),
new Array('一件代发厂家批发旅行多功能 内衣收纳包 文胸整理包 便携洗漱包','http://detail.1688.com/offer/1257711936.html','9','1','2015/2/11 11:22'),
new Array('厂家直销 批发混批 纳时光 韩版 可折叠单肩女包 旅行购物手提包','http://detail.1688.com/offer/36737835770.html','9.5','2','2015/2/8 16:48'),
new Array('韩版高级防水手拎手提包帆布包尼龙包小包便当包妈咪包女包饭盒包','http://detail.1688.com/offer/37987842603.html','7.9','1','2015/2/5 7:01'),
new Array('网店赠品韩版手提女士化妆包大号收纳包洗漱包礼品包大量现货','http://detail.1688.com/offer/1073453384.html','4.2','2','2015/2/9 10:10'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳蓝色玫瑰4s水钻保护壳套','http://detail.1688.com/offer/1192615989.html','10','3','2015/2/10 9:22'),
new Array('iphone6plus手机贴膜 高清膜 手机膜 手机保护膜 一件代发 批发','http://detail.1688.com/offer/41322898301.html','1.4','2','2015/2/17 22:38'),
new Array('苹果镶钻素材壳 iphone5S手机壳 透明超薄壳苹果5手机壳 批发','http://detail.1688.com/offer/1192284048.html','0.5','1000','2015/2/20 12:16'),
new Array('新款手机壳镶水钻壳苹果iphone4S/5S潮彩钻多色保护套 一件代发','http://detail.1688.com/offer/36357653304.html','8.7','2','2015/2/16 9:22'),
new Array('正品阿波罗克Q7高保真立体声音乐耳机 apolok入耳式带麦彩色耳塞','http://detail.1688.com/offer/1199942968.html','24.5','1','2015/2/3 8:50'),
new Array('现货10*18白色珠光膜阴阳骨袋 手机外壳袋 一件代发 厂家批发','http://detail.1688.com/offer/1216841609.html','0.3','100','2015/2/24 17:12'),
new Array('现货iPhone5S iPhone4S苹果手机壳 铁塔花镶钻保护套 一件代发','http://detail.1688.com/offer/35631975059.html','25.2','1','2015/2/26 16:44'),
new Array('三星N7100手机皮套 Note2水钻手机壳 奢华翻盖镶钻保护套一件代发','http://detail.1688.com/offer/1216493722.html','36.4','1','2015/2/28 14:09'),
new Array('小米4镶钻手机壳 小米4手机保护套 樱花流苏手机壳 批发一件代发','http://detail.1688.com/offer/41021874899.html','10.5','1','2015/2/19 18:05'),
new Array('批发宠物用品经济型粉色桃心狗窝 拖鞋形狗窝 可以混批','http://detail.1688.com/offer/39415727848.html','30','2','2015/2/15 11:43'),
new Array('椭圆形橡胶宠物沐浴按摩刷 宠物刷洗澡用品混批','http://detail.1688.com/offer/39408311643.html','1.8','2','2015/2/27 20:10'),
new Array('11月新款 小号D字圆形柄针梳 出口级橡胶软柄宠物梳子 精装含配梳','http://detail.1688.com/offer/42042893807.html','5.4','3','2015/2/26 15:51'),
new Array('现货批发2014新款 皮皮淘 法兰绒宠物怪兽装 红/蓝/紫色','http://detail.1688.com/offer/40904165845.html','11.5','3','2015/2/23 12:23'),
new Array('新款皮皮淘 PANDA两腿大熊猫宠物棉衣 宠物冬装现货批发','http://detail.1688.com/offer/40884450145.html','23','2','2015/2/27 15:34'),
new Array('【新品快订】新款皮皮淘加厚保暖圆形蕾丝边公主窝狗窝 两件套','http://detail.1688.com/offer/40683689802.html','58.8','1','2015/2/26 21:02'),
new Array('皮皮淘现货批发 加强天然免疫力 派得小型犬幼犬粮 500g','http://detail.1688.com/offer/40664540355.html','8.1','7','2015/2/10 22:02'),
new Array('皮皮淘现货批发宠物用品 梦幻七彩泡影条纹宠物衣服 速卖通热销','http://detail.1688.com/offer/40626460038.html','20','2','2015/2/27 15:34'),
new Array('现货批发 万达真牛皮德牧牵引绳 1.5/2.0/2.5/3.0米长 4种规格','http://detail.1688.com/offer/40573603909.html','27','1','2015/2/9 14:12'),
new Array('现货批发宠物用品 蒙贝水晶牛肉 70g/罐','http://detail.1688.com/offer/39966756041.html','12','1','2015/2/9 15:38'),
new Array('现货供应宠物零食 宠物除口臭 饼幻彩骨头多味高钙除臭饼干500g','http://detail.1688.com/offer/39423443707.html','9','3','2015/2/10 22:02'),
new Array('浙江地区代理批发哈利贝贝 胃宝综合营养粉 228g 宠物用品','http://detail.1688.com/offer/39386449142.html',	'20','30','2015/2/11 15:20'),
new Array('新款黑骨头尼龙小圆点宠物手提包 50*18*28cm','http://detail.1688.com/offer/40884334686.html','38','3','2015/2/6 18:11'),
new Array('现货批发LED发光狗牌 ABS塑胶骨头型闪光吊牌 50*30CM 可混批','http://detail.1688.com/offer/39415987754.html','4.5','5','2015/2/4 15:43'),
new Array('LED宠物项圈 晶格防水热销款 狗项圈 六色随选可混批 宠物用品','http://detail.1688.com/offer/39403903695.html','5','30','2015/2/2 11:25'),
new Array('新款 皮皮淘小奶牛造型宠物屋子 36*34*25cm 现货批发','http://detail.1688.com/offer/41227522855.html','42','1','2015/2/5 13:52'),
new Array('2014新款 小圆点三件套狗窝 紫/褐/藏青色宠物窝 宠物用品批发','http://detail.1688.com/offer/40702845145.html','69','1','2015/2/15 12:07'),
new Array('2014新款 现货批发 皮皮淘 毛绒加厚条纹背带四腿衣 宠物衣服','http://detail.1688.com/offer/40369807587.html','26','1','2015/2/8 14:12'),
new Array('外贸现货批发 超可爱米奇宠物变身装 狗狗衣服混批 5个尺寸','http://detail.1688.com/offer/40325383587.html','11.5','6','2015/2/28 8:57'),
new Array('韩国竹纤维不沾油洗碗巾/不掉毛洗碗布/抹布/百洁布/不沾油抹布','http://detail.1688.com/offer/35442178258.html','0.52','3000','2015/2/10 11:07'),
new Array('柔软超吸水双面不掉毛擦桌抹布 厨房超细纤维清洁布居家必备','http://detail.1688.com/offer/35508218631.html','1.5','750','2015/2/21 15:15'),
new Array('韩国摩克丽神奇干发帽 干发帽 7倍超强吸水 干发毛巾','http://detail.1688.com/offer/35442342761.html','1.37','1500','2015/2/25 18:44'),
new Array('创意家居 可爱卡通咪兔超强吸水干发帽 神奇干发巾','http://detail.1688.com/offer/35443778068.html','2.5','300','2015/2/22 14:06'),
new Array('拿样/补差价/补运费/付定金/付余款/付钱/其他付费','http://detail.1688.com/offer/37489615574.html','1','4','2015/2/13 19:10'),
new Array('14新款 PVC透明绣花女士水晶化妆包小花朵绣手拿包收纳包大号','http://detail.1688.com/offer/38698465706.html','7','6','2015/2/8 20:13'),
new Array('厂家直销 韩国可爱 安古奈子 双拉链零钱包 批发定制','http://detail.1688.com/offer/1298486861.html','4.8','1','2015/2/13 18:13'),
new Array('韩版时尚 糖果色尼龙网格防水化妆 收纳包/洗漱包旅行8007','http://detail.1688.com/offer/36679373723.html','7.5','1','2015/2/21 15:10'),
new Array('代发 可移除卡通儿童房墙贴纸瓷砖贴玻璃贴海底世界墙贴纸 AM7004','http://detail.1688.com/offer/39671170202.html','3.8','4','2015/2/3 17:01'),
new Array('冰雪奇缘文具冰雪公主冰雪皇后大冒险卡通儿童女孩笔袋现货供应','http://detail.1688.com/offer/40591775181.html','1.8','75','2015/2/17 11:21'),
new Array('新款FROZEN冰雪奇缘雪宝公仔冰雪大冒险卡通双层笔袋学习文具','http://detail.1688.com/offer/40765119232.html','6.5','2','2015/2/28 11:06'),
new Array('苹果镶钻素材壳 iphone4S手机壳 透明超薄壳苹果4手机壳 批发','http://detail.1688.com/offer/40122093334.html','0.7','2','2015/2/5 9:43'),
new Array('苹果iphone5S 4S镶钻手机皮套 爪链翻盖水钻手机保护套 一件代发','http://detail.1688.com/offer/35648880006.html','40.6','1','2015/2/4 11:49'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳珍珠蝴蝶结4s水钻保护壳套','http://detail.1688.com/offer/41405030064.html','10','1','2015/2/17 17:27'),
new Array('三星S5镶钻手机壳 三星i9600手机保护套 对角珍珠贴钻外壳 批发','http://detail.1688.com/offer/38408377601.html','7.5','1','2015/2/17 20:16'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳恋恋红唇4s水钻保护壳套','http://detail.1688.com/offer/1196006420.html','19.5','1','2015/2/15 22:30'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳对角五叶花4s水钻保护壳套','http://detail.1688.com/offer/41429977540.html','6.5','5','2015/2/20 19:42'),
new Array('iphone6手机贴膜 高清膜 手机膜 手机保护膜 一件代发 批发','http://detail.1688.com/offer/41305447868.html','1.4','2','2015/2/28 14:05'),
new Array('三星S5手机保护套 三星i9600手机壳 飘带水钻镶钻贴钻手机外壳','http://detail.1688.com/offer/1191409517.html','9','1','2015/2/9 16:28'),
new Array('三星N7100手机皮套 Note 2手机壳 珍珠花翻盖镶钻保护套 一件代发','http://detail.1688.com/offer/35632396575.html','30.1','1','2015/2/11 7:45'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳蓝色玫瑰4s水钻保护壳套','http://detail.1688.com/offer/1192615989.html','10.5','2','2015/2/24 20:37'),
new Array('外贸现货批发 超可爱米奇宠物变身装 狗狗衣服混批 5个尺寸','http://detail.1688.com/offer/40325383587.html','11.5','2','2015/2/20 19:21'),
new Array('现货批发宠物用品 蒙贝精品小龙骨 6块装/桶 高级桶装零食','http://detail.1688.com/offer/39965468983.html','12','3','2015/2/28 15:22'),
new Array('现货宠物围兜口水巾 扮靓围巾 潮流时尚 三角纯棉 多款图案','http://detail.1688.com/offer/39454788724.html','2.5','5','2015/2/16 12:26'),
new Array('现货批发锦纶单色脚印宠物胸背带 狗用牵引带 长120CM','http://detail.1688.com/offer/39432441285.html','5','1','2015/2/27 0:33'),
new Array('现货批发皮皮淘 新款糖果色棉绳编织球 小号 6cm','http://detail.1688.com/offer/40919580726.html','1.8','3','2015/2/13 21:50'),
new Array('新款宠物衣服秋冬装 皮皮淘荧光大嘴猴狗狗四脚衣服 色彩鲜艳新品','http://detail.1688.com/offer/40341577089.html','20.5','4','2015/2/14 12:33'),
new Array('宠物冬季衣服 新年富贵唐装 织锦缎贵族唐装宠物服装 狗衣服混批','http://detail.1688.com/offer/40340698454.html','16','2','2015/2/9 15:38'),
new Array('现货批发 宠物零食 蒙贝牛肉卷 100g/袋','http://detail.1688.com/offer/39966776038.html','6','3','2015/2/4 15:43'),
new Array('热销宠物零食 哈格黄金奶酪包 多种口味混 200g/袋','http://detail.1688.com/offer/39591978149.html','8','5','2015/2/4 15:43'),
new Array('批发皮皮淘A-TENTH宠物格子牛仔背带裤 狗衣服春夏装 文艺范','http://detail.1688.com/offer/39454788696.html','19','6','2015/2/9 13:44'),
new Array('厂家直销黑色宠物发声漏食球 怪叫怪声漏食球 中号 10CM','http://detail.1688.com/offer/39443561358.html','7','10','2015/2/15 12:07'),
new Array('宠物狗粮 诺瑞好之味狗粮牛肉味成犬粮泰迪金毛全犬种狗粮500g','http://detail.1688.com/offer/39432102620.html','4.8','6','2015/2/22 0:42'),
new Array('供应皮皮淘圆形骨头脚印狗盆混装宠物牌 圆形狗牌 混色随机发','http://detail.1688.com/offer/39425514427.html','1','20','2015/2/7 15:39'),
new Array('色彩鲜艳 粉紫色宠物塑料梳子 /带保护头密梳 宠物用品批发','http://detail.1688.com/offer/39416378825.html','3','20','2015/2/18 16:29'),
new Array('现货宠物狗狗自动喂食器 储粮桶食盘升降碰珠饮水器 可拆洗','http://detail.1688.com/offer/39408351692.html','55','2','2015/2/27 20:10'),
new Array('现货批发2014新款 皮皮淘半边七彩圆点宠物房子 红/蓝/紫三色可选','http://detail.1688.com/offer/40905377778.html','39','1','2015/2/23 10:29'),
new Array('新款宠物衣服 条纹零钱包套装狗衣服 四脚冬装 现货批发','http://detail.1688.com/offer/40532679720.html','19','4','2015/2/17 13:47'),
new Array('2014新款狗狗衣服秋冬装 超酷蝙蝠侠 四脚变身装宠物衣服','http://detail.1688.com/offer/40354677083.html','32','2','2015/2/21 17:02'),
new Array('宠物恶魔变身装 宠物衣服狗衣服 贵宾秋冬装批发','http://detail.1688.com/offer/39454128889.html','11','3','2015/2/21 17:02'),
new Array('批发正品南京金盾宠物诺信片 芬苯哒唑片 宠物驱虫 10粒/盒','http://detail.1688.com/offer/39451085527.html','11','2','2015/2/7 19:16'),
new Array('批发供应比瑞吉小型犬成犬粮10kg 北欧天然狗粮 泰迪贵宾犬主粮','http://detail.1688.com/offer/39432126588.html','270','3','2015/2/12 21:25'),
new Array('小额混批 优质宠物大包装鸡肉绕钙奶骨狗 800g','http://detail.1688.com/offer/39432078568.html','21','1','2015/2/13 21:50'),
new Array('2014宠物圣诞礼物 毛绒发声圣诞老人 宠物五角圣诞老人玩具','http://detail.1688.com/offer/39414787518.html','8','3','2015/2/19 8:08'),
new Array('现货批发皮皮淘优质塑胶水转印自动伸缩牵引器 三色混批 5M','http://detail.1688.com/offer/39413006358.html','20','5','2015/2/2 11:25'),
new Array('现货苹果5s镶钻手机壳iphone6plus外壳飞天女孩4s水钻保护壳套','http://detail.1688.com/offer/41299430923.html','13','1','2015/2/14 22:43'),
new Array('小米4镶钻手机壳 小米4手机保护套 五叶花手机壳 批发 一件代发','http://detail.1688.com/offer/41045625660.html','6','1','2015/2/19 11:36'),
new Array('镶钻车载汽车饰品 汽车摆件 摩丝摇头娃娃高档汽车饰品 工厂批发','http://detail.1688.com/offer/40253667444.html','17','2','2015/2/27 2:51'),
new Array('iphone6手机贴膜 高清膜 手机膜 手机保护膜 一件代发 批发','http://detail.1688.com/offer/41305447868.html','1.4','1','2015/2/23 15:06'));
	addDom = $('#dbTable').find('tbody').find('tr:first');
	$tip = $(['<tr>',
			  '<td>' + b[j][0] + '</td>',
			  '<td>' + b[j][1] + '</td>',
			  '<td>' + b[j][2] + '</td>',
			  '<td>' + b[j][3] + '</td>',
			  '<td>' + current() + '</td>',
			  '</tr>'].join(''));
	addDom.before($tip);
	listNum = $('#dbTable').find('tbody').find('tr').length;
	if(listNum > dataListNum){
		$('#dbTable').find('tbody').find('tr:gt('+ (dataListNum-1) + ')').remove();
	}
	if(++j==b.length){
	  j=0;
	}
};

// 进度条 ////////////////////////////////////////////////////////
function progressbarFN(){
	iLeftMs = aOpts.finish - new Date(); // left time in MS
	iElapsedMs = new Date() - aOpts.start, // elapsed time in MS
		iDays = parseInt(iLeftMs / iDms), 
		iHours = parseInt((iLeftMs - (iDays * iDms)) / iHms), 
		iMin = parseInt((iLeftMs - (iDays * iDms) - (iHours * iHms)) / iMms), 
		iSec = parseInt((iLeftMs - (iDays * iDms) - (iMin * iMms) - (iHours * iHms)) / iCms),
		iPerc = (iElapsedMs > 0) ? iElapsedMs / iDuration * 100 : 0;
		
	// display current positions and progress
	$(vPb).children('.percent').html('<b>'+iPerc.toFixed(1)+'%</b>');
	$(vPb).children('.elapsed').html('剩余：'+iHours+'小时'+iMin+'分'+iSec+'秒</b>');
	$(vPb).children('.pbar').children('.ui-progressbar-value').css('width', iPerc+'%');

	// in case of Finish
	if (iPerc >= 100) {
		clearInterval(vInterval);
		$(vPb).children('.percent').html('<b>100%</b>');
		$(vPb).children('.elapsed').html('Finished');
	}
}
$.fn.anim_progressbar = function (aOptions) {
	// def values
	iCms = 10000;
	iMms = 60 * iCms;
	iHms = 3600 * iCms;
	iDms = 24 * 3600 * iCms;

	// def options
	aDefOpts = {
		start: new Date(), // now
		finish: new Date().setTime(new Date().getTime() + 60 * iCms), // now + 60 sec
		interval: 100
	}
	aOpts = jQuery.extend(aDefOpts, aOptions);
	vPb = this;

	// each progress bar
	return this.each(
	function(){
		iDuration = aOpts.finish - aOpts.start;
		$(vPb).children('.pbar').progressbar();
	}
	);
}

// 获取系统时间
function current(){ 
	var str = "1234567890";
	var a = str.substring(0,8); //==str.substring(8)---结果:12345678
	var today = new Date();    
	var day   = today.getDate();               
	var month = today.getMonth() + 1; //显示月份比实际月份小1,所以要加1
	var year  = today.getFullYear();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	
	// 修正显示格式
	month = month < 10?"0" + month:month;
	day = day < 10?"0" + day:day;
	hours = hours < 10?"0" + hours:hours;
	minutes = minutes < 10?"0" + minutes:minutes;
	seconds = seconds < 10?"0" + seconds:seconds;
	// 输出
	var date  = year + "-" + month + "-" + day + "&nbsp;&nbsp;" + hours + ":" + minutes + ":" + seconds;	//结果:2008-05-08,2008-12-29
	return date;
}