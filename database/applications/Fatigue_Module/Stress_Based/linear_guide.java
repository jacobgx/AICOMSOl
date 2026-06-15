/*
 * linear_guide.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:03 by COMSOL 6.3.0.290. */
public class linear_guide {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Stress_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "linear_guide_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u63a5\u89e6\u4f53\u79ef");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").set(3, 4, 5, 6);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u63a5\u89e6\u533a\u57df");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(30, 32, 39, 41);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u63a5\u89e6\u6df1\u5ea6\u7ebf");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(59);

    model.component("comp1").view("view1").set("renderwireframe", false);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "400 [um]", "\u6eda\u9053\u957f\u5ea6");
    model.param().set("R1", "2 [mm]", "\u69fd\u534a\u5f84");
    model.param().set("a", "161 [um]", "\u957f\u534a\u8f74");
    model.param().set("b", "36 [um]", "\u77ed\u534a\u8f74");
    model.param().set("pMax", "1.14 [GPa]", "\u6700\u5927\u63a5\u89e6\u538b\u529b");
    model.param().set("gC", "45 [deg]", "\u63a5\u89e6\u89d2");
    model.param().set("ga", "a/R1 [rad]", "\u957f\u534a\u8f74\u89d2\u5ea6");
    model.param().set("sTot", "L-2*b", "\u603b\u79fb\u52a8\u957f\u5ea6");
    model.param().set("nStep", "50", "\u6b65\u6570");
    model.param().set("ds", "sTot/nStep", "\u79fb\u52a8\u589e\u91cf");
    model.param().set("s0", "-sTot/2", "\u63a5\u89e6\u7684\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("n", "0", "\u5206\u6790\u6b65\u957f");
    model.param().set("alpha", "L/(2*(nStep/2)^3)", "\u8f7d\u8377\u9636\u8dc3\u51fd\u6570\u5e38\u6570");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("g", "atan2((0.014-z),(0.0075-y))", "\u6eda\u9053\u89d2\u4f4d\u7f6e");
    model.component("comp1").variable("var1").set("dg", "g-gC", "\u4e3b\u63a5\u89e6\u4e2d\u5fc3\u89d2\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("db", "x-xC", "\u6b21\u63a5\u89e6\u4e2d\u95f4\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("xC", "cPos(n)", "\u6cbf\u6eda\u9053\u7684\u63a5\u89e6\u4e2d\u5fc3");
    model.component("comp1").variable("var1").set("b1", "db/b", "\u77ed\u8f74\u63a5\u89e6\u6bd4");
    model.component("comp1").variable("var1").set("g1", "dg/ga", "\u957f\u8f74\u63a5\u89e6\u6bd4");
    model.component("comp1").variable("var1").set("rC", "(g1)^2+(b1)^2", "\u63a5\u89e6\u6807\u8bc6\u7b26");
    model.component("comp1").variable("var1")
         .set("pMag", "if(rC<1,sqrt(1-rC),0)", "\u8f7d\u8377\u653e\u5927\u500d\u6570");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "cPos");
    model.component("comp1").func("an1").set("expr", "alpha*(x-nStep/2)^3");
    model.component("comp1").func("an1").set("fununit", "m");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("sel2");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pMax*pMag");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(6);
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2, 13, 48, 49);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "n", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1,nStep)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u7814\u7a76 1/\u4e09\u7ef4\u955c\u50cf");
    model.result().dataset("mir1").set("quickplane", "xz");
    model.result("pg1").run();
    model.result("pg1").label("\u8868\u9762\uff1a\u7b49\u6548\u5e94\u529b");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").setIndex("looplevel", 26, 0);
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 1);

    model.view("view2").set("transparency", false);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u8868\u9762\uff1a\u63a5\u89e6\u5e94\u529b");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("expr", "solid.sp3Gp");
    model.result("pg2").feature("vol1").set("descr", "\u7b2c\u4e09\u4e3b\u5e94\u529b");
    model.result("pg2").feature("vol1").set("colortabletrans", "reverse");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("\u7814\u7a76 1/\u622a\u9762\uff1a\u5168\u539a\u5ea6");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6b21\u8868\u5c42\uff1a\u7b49\u6548\u5e94\u529b");
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.misesGp");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5168\u539a\u5ea6\uff1a\u7b49\u6548\u5e94\u529b");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("sel3");
    model.result("pg4").feature("lngr1").set("expr", "solid.misesGp");
    model.result("pg4").feature("lngr1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg4").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6df1\u5ea6 (mm)");
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5168\u539a\u5ea6\uff1a\u526a\u5207\u5e94\u529b");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "solid.sGpxy");
    model.result("pg5").feature("lngr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxy \u5206\u91cf");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u6700\u5927 Mises");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", "7.5-2.0235*cos(45[deg])");
    model.result().dataset("cpt1").set("pointz", "14-2.0235*sin(45[deg])");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u6700\u5927\u526a\u5207");
    model.result().dataset("cpt2").set("pointy", "7.5-2.0166*cos(45[deg])");
    model.result().dataset("cpt2").set("pointz", "14-2.0166*sin(45[deg])");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6700\u5927 Mises \u70b9\uff1a\u5e94\u529b\u5206\u91cf");
    model.result("pg6").set("data", "cpt1");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("expr", "solid.misesGp");
    model.result("pg6").feature("ptgr1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "cPos(n)");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "Mises", 0);
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("expr", "solid.sGpxx");
    model.result("pg6").feature("ptgr2").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg6").feature("ptgr2").setIndex("legends", "sx", 0);
    model.result("pg6").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr3").set("expr", "solid.sGpyy");
    model.result("pg6").feature("ptgr3").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyy \u5206\u91cf");
    model.result("pg6").feature("ptgr3").setIndex("legends", "sy & sz", 0);
    model.result("pg6").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr4").set("expr", "solid.sGpxy");
    model.result("pg6").feature("ptgr4").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxy \u5206\u91cf");
    model.result("pg6").feature("ptgr4").setIndex("legends", "sxy & sxz", 0);
    model.result("pg6").feature().duplicate("ptgr5", "ptgr4");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr5").set("expr", "solid.sGpyz");
    model.result("pg6").feature("ptgr5").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cyz \u5206\u91cf");
    model.result("pg6").feature("ptgr5").setIndex("legends", "syz", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u8f7d\u8377\u4f4d\u7f6e (mm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6700\u5927\u526a\u5207\u70b9\uff1a\u5e94\u529b\u5206\u91cf");
    model.result("pg7").set("data", "cpt2");
    model.result("pg7").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("stre1", "StressBasedModel", 3);
    model.component("comp1").physics("ftg").feature("stre1").selection().named("sel1");
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueHCFMultiaxModel", "DangVan");
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueInputPhysics", "solid");

    model.component("comp1").material("mat1").propertyGroup()
         .create("fatigueStressDangVan", "fatigueStressDangVan", "Dang_Van[Fatigue]");
    model.component("comp1").material("mat1").propertyGroup("fatigueStressDangVan")
         .set("a_DangVan", new String[]{"0.23"});
    model.component("comp1").material("mat1").propertyGroup("fatigueStressDangVan")
         .set("b_DangVan", new String[]{"248[MPa]"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("colortable", "Traffic");
    model.result("pg8").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg8").feature("surf1").create("mrkr1", "Marker");
    model.result("pg8").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg8").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg8").run();
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().dataset("mir2").label("\u7814\u7a76 2/\u4e09\u7ef4\u955c\u50cf");
    model.result("pg8").run();
    model.result("pg8").set("data", "mir2");
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").set("data", "dset2");
    model.result().dataset("cpl2").label("\u7814\u7a76 2/\u622a\u9762\uff1a\u5168\u539a\u5ea6");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u6b21\u8868\u5c42\uff1a\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50");
    model.result("pg9").set("data", "cpl2");
    model.result("pg9").set("view", "view5");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "ftg.fus");
    model.result("pg9").feature("surf1").set("descr", "\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50");
    model.result("pg9").run();
    model.result("pg2").run();

    model.title("\u7ebf\u6027\u5bfc\u8f68\u4e2d\u7684\u6eda\u52a8\u63a5\u89e6\u75b2\u52b3");

    model
         .description("\u7ebf\u6027\u5bfc\u8f68\u4e0a\u7684\u8d1f\u8f7d\u8d85\u51fa\u5236\u9020\u5546\u89c4\u683c\u9650\u5236\uff0c\u56e0\u6b64\uff0c\u9700\u8981\u6ce8\u610f\u63a5\u89e6\u8f7d\u8377\u662f\u5426\u4f1a\u5f15\u8d77\u75b2\u52b3\u5265\u843d\u3002\u5728\u6eda\u52a8\u63a5\u89e6\u75b2\u52b3\u5206\u6790\u8fc7\u7a0b\u4e2d\uff0c\u6211\u4eec\u5bf9\u627f\u53d7\u5927\u90e8\u5206\u8f7d\u8377\u7684\u6eda\u52a8\u4f53\u7684\u8fd0\u52a8\u8fdb\u884c\u6a21\u62df\uff0c\u5e76\u4f7f\u7528 Dang Van \u6a21\u578b\u8fdb\u884c\u75b2\u52b3\u8bc4\u4f30\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("linear_guide.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
