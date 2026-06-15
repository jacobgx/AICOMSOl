/*
 * postbuckling_shell_arclength.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class postbuckling_shell_arclength {

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

    model.label("postbuckling_shell.mph");

    model.result("pg2").run();
    model.result().table().create("tbl1", "Table");
    model.result().evaluationGroup("eg1").copyToTable("tbl1");
    model.result().table("tbl1").label("\u8868\u683c\uff1a\u53c2\u8003\u7ed3\u679c");

    model.study().remove("std1");

    model.param().rename("disp", "p");
    model.param().descr("p", "\u65e0\u91cf\u7eb2\u8f7d\u8377");
    model.param().set("p_scaleFactor", "1E5[N]");
    model.param().descr("p_scaleFactor", "\u8f7d\u8377\u6bd4\u4f8b\u56e0\u5b50");
    model.param().set("P", "p*p_scaleFactor");
    model.param().descr("P", "\u603b\u8f7d\u8377");

    model.component("comp1").cpl().remove("aveop1");

    model.component("comp1").physics("shell").feature().remove("ge1");

//    Started method call postBucklingWithArclength

    model.param().set("p", "0");

    model.study().create("std_in");
    model.study("std_in").label("\u521d\u59cb\u731c\u6d4b\u503c\u5360\u4f4d\u7814\u7a76");
    model.study("std_in").create("stat_in", "Stationary");
    model.study("std_in").feature("stat_in").set("geometricNonlinearity", true);

    model.sol().create("sol_in");
    model.sol("sol_in").createAutoSequence("std_in");
    model.sol("sol_in").attach("std_in");

    model.study().create("std_c");
    model.study("std_c").label("\u540e\u5c48\u66f2\u7814\u7a76");
    model.study("std_c").create("stat_c", "Stationary");
    model.study("std_c").feature("stat_c").set("geometricNonlinearity", true);

    model.sol().create("sol_c");
    model.sol("sol_c").createAutoSequence("std_c");
    model.sol("sol_c").attach("std_c");

    model.study().create("std_p");
    model.study("std_p").label("\u53c2\u6570\u5316\u89e3\u5360\u4f4d\u7814\u7a76");
    model.study("std_p").create("stat_p", "Stationary");

    model.sol().create("sol_p");
    model.sol("sol_p").createAutoSequence("std_p");
    model.sol("sol_p").attach("std_p");

    model.study("std_p").feature("stat_p").set("geometricNonlinearity", true);
    model.study("std_p").feature("stat_p").set("useparam", true);
    model.study("std_p").feature("stat_p").setIndex("pname", "p", 0);
    model.study("std_p").feature("stat_p").setIndex("plistarr", "1 2", 0);
    model.study("std_p").feature("stat_p").setIndex("punit", "N", 0);

    model.result().dataset().create("dset_c", "Solution");
    model.result().dataset("dset_c").set("solution", "sol_c");
    model.result().evaluationGroup().create("eg_c", "EvaluationGroup");
    model.result().evaluationGroup("eg_c").set("data", "dset_c");
    model.result().evaluationGroup("eg_c").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg_c").feature("gev1").setIndex("expr", "w_center", 0);
    model.result().evaluationGroup("eg_c").set("includeparameters", false);
    model.result().dataset().remove("dset_c");

//    Finished method call postBucklingWithArclength

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "shell.misesGp");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4\uff1a\u529b vs. \u4f4d\u79fb");
    model.result().evaluationGroup("eg1").set("data", "dset3");
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
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg2").feature("tblp1").set("linemarker", "circle");
    model.result("pg2").feature("tblp1").set("markerpos", "interp");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u5f27\u957f\u6cd5", 0);
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("source", "table");
    model.result("pg2").feature("tblp2").set("linemarker", "diamond");
    model.result("pg2").feature("tblp2").set("markers", 12);
    model.result("pg2").feature("tblp2").setIndex("legends", "\u5168\u5c40\u65b9\u7a0b\u6cd5", 0);
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u57fa\u4e8e\u589e\u91cf\u5f27\u957f\u6cd5\u7684\u540e\u5c48\u66f2\u5206\u6790");

    model
         .description("\u5bf9\u4e8e\u7ec6\u957f\u7ed3\u6784\uff0c\u5f53\u5de5\u4f5c\u8f7d\u8377\u9ad8\u4e8e\u4e34\u754c\u6781\u9650\u65f6\uff0c\u5c48\u66f2\u53ef\u80fd\u5f15\u53d1\u4e25\u91cd\u7684\u4e0d\u7a33\u5b9a\u60c5\u51b5\u3002\u7814\u7a76\u8fd9\u79cd\u7ed3\u6784\u5728\u8d85\u51fa\u4e34\u754c\u5c48\u66f2\u8f7d\u8377\u65f6\u7684\u7279\u6027\u81f3\u5173\u91cd\u8981\uff0c\u8fd9\u79f0\u4e3a\u540e\u5c48\u66f2\u5206\u6790\u3002\u5728\u540e\u5c48\u66f2\u5206\u6790\u4e2d\u8ddf\u8e2a\u5e73\u8861\u8def\u5f84\u5e76\u4e0d\u5bb9\u6613\uff0c\u56e0\u4e3a\u8fd9\u4f1a\u5bfc\u81f4\u6781\u9650\u70b9\u7b49\u6570\u503c\u56f0\u96be\u3002\u5728\u8fd9\u79cd\u60c5\u51b5\u4e0b\uff0c\u91c7\u7528\u5f27\u957f\u6cd5\u8ddf\u8e2a\u5e73\u8861\u8def\u5f84\u662f\u4e00\u4e2a\u5e7f\u6cdb\u8ba4\u53ef\u7684\u7b56\u7565\u3002\n\n\u5728\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u5c06\u589e\u91cf\u5f27\u957f\u6cd5\u4e0e\u4e09\u6b21\u5916\u63a8\u6cd5\u76f8\u7ed3\u5408\uff0c\u7528\u4e8e\u5bf9\u4e2d\u5fc3\u627f\u53d7\u70b9\u8f7d\u8377\u7684\u94f0\u63a5\u5706\u67f1\u9762\u677f\u8fdb\u884c\u540e\u5c48\u66f2\u5206\u6790\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u7c7b\u4f3c\u793a\u4f8b\uff08\u5176\u4e2d\u4f7f\u7528\u57fa\u4e8e\u4f4d\u79fb\u5355\u8c03\u9012\u589e\u7684\u5168\u5c40\u65b9\u7a0b\u65b9\u6cd5\uff09\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol_in").clearSolutionData();
    model.sol("sol_c").clearSolutionData();
    model.sol("sol_p").clearSolutionData();
    model.sol("sol_1").clearSolutionData();
    model.sol("sol_2").clearSolutionData();
    model.sol("sol_3").clearSolutionData();
    model.sol("sol_4").clearSolutionData();

    model.label("postbuckling_shell_arclength.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
