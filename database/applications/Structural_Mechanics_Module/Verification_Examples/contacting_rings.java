/*
 * contacting_rings.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:22 by COMSOL 6.3.0.290. */
public class contacting_rings {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("r1", "160[mm]");
    model.param().descr("r1", "\u5916\u73af\u534a\u5f84");
    model.param().set("r2", "111.5[mm]");
    model.param().descr("r2", "\u5185\u73af\u534a\u5f84");
    model.param().set("t1", "4[mm]");
    model.param().descr("t1", "\u5916\u73af\u539a\u5ea6");
    model.param().set("t2", "11.5[mm]");
    model.param().descr("t2", "\u5185\u73af\u539a\u5ea6");
    model.param().set("y0", "111.5[mm]-156[mm]");
    model.param().descr("y0", "\u5185\u73af\u4e2d\u5fc3\u521d\u59cb y \u8f74\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r1-t1");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -95);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "r2");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"0", "y0"});
    model.component("comp1").geom("geom1").feature("c2").set("rot", -95);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "t2", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("c1", 2, 3);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("L", "(r1-t1)*(atan2(-y,-x)-pi/2)");
    model.component("comp1").variable("var1").descr("L", "\u5916\u73af\u957f\u5ea6");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(1);
    model.component("comp1").pair("p1").destination().set(4);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});

    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlAuglag", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("pfm", "1/50");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("sliptotStore", true);
    model.component("comp1").physics("solid").feature("dcnt1").create("stb1", "ContactStabilization", 1);

    model.param().set("phi", "0[rad]");
    model.param().descr("phi", "\u5185\u73af\u65cb\u8f6c\u89d2\u5ea6");

    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig1").selection().set(5);
    model.component("comp1").physics("solid").feature("rig1").set("CenterOfRotationType", "userDefined");
    model.component("comp1").physics("solid").feature("rig1").set("xc", new String[]{"0", "y0", "0"});
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("solid").feature("rig1").set("phi0", "-phi");
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1").set("Ft", new int[]{0, -500, 0});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 60);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 100);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "r1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "r1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "phi", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,pi/120,pi/6)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_Tn_p1").set("scaleval", "1e5");
    model.sol("sol1").feature("v1").feature("comp1_solid_Tt_p1").set("scaleval", "1e5");
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", "pi/1000");
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", "pi/1000");
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "pi/10000");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subiter", 15);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("defaultPlotID", "stress");
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.sol("sol1").run("st1");

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("defaultPlotID", "stress");
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"solid.u_rig1", "0"});
    model.result("pg1").feature("pttraj1").setIndex("expr", "y0+solid.v_rig1", 1);
    model.result("pg1").feature("pttraj1").set("linecolor", "red");
    model.result("pg1").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj1").set("arrowexpr", new String[]{"cos(phi+5[deg])", "0"});
    model.result("pg1").feature("pttraj1").setIndex("arrowexpr", "sin(-phi-5[deg])", 1);
    model.result("pg1").feature("pttraj1").set("arrowtype", "cone");
    model.result("pg1").feature("pttraj1").set("arrowbase", "head");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").label("\u521a\u4f53\u4f4d\u79fb");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"solid.rig1.v"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u4f4d\u79fb\uff0cy \u5206\u91cf"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").label("\u63a5\u89e6\u538b\u529b\uff0c\u5916\u73af");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u63a5\u89e6\u538b\u529b (N/m<sup>2</sup>)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "dst2src_p1(solid.Tn_p1)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "L");
    model.result("pg3").feature("lngr1").set("xdataunit", "mm");
    model.result("pg3").feature("lngr1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("lngr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").feature("gmrk1").set("display", "max");
    model.result("pg3").feature("lngr1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg3").feature("lngr1").feature("gmrk1").set("inclycoord", false);
    model.result("pg3").feature("lngr1").feature("gmrk1").set("includeunit", true);
    model.result("pg3").run();
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").selection().set(4);
    model.result().dataset().create("par1", "Parametric1D");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u7d2f\u79ef\u6ed1\u79fb");
    model.result("pg4").set("data", "par1");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "gpeval(4,solid.sliptot)");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u63a5\u89e6\u73af\u4e4b\u95f4\u7684\u6469\u64e6");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u95ee\u9898\u6a21\u62df\u4e00\u4e2a\u73af\u5728\u53e6\u4e00\u4e2a\u73af\u4e2d\u7684\u9ecf\u6ed1\u6469\u64e6\u963b\u529b\u3002\u8ba1\u7b97\u4e86\u5185\u73af\u7684\u4f4d\u79fb\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("contacting_rings.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
