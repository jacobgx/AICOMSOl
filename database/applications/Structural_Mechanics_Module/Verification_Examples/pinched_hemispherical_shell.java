/*
 * pinched_hemispherical_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class pinched_hemispherical_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 10);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{10, 10, 10});
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"-5", "-5", "10*cos(18*pi/180)[m]"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("csur1", "ConvertToSurface");
    model.component("comp1").geom("geom1").feature("csur1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").run("csur1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("csur1", 1, 2, 3, 4, 5, 6, 7, 8);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94a2");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"68.25e6"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"6850"});

    model.component("comp1").physics("shell").feature("to1").set("d", 0.04);
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(1, 4);
    model.component("comp1").physics("shell").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("shell").feature("disp1").selection().set(4);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("shell").feature("pl1").label("\u70b9\u8f7d\u8377\uff0cX");
    model.component("comp1").physics("shell").feature("pl1").selection().set(4);
    model.component("comp1").physics("shell").feature("pl1").set("forcePoint", new String[]{"-100*para", "0", "0"});
    model.component("comp1").physics("shell").create("pl2", "PointLoad", 0);
    model.component("comp1").physics("shell").feature("pl2").label("\u70b9\u8f7d\u8377\uff0cY");
    model.component("comp1").physics("shell").feature("pl2").selection().set(2);
    model.component("comp1").physics("shell").feature("pl2").set("forcePoint", new String[]{"0", "100*para", "0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run();

    model.param().set("para", "0");
    model.param().descr("para", "\u6c42\u89e3\u5668\u53c2\u6570");

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1,1)", 0);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "5e5");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "-u");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "para*100[N]");
    model.result("pg2").feature("ptgr1").set("linewidth", 3);
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u529b x \u4f5c\u7528\u4e0b\u7684 -u", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").selection().set(2);
    model.result("pg2").feature("ptgr2").set("expr", "v");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u529b y \u4f5c\u7528\u4e0b\u7684 v", 0);
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u529b (N)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u529b\u4f5c\u7528\u4e0b\u7684\u4f4d\u79fb (m)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(2, 4);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "u", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "v", 1);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("\u5939\u7d27\u7684\u534a\u7403\u58f3");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u7814\u7a76\u4e00\u4e2a\u534a\u7403\u58f3\u7684\u975e\u7ebf\u6027\u53d8\u5f62\u3002");

    model.label("pinched_hemispherical_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
