/*
 * postbuckling_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class postbuckling_shell {

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

    model.param().set("R", "2540[mm]");
    model.param().descr("R", "\u9762\u677f\u534a\u5f84");
    model.param().set("L", "254[mm]");
    model.param().descr("L", "\u9762\u677f\u957f\u5ea6");
    model.param().set("thic", "6.35[mm]");
    model.param().descr("thic", "\u9762\u677f\u539a\u5ea6");
    model.param().set("theta", "0.1[rad]");
    model.param().descr("theta", "\u9762\u677f\u622a\u9762\u89d2\u5ea6");
    model.param().set("E0", "3.103[GPa]");
    model.param().descr("E0", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu0", "0.3");
    model.param().descr("nu0", "\u6cca\u677e\u6bd4");
    model.param().set("disp", "0");
    model.param().descr("disp", "\u4f4d\u79fb\u53c2\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"0", "R"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"L", "R"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", "theta");
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("rev1");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(1);
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("w_center", "-intop1(w)");
    model.component("comp1").variable("var1").descr("w_center", "\u58f3\u4e2d\u5fc3\u7684\u5782\u76f4\u4f4d\u79fb");

    model.component("comp1").physics("shell").feature("to1").set("d", "thic");
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(3, 4);
    model.component("comp1").physics("shell").create("pin1", "Pinned", 1);
    model.component("comp1").physics("shell").feature("pin1").selection().set(2);
    model.component("comp1").physics("shell").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("shell").feature("pl1").selection().set(4);
    model.component("comp1").physics("shell").feature("pl1").set("forcePoint", new String[]{"0", "0", "-P/4"});
    model.component("comp1").physics("shell").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell").feature("ge1").setIndex("name", "P", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").setIndex("equation", "aveop1(-w)-disp", 0, 0);
    model.component("comp1").physics("shell").feature("ge1")
         .setIndex("description", "\u58f3\u4e2d\u5fc3\u7684\u529b", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("shell").feature("ge1").set("SourceTermQuantity", "displacement");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").label("\u540e\u5c48\u66f2\u7814\u7a76");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "R", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "R", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,2e-4,1)", 0);
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.w_center>0.035", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);
    model.sol("sol1").feature("s1").set("reacf", false);
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
    model.result("pg1").setIndex("looplevel", 92, 0);
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
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4\uff1a\u529b vs. \u4f4d\u79fb");
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(4);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "w_center", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "P", 1);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u529b vs. \u4f4d\u79fb");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u58f3\u4e2d\u5fc3\u7684\u5782\u76f4\u4f4d\u79fb (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u58f3\u4e2d\u5fc3\u7684\u529b (N)");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u94f0\u63a5\u5706\u67f1\u58f3\u4f53\u7684\u540e\u5c48\u66f2\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5f53\u8f7d\u8377\u4e0e\u4f4d\u79fb\u90fd\u4e0d\u662f\u5355\u8c03\u589e\u5927\u65f6\u5982\u4f55\u8ffd\u8e2a\u540e\u5c48\u66f2\u8def\u5f84\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u516c\u5f00\u7684\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("postbuckling_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
