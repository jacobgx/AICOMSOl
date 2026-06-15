/*
 * free_cylinder.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:23 by COMSOL 6.3.0.290. */
public class free_cylinder {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("height", "10[m]");
    model.param().descr("height", "\u5706\u67f1\u4f53\u9ad8\u5ea6");
    model.param().set("thic", "0.4[m]");
    model.param().descr("thic", "\u5706\u67f1\u4f53\u539a\u5ea6");
    model.param().set("r_in", "1.8[m]");
    model.param().descr("r_in", "\u5185\u534a\u5f84");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"thic", "height"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"r_in", "0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"8000"});
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"2e11"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5b9e\u4f53");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().dataset("dset1solidrev").set("modenumber", "solid.mk");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u632f\u578b, 3D (solid)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5b9e\u4f53)");
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
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5b9e\u4f53)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormR", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cR \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLR", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cR \u5e73\u79fb", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2});

    model.view("view2").set("showgrid", false);

    model.result("pg2").run();

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);

    model.component("comp1").physics("shell").selection().set(1);
    model.component("comp1").physics("shell").feature("to1").set("d", "thic");
    model.component("comp1").physics("shell").feature("to1").set("OffsetDefinition", "top");

    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().set(1);

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std2").label("\u7814\u7a76 2\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u58f3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2shellshl", "Shell");
    model.result().dataset("dset2shellshl").set("data", "dset2");
    model.result().dataset("dset2shellshl").setIndex("topconst", "1", 6, 1);
    model.result().dataset("dset2shellshl").setIndex("bottomconst", "-1", 6, 1);
    model.result().dataset("dset2shellshl").setIndex("orientation2expr", "shell.nlR", 0);
    model.result().dataset("dset2shellshl").setIndex("displacement2expr", "arr", 0);
    model.result().dataset("dset2shellshl").setIndex("orientation2expr", "shell.nlZ", 1);
    model.result().dataset("dset2shellshl").setIndex("displacement2expr", "arz", 1);
    model.result().dataset("dset2shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset2shellshl").set("seplevels", false);
    model.result().dataset("dset2shellshl").set("resolution", 2);
    model.result().dataset("dset2shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset2shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2shellshl");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").label("\u632f\u578b (shell)");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("data", "dset2shellshl");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.w"});
    model.result().dataset().create("dset2shellrev", "Revolve2D");
    model.result().dataset("dset2shellrev").set("data", "dset2shellshl");
    model.result().dataset("dset2shellrev").set("revangle", 225);
    model.result().dataset("dset2shellrev").set("startangle", -90);
    model.result().dataset("dset2shellrev").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2shellrev");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").label("\u632f\u578b, 3D (shell)");
    model.result("pg4").set("showlegends", false);
    model.result().dataset("dset2shellrev").set("modenumber", "shell.mk");
    model.result("pg4").set("data", "dset2shellrev");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u58f3)");
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
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2\uff0c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u58f3)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormR", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cR \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLR", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cR \u5e73\u79fb", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{2});

    model.view("view3").set("showgrid", false);

    model.result("pg4").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "xz");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature().copy("r1", "geom1/r1");
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").feature().create("rev1", "Revolve");
    model.component("comp2").geom("geom2").feature("rev1").set("workplane", "wp1");
    model.component("comp2").geom("geom2").feature("rev1").selection("input").set("wp1");
    model.component("comp2").geom("geom2").feature("rev1").set("angtype", "specang");
    model.component("comp2").geom("geom2").feature("rev1").set("angle2", 15);
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("solid2").create("pc1", "PeriodicCondition", 2);
    model.component("comp2").physics("solid2").feature("pc1").selection().set(2, 5);
    model.component("comp2").physics("solid2").feature("pc1").set("PeriodicType", "CyclicSymmetry");

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(3);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(2, 7);
    model.component("comp2").mesh("mesh2").run("map1");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 20);
    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").material().create("matlnk3", "Link");

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std3").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std3").feature("eig").setSolveFor("/physics/solid2", true);

    model.component("comp2").common().create("mpf2", "ParticipationFactors");

    model.study("std3").label("\u7814\u7a76 3\uff0c\u4e09\u7ef4\u5b9e\u4f53\u6247\u533a");
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 10);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u632f\u578b (solid2)");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid2.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset4");
    model.result().evaluationGroup("std3EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 3\uff0c\u4e09\u7ef4\u5b9e\u4f53\u6247\u533a)");
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
    model.result().evaluationGroup().create("std3mpf2", "EvaluationGroup");
    model.result().evaluationGroup("std3mpf2").set("data", "dset4");
    model.result().evaluationGroup("std3mpf2")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 3\uff0c\u4e09\u7ef4\u5b9e\u4f53\u6247\u533a)");
    model.result().evaluationGroup("std3mpf2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormX", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormY", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormZ", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormX", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormY", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormZ", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLX", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLY", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLZ", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRX", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRY", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRZ", 11);
    model.result().evaluationGroup("std3mpf2").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std3mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std3mpf2").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{4});
    model.result("pg5").run();
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", "360/15");
    model.result().dataset("sec1").set("include", "manual");
    model.result().dataset("sec1").set("startsector", 18);
    model.result().dataset("sec1").set("sectorsinclude", 15);
    model.result("pg5").run();
    model.result("pg5").set("data", "sec1");
    model.result("pg5").run();

    model.view("view6").set("showgrid", false);

    model.result("pg5").set("looplevel", new int[]{3});
    model.result("pg5").run();

    model.component("comp2").physics().create("shell2", "Shell", "geom2");

    model.study("std1").feature("eig").setSolveFor("/physics/shell2", true);
    model.study("std2").feature("eig").setSolveFor("/physics/shell2", true);
    model.study("std3").feature("eig").setSolveFor("/physics/shell2", true);

    model.component("comp2").physics("shell2").selection().set(1);
    model.component("comp2").physics("shell2").feature("to1").set("d", "thic");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("shell2").feature("to1").set("OffsetDefinition", "top");

    model.component("comp2").coordSystem().create("sys3", "Cylindrical");

    model.component("comp2").physics("shell2").create("pc1", "PeriodicCondition", 1);
    model.component("comp2").physics("shell2").feature("pc1").selection().set(1, 6);
    model.component("comp2").physics("shell2").feature("pc1").set("PeriodicType", "CyclicSymmetry");
    model.component("comp2").physics("shell2").feature("pc1").set("thetaS", "15[deg]");
    model.component("comp2").physics("shell2").feature("pc1").set("TransformationMethod", "sys3");

    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").selection().geom("geom2", 2);
    model.component("comp2").material("matlnk4").selection().set(1);

    model.study().create("std4");
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("solnum", "auto");
    model.study("std4").feature("eig").set("notsolnum", "auto");
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std4").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std4").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std4").feature("eig").setSolveFor("/physics/shell2", true);
    model.study("std4").label("\u7814\u7a76 4\uff0c\u4e09\u7ef4\u58f3\u6247\u533a");
    model.study("std4").feature("eig").set("neigsactive", true);
    model.study("std4").feature("eig").set("neigs", 10);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("dset6shell2shl", "Shell");
    model.result().dataset("dset6shell2shl").set("data", "dset6");
    model.result().dataset("dset6shell2shl").setIndex("topconst", "1", 6, 1);
    model.result().dataset("dset6shell2shl").setIndex("bottomconst", "-1", 6, 1);
    model.result().dataset("dset6shell2shl").setIndex("orientationexpr", "shell2.nlX", 0);
    model.result().dataset("dset6shell2shl").setIndex("displacementexpr", "ar2x", 0);
    model.result().dataset("dset6shell2shl").setIndex("orientationexpr", "shell2.nlY", 1);
    model.result().dataset("dset6shell2shl").setIndex("displacementexpr", "ar2y", 1);
    model.result().dataset("dset6shell2shl").setIndex("orientationexpr", "shell2.nlZ", 2);
    model.result().dataset("dset6shell2shl").setIndex("displacementexpr", "ar2z", 2);
    model.result().dataset("dset6shell2shl").set("distanceexpr", "shell2.z_pos");
    model.result().dataset("dset6shell2shl").set("seplevels", false);
    model.result().dataset("dset6shell2shl").set("resolution", 2);
    model.result().dataset("dset6shell2shl").set("areascalefactor", "shell2.ASF");
    model.result().dataset("dset6shell2shl").set("linescalefactor", "shell2.LSF");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset6shell2shl");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").label("\u632f\u578b (shell2)");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").set("data", "dset6shell2shl");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"shell2.disp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def")
         .set("expr", new String[]{"shell2.u", "shell2.v", "shell2.w"});
    model.result().evaluationGroup().create("std4EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std4EvgFrq").set("data", "dset6");
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4\uff0c\u4e09\u7ef4\u58f3\u6247\u533a)");
    model.result().evaluationGroup("std4EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std4EvgFrq").run();
    model.result().evaluationGroup().create("std4mpf2", "EvaluationGroup");
    model.result().evaluationGroup("std4mpf2").set("data", "dset6");
    model.result().evaluationGroup("std4mpf2")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 4\uff0c\u4e09\u7ef4\u58f3\u6247\u533a)");
    model.result().evaluationGroup("std4mpf2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormX", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormY", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfLnormZ", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormX", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormY", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.pfRnormZ", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLX", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLY", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffLZ", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRX", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRY", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("expr", "mpf2.mEffRZ", 11);
    model.result().evaluationGroup("std4mpf2").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std4mpf2").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std4mpf2").run();
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{4});
    model.result("pg6").run();
    model.result().dataset().create("sec2", "Sector3D");
    model.result().dataset("sec2").set("data", "dset6shell2shl");
    model.result().dataset("sec2").set("sectors", "360/15");
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("startsector", 18);
    model.result().dataset("sec2").set("sectorsinclude", 15);
    model.result("pg6").run();
    model.result("pg6").set("data", "sec2");
    model.result("pg6").run();

    model.view("view7").set("showgrid", false);

    model.result("pg6").set("looplevel", new int[]{3});
    model.result("pg6").run();

    model.component("comp1").physics("solid").prop("Mode2Daxi").set("ModeExtension", true);

    model.study("std1").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("eig").setSolveFor("/physics/shell2", false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{3});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{4});
    model.result("pg2").run();

    model.component("comp1").physics("shell").prop("Mode2Daxi").set("ModeExtension", true);

    model.study("std2").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std2").feature("eig").setSolveFor("/physics/shell2", false);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 10);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{3});
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{4});
    model.result("pg4").run();

    model.study("std3").feature("eig").setSolveFor("/physics/shell2", false);

    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u81ea\u7531\u5706\u67f1\u4f53\u7684\u7279\u5f81\u9891\u7387\u5206\u6790");

    model
         .description("\u672c\u4f8b\u5c06\u81ea\u7531\u5706\u67f1\u7ba1\u7684\u7279\u5f81\u9891\u7387\u4e0e NAFEMS \u57fa\u51c6\u89e3\u8fdb\u884c\u6bd4\u8f83\uff1b\u5176\u4e2d\u4f7f\u7528\u4e09\u4e2a\u4e0d\u540c\u7684\u7269\u7406\u573a\u63a5\u53e3\uff1a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u5f0f\u7684\u201c\u56fa\u4f53\u529b\u5b66\u201d\u548c\u201c\u58f3\u201d\uff0c\u4ee5\u53ca\u4e09\u7ef4\u6a21\u5f0f\u7684\u201c\u56fa\u4f53\u529b\u5b66\u201d\uff0c\u5e76\u91c7\u7528\u5faa\u73af\u5bf9\u79f0\u8fb9\u754c\u6761\u4ef6\u3002\u6b64\u5916\uff0c\u8fd8\u663e\u793a\u5982\u4f55\u8ba1\u7b97\u626d\u66f2\u53d8\u5f62\u7684\u7279\u5f81\u6a21\u6001\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("free_cylinder.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
