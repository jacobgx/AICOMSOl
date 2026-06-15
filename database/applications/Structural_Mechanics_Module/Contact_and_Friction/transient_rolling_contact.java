/*
 * transient_rolling_contact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:04 by COMSOL 6.3.0.290. */
public class transient_rolling_contact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new int[]{-1, 0});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new int[]{0, -1});
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 180);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca2").set("point1", new double[]{0.5, -1});
    model.component("comp1").geom("geom1").feature("ca2").set("point2", new double[]{1.5, 0});
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 270);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("ca1", 2);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("ca2", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("ccur1").selection("input").set("ca1", "ca2", "ls1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("ccur1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ccur1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.15);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{-0.85, 0});
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", 0.02, 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 5);

    model.param().set("theta", "10[deg]");
    model.param().descr("theta", "\u8f85\u52a9\u51e0\u4f55\u53c2\u6570");

    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "theta");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"5[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3[kg/m^3]"});

    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(8, 9, 12, 15);

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().named("geom1_csel1_bnd");
    model.component("comp1").pair("p1").destination().named("sel1");

    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlAuglag", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("useRelaxation", "Never");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1")
         .set("FrictionModel", "DynamicCoulomb");
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mustat", 0.5);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mudyn", 0.3);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("dcfric", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("storeFricEnergy", true);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("V", "intop1(g_const*solid.rho*(y+0.85[m]))*1[m]");
    model.component("comp1").variable("var1").descr("V", "\u52bf\u80fd");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().named("sel1");
    model.component("comp1").cpl("maxop1").set("points", "integration");

    model.component("comp1").variable("var1")
         .set("mu_max", "maxop1(if(solid.dcnt1.isContact_p1,solid.dcnt1.mu_fric,0))");
    model.component("comp1").variable("var1").descr("mu_max", "\u6469\u64e6\u7cfb\u6570\u6700\u5927\u503c");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);

    model.study("std1").feature("time").set("tlist", "range(0,2e-2,4)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_Tn_p1").set("scaleval", "5e4");
    model.sol("sol1").feature("v1").feature("comp1_solid_Tt_p1").set("scaleval", "5e4");
    model.sol("sol1").feature("v1").feature("comp1_solid_Wfric_p1").set("scaleval", "5e-3*5e4");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "5e-3");
    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "5e-3");
    model.sol("sol1").feature("t1").feature("se1").set("ntolfact", 0.1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subdtech", "const");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subtermconst", "itertol");

    model.component("comp1").variable("var1")
         .set("W_tot", "solid.Wk_tot+solid.Ws_tot+V+solid.Wfric_tot", "\u603b\u52bf\u80fd");
    model.component("comp1").variable("var1").descr("W_tot", "\u603b\u80fd\u91cf");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "W_tot");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 201, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "von Mises \u5e94\u529b (N/m<sup>2</sup>) \u548c\u70b9\u8f68\u8ff9");
    model.result("pg2").create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").set("plotdata", "points");
    model.result("pg2").feature("pttraj1").set("expr", new String[]{"u-cos(theta)", "0"});
    model.result("pg2").feature("pttraj1").setIndex("expr", "v-sin(theta)", 1);
    model.result("pg2").feature("pttraj1").selection().set(5);
    model.result("pg2").feature("pttraj1").set("linecolor", "red");
    model.result("pg2").feature("pttraj1").set("pointtype", "point");
    model.result("pg2").feature("pttraj1").set("sphereradiusscaleactive", true);
    model.result("pg2").feature("pttraj1").set("sphereradiusscale", 15);
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
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", "1e-1");
    model.result().export("anim1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u80fd\u91cf");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "solid.Wk_tot", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "solid.Ws_tot", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "V", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "solid.Wfric_tot", 3);
    model.result("pg3").feature("glob1").setIndex("descr", "\u603b\u6469\u64e6\u8017\u80fd", 3);
    model.result("pg3").feature("glob1").setIndex("expr", "W_tot", 4);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6469\u64e6\u7cfb\u6570");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "mu_max", 0);
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u603b\u80fd\u91cf");
    model.result("pg2").run();

    model.title("\u77ac\u6001\u6eda\u52a8\u63a5\u89e6");

    model
         .description("\u8fd9\u4e00\u6982\u5ff5\u793a\u4f8b\u4ecb\u7ecd\u5982\u4f55\u5904\u7406\u9ecf\u6ed1\u6469\u64e6\u8f6c\u53d8\u7684\u77ac\u6001\u63a5\u89e6\u95ee\u9898\u3002\u5728 U \u578b\u7ba1\u9876\u90e8\u91ca\u653e\u627f\u53d7\u91cd\u529b\u8f7d\u8377\u7684\u8f6f\u7a7a\u5fc3\u7ba1\uff0c\u8f6f\u7ba1\u4f1a\u4ea7\u751f\u6ed1\u52a8\u548c\u6eda\u52a8\uff0c\u5177\u4f53\u7684\u8fd0\u52a8\u53d6\u51b3\u4e8e\u5176\u5728 U \u578b\u7ba1\u4e2d\u7684\u4f4d\u7f6e\u53ca\u5176\u901f\u5ea6\u3002\u7531\u4e8e\u63a5\u89e6\u529b\u548c\u60ef\u6027\u529b\uff0c\u8f6f\u7ba1\u7684\u6a2a\u622a\u9762\u4e5f\u4f1a\u53d8\u6210\u692d\u5706\u5f62\u3002\u80fd\u91cf\u5e73\u8861\u53ef\u9a8c\u8bc1\u89e3\u7684\u7cbe\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("transient_rolling_contact.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
