/*
 * slider_crank_mechanism.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:27 by COMSOL 6.3.0.290. */
public class slider_crank_mechanism {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.param().set("d", "0.1[m]");
    model.param().descr("d", "\u539a\u5ea6");
    model.param().set("l", "1[m]");
    model.param().descr("l", "\u957f\u5ea6");
    model.param().set("m", "1[kg]");
    model.param().descr("m", "\u8d28\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d", "1"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-d/2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -45);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"l/sqrt(2)", "0"});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"m/(l*d^2)"});

    model.component("comp1").physics("mbd").prop("d").set("d", "d");
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(1);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd2").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd2").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("rd2").set("ConsistentInitialization", "ForceInitialValues");
    model.component("comp1").physics("mbd").feature("rd2").set("TranslationFirstAxis", true);
    model.component("comp1").physics("mbd").feature("rd2").feature("init1").set("ut", new int[]{-4, 0, 0});
    model.component("comp1").physics("mbd").feature("rd2").feature("init1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd2").feature("init1").feature("crb1").selection().set(8);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjb1").selection().set(1);
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjb1").selection().set(5);
    model.component("comp1").physics("mbd").create("rslj1", "ReducedSlotJoint", -1);
    model.component("comp1").physics("mbd").feature("rslj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("rslj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("rslj1").set("JointTranslationalAxis", "AttachedOnSource");
    model.component("comp1").physics("mbd").feature("rslj1").feature("cjb1").selection().set(8);
    model.component("comp1").physics("mbd").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1, 2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Wp", "intop1(mbd.rho*g_const*y*d)");
    model.component("comp1").variable("var1").descr("Wp", "\u603b\u52bf\u80fd");
    model.component("comp1").variable("var1").set("W", "Wp+mbd.Wk_tot");
    model.component("comp1").variable("var1").descr("W", "\u603b\u80fd\u91cf");

    model.study("std1").feature("time").set("tlist", "range(0,0.005,10)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxorder", 3);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("rangecoloractive", "on");
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "1/sqrt(2) 1.5/sqrt(2) sqrt(2)");
    model.result().dataset("cpt1").set("pointy", "1/sqrt(2) 0.5/sqrt(2) 0");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").set("pointx", "1/sqrt(2)");
    model.result().dataset("cpt2").set("pointy", "1/sqrt(2)");
    model.result("pg1").run();
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("data", "cpt1");
    model.result("pg1").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature("pttraj1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").feature("col1").set("expr", "X");
    model.result("pg1").feature("pttraj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg1").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("frametype", "material");
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("slider_crank_mechanism_aA.txt");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "cpt2");
    model.result("pg3").label("\u52a0\u901f\u5ea6\uff1a\u70b9 A");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("expr", "mbd.u_ttX");
    model.result("pg3").feature("ptgr1").set("descr", "\u52a0\u901f\u5ea6\uff0cX \u5206\u91cf");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "COMSOL", 0);
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linemarker", "circle");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "Ref. 1", 0);
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53c2\u8003\u8d44\u6599 1", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymax", 60);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u70b9 A \u7684\u52a0\u901f\u5ea6\uff0cx \u5206\u91cf (m/s^2)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u80fd\u91cf");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"Wp"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u603b\u52bf\u80fd"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u603b\u52bf\u80fd", "\u603b\u52a8\u80fd"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot", "W"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u603b\u52bf\u80fd", "\u603b\u52a8\u80fd", "\u603b\u80fd\u91cf"});
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").feature("glob1").set("markers", 24);
    model.result("pg4").run();
    model.result("pg4").set("axislimits", true);
    model.result("pg4").run();
    model.result("pg4").set("ymax", 35);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg4").run();
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
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();

    model.title("\u66f2\u67c4\u6ed1\u5757\u673a\u6784");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u591a\u4f53\u52a8\u529b\u5b66\u57fa\u51c6\u95ee\u9898\uff0c\u7814\u7a76\u66f2\u67c4\u6ed1\u5757\u673a\u6784\u3002\u673a\u6784\u4e2d\u7684\u8fde\u63a5\u8bbe\u7f6e\u4e3a\u521a\u6027\u3002\u4e24\u4e2a\u94f0\u94fe\u5173\u8282\u548c\u4e00\u4e2a\u7f29\u8fdb\u69fd\u5173\u8282\u7528\u4e8e\u521b\u5efa\u8fd9\u4e9b\u8fde\u63a5\u3002\u672c\u4f8b\u5c06\u67d0\u4e2a\u70b9\u5904\u7684\u52a0\u901f\u5ea6\u65f6\u7a0b\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("slider_crank_mechanism.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
