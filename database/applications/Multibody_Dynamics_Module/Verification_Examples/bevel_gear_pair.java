/*
 * bevel_gear_pair.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:27 by COMSOL 6.3.0.290. */
public class bevel_gear_pair {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n1", "50", "\u9f7f\u6570\uff0c\u9f7f\u8f6e 1");
    model.param().set("dp1", "100[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u9f7f\u8f6e 1");
    model.param().set("gamma1", "78.7[deg]", "\u9525\u89d2\uff0c\u9f7f\u8f6e 1");
    model.param().set("n2", "10", "\u9f7f\u6570\uff0c\u9f7f\u8f6e 2");
    model.param().set("dp2", "20[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u9f7f\u8f6e 2");
    model.param().set("gamma2", "11.3[deg]", "\u9525\u89d2\uff0c\u9f7f\u8f6e 2");
    model.param().set("alpha", "20[deg]", "\u538b\u529b\u89d2");
    model.param().set("xcx1", "0[mm]", "\u9f7f\u8f6e 1 \u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("xcy1", "0[mm]", "\u9f7f\u8f6e 1 \u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("xcz1", "0[mm]", "\u9f7f\u8f6e 1 \u4e2d\u5fc3\uff0cz \u5750\u6807");
    model.param().set("ex1", "0", "\u9f7f\u8f6e 1 \u8f74\uff0cx \u5206\u91cf");
    model.param().set("ey1", "-1", "\u9f7f\u8f6e 1 \u8f74\uff0cy \u5206\u91cf");
    model.param().set("ez1", "0", "\u9f7f\u8f6e 1 \u8f74\uff0cz \u5206\u91cf");
    model.param().set("xcx2", "0[mm]", "\u9f7f\u8f6e 2 \u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("xcy2", "-10[mm]", "\u9f7f\u8f6e 2 \u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("xcz2", "50[mm]", "\u9f7f\u8f6e 2 \u4e2d\u5fc3\uff0cz \u5750\u6807");
    model.param().set("ex2", "0", "\u9f7f\u8f6e 2 \u8f74\uff0cx \u5206\u91cf");
    model.param().set("ey2", "0", "\u9f7f\u8f6e 2 \u8f74\uff0cy \u5206\u91cf");
    model.param().set("ez2", "-1", "\u9f7f\u8f6e 2 \u8f74\uff0cz \u5206\u91cf");
    model.param().set("M_b", "10[N*m]", "\u6746\u548c\u9f7f\u8f6e 2 \u4e4b\u95f4\u7684\u626d\u77e9");
    model.param().set("th_b", "1[rad]", "\u6746\u7684\u65cb\u8f6c\u89d2\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module\\3D\\External_Gears\\bevel_gear.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n", "n1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dp", "dp1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "alpha", "alpha");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "gamma", "gamma1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "adr", 0.75);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "tfr", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rfr", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dhr", 0.6);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wgr", 0.03);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "lsr", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "xc", "xcx1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "yc", "xcy1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "zc", "xcz1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "egx", "ex1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "egy", "ey1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "egz", "ez1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "n", "n2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dp", "dp2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "alpha", "alpha");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "gamma", "gamma2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "adr", 0.3);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "htr", 3);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "tfr", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "rfr", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wgr", 0.4);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wbr", 0.1);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wcr", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "lsr", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "xc", "xcx2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "yc", "xcy2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "zc", "xcz2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "egx", "ex2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "egy", "ey2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "egz", "ez2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "th", "360/(2*n2)[deg]");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "dp2/8");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "dp1/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"xcx2", "xcy2", "xcz2"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{0, 0, -1});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createGears");
    model.component("comp1").physics("mbd").feature("bvg1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("bvg1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("bvg1").feature("fix1").set("WeakConstraints", true);
    model.component("comp1").physics("mbd").feature("bvg2").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").create("grp1", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp1").set("Wheel", "bvg1");
    model.component("comp1").physics("mbd").feature("grp1").set("Pinion", "bvg2");
    model.component("comp1").physics("mbd").feature("grp1").set("ContactForceComputation", "WeakConstraints");
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(5);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u6746");
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "bvg2");
    model.component("comp1").physics("mbd").feature("hgj1").set("CenterOfJointType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(648, 650);
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("hgj1").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj1").feature("afm1").set("Ms", "M_b");
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj2").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjp1").selection().set(647, 649);
    model.component("comp1").physics("mbd").feature("hgj2").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj2").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("thp", "-th_b*t[1/s]");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);

    model.study("std1").feature("time").set("tlist", "range(0,0.05,1)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").set("expr", new String[]{"mbd.grp1.Fc"});
    model.result().numerical("gev1").set("descr", new String[]{"\u63a5\u89e6\u70b9\u7684\u529b"});
    model.result().numerical("gev1").set("unit", new String[]{"N"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").set("expr", new String[]{"mbd.bvg1.RFx"});
    model.result().numerical("gev2").set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf"});
    model.result().numerical("gev2").set("unit", new String[]{"N"});
    model.result().numerical("gev2").set("expr", new String[]{"mbd.bvg1.RFx", "mbd.bvg1.RFy"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf"});
    model.result().numerical("gev2").set("expr", new String[]{"mbd.bvg1.RFx", "mbd.bvg1.RFy", "mbd.bvg1.RFz"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cz \u5206\u91cf"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev3").set("expr", new String[]{"mbd.bvg1.RMx"});
    model.result().numerical("gev3")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf"});
    model.result().numerical("gev3").set("unit", new String[]{"N*m"});
    model.result().numerical("gev3").set("expr", new String[]{"mbd.bvg1.RMx", "mbd.bvg1.RMy"});
    model.result().numerical("gev3")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cy \u5206\u91cf"});
    model.result().numerical("gev3").set("expr", new String[]{"mbd.bvg1.RMx", "mbd.bvg1.RMy", "mbd.bvg1.RMz"});
    model.result().numerical("gev3")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cy \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cz \u5206\u91cf"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53cd\u4f5c\u7528\u529b");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.bvg1.RFx"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.bvg1.RFx", "mbd.bvg1.RFy"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.bvg1.RFx", "mbd.bvg1.RFy", "mbd.bvg1.RFz"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\uff0cz \u5206\u91cf"});
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u53cd\u4f5c\u7528\u529b (N)");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u53cd\u4f5c\u7528\u529b\u77e9");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.bvg1.RMx"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.bvg1.RMx", "mbd.bvg1.RMy"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cy \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.bvg1.RMx", "mbd.bvg1.RMy", "mbd.bvg1.RMz"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9\uff0cx \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cy \u5206\u91cf", "\u53cd\u4f5c\u7528\u529b\u77e9\uff0cz \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u53cd\u4f5c\u7528\u529b\u77e9 (N-m)");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").label("\u9f7f\u8f6e");
    model.result("pg2").run();
    model.result("pg2").feature().remove("arwl1");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("colortable", "AuroraAustralis");
    model.result("pg2").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result("pg2").run();

    model.title("\u9525\u9f7f\u8f6e\u4e0a\u7684\u529b\u548c\u529b\u77e9");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u6a21\u62df\u76f4\u9525\u9f7f\u8f6e\u526f\uff0c\u5176\u4e2d\u5c06\u9f7f\u8f6e\u4f5c\u4e3a\u521a\u4f53\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u901a\u8fc7\u9f7f\u8f6e\u526f\u8fde\u63a5\u3002\u672c\u4f8b\u6267\u884c\u77ac\u6001\u7814\u7a76\u6765\u8ba1\u7b97\u56fa\u5b9a\u9f7f\u8f6e\u627f\u53d7\u7684\u529b\u548c\u529b\u77e9\uff0c\u5e76\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("bevel_gear_pair.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
