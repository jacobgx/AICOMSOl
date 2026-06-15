/*
 * coupled_vibrations_manual.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:58 by COMSOL 6.3.0.290. */
public class coupled_vibrations_manual {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Acoustic-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 38);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 255);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("shell").selection().set(3);
    model.component("comp1").physics("shell").feature("to1").set("d", "0.38[mm]");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(2, 3, 7, 10);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"343"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"2.1e11"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 10);
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.study("std1").label("\u7ed3\u6784\u5206\u6790");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 20);
    model.study("std1").feature("eig").set("shift", "500");
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", false);
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
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7ed3\u6784\u5206\u6790)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b\uff0c\u7ed3\u6784\u5206\u6790");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("scenelight", false);

    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("ftplistmethod", "manual");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std2").label("\u58f0\u5b66\u5206\u6790");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 20);
    model.study("std2").feature("eig").set("shift", "500");
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b (acpr)");
    model.result("pg2").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg2").feature("con1").set("colortable", "Wave");
    model.result("pg2").feature("con1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg4").feature("iso1").set("number", "10");
    model.result("pg4").feature("iso1").set("colortable", "Wave");
    model.result("pg4").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u58f0\u5b66\u5206\u6790)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\uff0c\u58f0\u5b66\u5206\u6790\uff0c\u7b49\u503c\u9762");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{2});
    model.result("pg4").run();

    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().set(3);
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "ForceDefArea");
    model.component("comp1").physics("shell").feature("fl1")
         .set("forceDeformedArea_src", "root.comp1.acpr.FAcoPerAreax");
    model.component("comp1").physics("acpr").create("nacc1", "NormalAcceleration", 2);
    model.component("comp1").physics("acpr").feature("nacc1").selection().set(3);
    model.component("comp1").physics("acpr").feature("nacc1").set("Type", "acc");
    model.component("comp1").physics("acpr").feature("nacc1").set("acc_src", "root.comp1.shell.us_ttx");

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("ftplistmethod", "manual");
    model.study("std3").feature("eig").set("chkeigregion", true);
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("linpsolnum", "auto");
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std3").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std3").label("\u8026\u5408\u5206\u6790");
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 20);
    model.study("std3").feature("eig").set("shift", "500");
    model.study("std3").feature("eig").set("eigwhich", "lr");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("dset3shellshl", "Shell");
    model.result().dataset("dset3shellshl").set("data", "dset3");
    model.result().dataset("dset3shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset3shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset3shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset3shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset3shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset3shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset3shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset3shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset3shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset3shellshl").set("seplevels", false);
    model.result().dataset("dset3shellshl").set("resolution", 2);
    model.result().dataset("dset3shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset3shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3shellshl");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u632f\u578b (shell)");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").set("data", "dset3shellshl");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset3");
    model.result().evaluationGroup("std3EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u8026\u5408\u5206\u6790)");
    model.result().evaluationGroup("std3EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std3EvgFrq").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg6").feature("surf1").set("colortable", "Wave");
    model.result("pg6").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b (acpr) 1");
    model.result("pg6").feature("surf1").set("colortable", "WaveLight");
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg6").feature("con1").set("colortable", "Wave");
    model.result("pg6").feature("con1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").create("iso1", "Isosurface");
    model.result("pg8").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg8").feature("iso1").set("number", "10");
    model.result("pg8").feature("iso1").set("colortable", "Wave");
    model.result("pg8").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg5").run();
    model.result("pg5").label("\u632f\u578b\uff0c\u8026\u5408\u5206\u6790");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("data", "dset3");
    model.result("pg5").feature("slc1").set("solutionparams", "parent");
    model.result("pg5").feature("slc1").set("expr", "acpr.p_t");
    model.result("pg5").feature("slc1").set("quickxnumber", 1);
    model.result("pg5").feature("slc1").set("colortable", "Wave");
    model.result("pg5").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg9", "pg5");
    model.result("pg9").run();
    model.result("pg9").label("\u8026\u5408\u6548\u5e94");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").set("expr", "pz");
    model.result("pg9").feature("slc1").set("unit", "Pa/m");
    model.result("pg9").feature("slc1").set("colorscalemode", "linear");
    model.result("pg9").feature("slc1").set("colortable", "Cividis");
    model.result("pg9").run();
    model.result().evaluationGroup("std3EvgFrq").run();
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b\uff0c\u58f0\u5b66\u5206\u6790");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b\u7ea7\uff0c\u58f0\u5b66\u5206\u6790");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u538b\uff0c\u8026\u5408\u5206\u6790");
    model.result("pg7").run();
    model.result("pg7").label("\u58f0\u538b\u7ea7\uff0c\u8026\u5408\u5206\u6790");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u538b\uff0c\u8026\u5408\u5206\u6790\uff0c\u7b49\u503c\u9762");
    model.result("pg5").run();

    model.title("\u5bc6\u5c01\u5145\u6c14\u5706\u7b52\u5706\u7247\u76d6\u7684\u632f\u52a8");

    model
         .description("\u5bf9\u7b52\u578b\u8154\u5706\u7247\u76d6\u7684\u632f\u52a8\u6a21\u6001\u7684\u7279\u5f81\u9891\u7387\u5206\u6790\u8868\u660e\uff0c\u5706\u7247\u76d6\u7684\u5f2f\u66f2\u6a21\u6001\u4e0e\u5706\u7b52\u4e2d\u7684\u58f0\u5b66\u6a21\u6001\u4e4b\u95f4\u7684\u8026\u5408\u6709\u7740\u91cd\u8981\u7684\u5f71\u54cd\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u534a\u89e3\u6790\u548c\u5b9e\u9a8c\u6570\u636e\u5177\u6709\u9ad8\u5ea6\u76f8\u5173\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("coupled_vibrations_manual.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
