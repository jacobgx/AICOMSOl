/*
 * holed_plate_fracture.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:13 by COMSOL 6.3.0.290. */
public class holed_plate_fracture {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Damage");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("height", "120[mm]");
    model.param().descr("height", "\u677f\u9ad8\u5ea6");
    model.param().set("width", "65[mm]");
    model.param().descr("width", "\u677f\u5bbd\u5ea6");
    model.param().set("notchHeight", "0.5[mm]");
    model.param().descr("notchHeight", "\u7f3a\u53e3\u9ad8\u5ea6");
    model.param().set("notchWidth", "10[mm]");
    model.param().descr("notchWidth", "\u7f3a\u53e3\u5bbd\u5ea6");
    model.param().set("notchLocation", "65[mm]");
    model.param().descr("notchLocation", "\u7f3a\u53e3\u4f4d\u7f6e");
    model.param().set("holeRadius", "10[mm]");
    model.param().descr("holeRadius", "\u5b54\u534a\u5f84");
    model.param().set("holeX", "36.5[mm]");
    model.param().descr("holeX", "\u5b54\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("holeY", "51[mm]");
    model.param().descr("holeY", "\u5b54\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("elemSize", "0.25[mm]");
    model.param().descr("elemSize", "\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param().set("para", "0");
    model.param().descr("para", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"width", "height"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "holeRadius");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"holeX", "holeY"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "5[mm]");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"20[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "5[mm]");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"20[mm]", "height-20[mm]"});
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"notchWidth", "notchHeight"});
    model.component("comp1").geom("geom1").feature("r2")
         .set("pos", new String[]{"0", "notchLocation-notchHeight/2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "c2", "c3", "r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"width", "holeY+elemSize*12"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"holeX", "holeY+elemSize*5"});
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"width", "holeY-elemSize*12"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"holeX", "holeY-elemSize*5"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "holeX", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "holeY", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "holeX", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "notchLocation+elemSize*12", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "notchWidth*3/4", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "notchLocation+elemSize*12", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "notchWidth*3/4", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "notchLocation+notchHeight/2", 3, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "notchWidth*3/4", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "notchLocation-notchHeight/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "notchWidth*3/4", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "notchLocation-elemSize*5", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "holeX+holeRadius", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "holeY", 2, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("dif1", "ls1", "ls2", "pol1", "pol2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("uni1", 14, 15, 16, 17, 18, 19, 20, 22, 23);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 3, 4);
    model.component("comp1").geom("geom1").run("mcd1");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "1[mm]");
    model.component("comp1").physics("solid").feature("lemm1").create("dmg1", "Damage", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg1").set("ModelType", "PhaseFieldDmg");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg1").set("lint_ph", "0.25[mm]");
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig1").selection().set(9, 10, 13, 14);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 0);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rig1").set("WeakConstraints", true);
    model.component("comp1").physics("solid").feature().duplicate("rig2", "rig1");
    model.component("comp1").physics("solid").feature("rig2").selection().set(11, 12, 15, 16);
    model.component("comp1").physics("solid").feature("rig2").setIndex("U0", "para*2[mm]", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"6[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.22"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2000"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PhaseFieldDamage", "PhaseFieldDamage", "Phase_field_damage");
    model.component("comp1").material("mat1").propertyGroup("PhaseFieldDamage")
         .set("Gc", new String[]{"2280[J/m^2]"});

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "elemSize");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.0075);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "height", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "height", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 0.0025);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", 0.0025);
    model.sol("sol1").feature("s1").feature("se1").set("segiter", 3);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sdp1Gp");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u88c2\u7eb9\u76f8\u573a");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.phic");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u88c2\u7eb9\u8f68\u8ff9");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "solid.phic<0.6");
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").set("inheritplot", "surf1");
    model.result("pg3").feature("line1").set("inheritcolor", false);
    model.result("pg3").feature("line1").set("inheritrange", false);
    model.result("pg3").feature("line1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("line1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("filt1").set("expr", "solid.phic<0.6");
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "solid.sdp1Gp");
    model.result("pg3").feature("con1").set("levelmethod", "levels");
    model.result("pg3").feature("con1").set("levels", "range(30,10,80)");
    model.result("pg3").feature("con1").set("inheritplot", "surf1");
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("inheritrange", false);
    model.result("pg3").feature("con1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("con1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("con1").feature("filt1").set("expr", "solid.phic<0.9");
    model.result("pg3").feature("con1").feature("filt1").set("nodespec", "all");
    model.result("pg3").run();
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("expr", new String[]{"solid.xcx_rig1", "solid.xcy_rig1"});
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1").set("arrowexpr", new String[]{"solid.rig1.RFx", "solid.rig1.RFy"});
    model.result("pg3").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg3").feature("pttraj1").set("arrowscale", "4E-5");
    model.result("pg3").feature("pttraj1").set("inheritplot", "surf1");
    model.result("pg3").feature("pttraj1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj1").feature("def1").set("expr", new String[]{"solid.rig1.u", "0"});
    model.result("pg3").feature("pttraj1").feature("def1").setIndex("expr", "solid.rig1.v", 1);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("pttraj2", "pttraj1");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj2").setIndex("expr", "solid.xcx_rig2", 0);
    model.result("pg3").feature("pttraj2").setIndex("expr", "solid.xcy_rig2", 1);
    model.result("pg3").feature("pttraj2").setIndex("arrowexpr", "solid.rig2.RFx", 0);
    model.result("pg3").feature("pttraj2").setIndex("arrowexpr", "solid.rig2.RFy", 1);
    model.result("pg3").feature("pttraj2").set("inheritplot", "pttraj1");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj2").feature("def1").setIndex("expr", "solid.rig2.u", 0);
    model.result("pg3").feature("pttraj2").feature("def1").setIndex("expr", "solid.rig2.v", 1);
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f7d\u8377 vs");
    model.result("pg4").label("\u8f7d\u8377 vs. \u4f4d\u79fb");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u4f4d\u79fb (mm)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8f7d\u8377 (kN)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "solid.rig2.RFy", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "solid.rig2.v");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 88, 0);
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u7a7f\u5b54\u677f\u7684\u8106\u6027\u65ad\u88c2");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u76f8\u573a\u635f\u4f24\u6a21\u578b\u6765\u6a21\u62df\u5e26\u7f3a\u53e3\u7684\u7a7f\u5b54\u677f\u7684\u8106\u6027\u65ad\u88c2\u3002\u901a\u8fc7\u5efa\u7acb\u677f\u7684\u51e0\u4f55\u7ed3\u6784\u4ee5\u4ea7\u751f\u6df7\u5408\u6a21\u5f0f\u7684\u65ad\u88c2\uff0c\u5e76\u6839\u636e\u5b9e\u9a8c\u6570\u636e\u786e\u5b9a\u677f\u7684\u5c3a\u5bf8\u548c\u6750\u6599\u5c5e\u6027\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4e3a\u6b64\u7c7b\u95ee\u9898\u8bbe\u7f6e\u6709\u6548\u4e14\u7a33\u5b9a\u7684\u6c42\u89e3\u5668\u914d\u7f6e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("holed_plate_fracture.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
