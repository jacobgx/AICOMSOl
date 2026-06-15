/*
 * lumped_human_body.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:21 by COMSOL 6.3.0.290. */
public class lumped_human_body {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Biomechanics");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lms", "LumpedMechanicalSystem");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/lms", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m1", "6.15[kg]", "\u8d28\u91cf 1");
    model.param().set("m2", "6[kg]", "\u8d28\u91cf 2");
    model.param().set("m3", "12.58[kg]", "\u8d28\u91cf 3");
    model.param().set("m4", "50.34[kg]", "\u8d28\u91cf 4");
    model.param().set("k1", "6[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5f39\u7c27 1");
    model.param().set("k2", "6[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5f39\u7c27 2");
    model.param().set("k3", "10[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5f39\u7c27 3");
    model.param().set("k4", "10[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5f39\u7c27 4");
    model.param().set("k5", "18[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5f39\u7c27 5");
    model.param().set("c1", "0.3[kN*s/m]", "\u963b\u5c3c\u7cfb\u6570\uff0c\u963b\u5c3c\u5668 1");
    model.param().set("c2", "0.65[kN*s/m]", "\u963b\u5c3c\u7cfb\u6570\uff0c\u963b\u5c3c\u5668 2");
    model.param().set("c4", "1.9[kN*s/m]", "\u963b\u5c3c\u7cfb\u6570\uff0c\u963b\u5c3c\u5668 4");
    model.param().set("ms", "0.3[kg]", "\u8d28\u91cf\uff0c\u978b\u5e95");
    model.param().set("ks", "403[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u978b\u5e95");
    model.param().set("cs", "2170[kN*s/m]", "\u963b\u5c3c\u7cfb\u6570\uff0c\u978b\u5e95");
    model.param().set("kg", "880[kN/m]", "\u5f39\u7c27\u5e38\u6570\uff0c\u5730\u9762");
    model.param().set("ub", "10[mm]", "\u57fa\u5ea7\u6fc0\u52b1");

    model.component("comp1").physics("lms").feature("fix1").active(false);
    model.component("comp1").physics("lms").create("M1", "Mass", -1);
    model.component("comp1").physics("lms").feature("M1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("lms").feature("M1").set("m", "m1");
    model.component("comp1").physics("lms").feature("M1").set("includeForce", false);
    model.component("comp1").physics("lms").create("K1", "Spring", -1);
    model.component("comp1").physics("lms").feature("K1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("K1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("lms").feature("K1").set("k", "k1");
    model.component("comp1").physics("lms").feature("K1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("C1", "Damper", -1);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("lms").feature("C1").set("c", "c1");
    model.component("comp1").physics("lms").feature("C1").set("includeForce", false);
    model.component("comp1").physics("lms").feature("C1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("K2", "Spring", -1);
    model.component("comp1").physics("lms").feature("K2").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("K2").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("lms").feature("K2").set("k", "k2");
    model.component("comp1").physics("lms").feature("K2").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("C2", "Damper", -1);
    model.component("comp1").physics("lms").feature("C2").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("C2").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("lms").feature("C2").set("c", "c2");
    model.component("comp1").physics("lms").feature("C2").set("includeForce", false);
    model.component("comp1").physics("lms").feature("C2").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("M2", "Mass", -1);
    model.component("comp1").physics("lms").feature("M2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("lms").feature("M2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("lms").feature("M2").set("m", "m2");
    model.component("comp1").physics("lms").feature("M2").set("includeForce", false);
    model.component("comp1").physics("lms").create("K3", "Spring", -1);
    model.component("comp1").physics("lms").feature("K3").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("lms").feature("K3").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("lms").feature("K3").set("k", "k3");
    model.component("comp1").physics("lms").feature("K3").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("M3", "Mass", -1);
    model.component("comp1").physics("lms").feature("M3").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("lms").feature("M3").setIndex("Connections", 5, 1, 0);
    model.component("comp1").physics("lms").feature("M3").set("m", "m3");
    model.component("comp1").physics("lms").feature("M3").set("includeForce", false);
    model.component("comp1").physics("lms").create("K4", "Spring", -1);
    model.component("comp1").physics("lms").feature("K4").setIndex("Connections", 5, 0, 0);
    model.component("comp1").physics("lms").feature("K4").setIndex("Connections", 6, 1, 0);
    model.component("comp1").physics("lms").feature("K4").set("k", "k4");
    model.component("comp1").physics("lms").feature("K4").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("C3", "Damper", -1);
    model.component("comp1").physics("lms").feature("C3").tag("C4");
    model.component("comp1").physics("lms").feature("C4").setIndex("Connections", 5, 0, 0);
    model.component("comp1").physics("lms").feature("C4").setIndex("Connections", 6, 1, 0);
    model.component("comp1").physics("lms").feature("C4").set("c", "c4");
    model.component("comp1").physics("lms").feature("C4").set("includeForce", false);
    model.component("comp1").physics("lms").feature("C4").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("M4", "Mass", -1);
    model.component("comp1").physics("lms").feature("M4").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("lms").feature("M4").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("lms").feature("M4").set("m", "m4");
    model.component("comp1").physics("lms").feature("M4").set("includeForce", false);
    model.component("comp1").physics("lms").create("K5", "Spring", -1);
    model.component("comp1").physics("lms").feature("K5").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("lms").feature("K5").setIndex("Connections", 5, 1, 0);
    model.component("comp1").physics("lms").feature("K5").set("k", "k5");
    model.component("comp1").physics("lms").feature("K5").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("K6", "Spring", -1);
    model.component("comp1").physics("lms").feature("K6").tag("Ks");
    model.component("comp1").physics("lms").feature("Ks").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("lms").feature("Ks").setIndex("Connections", "a", 1, 0);
    model.component("comp1").physics("lms").feature("Ks").set("k", "ks");
    model.component("comp1").physics("lms").feature("Ks").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("C5", "Damper", -1);
    model.component("comp1").physics("lms").feature("C5").tag("Cs");
    model.component("comp1").physics("lms").feature("Cs").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("lms").feature("Cs").setIndex("Connections", "a", 1, 0);
    model.component("comp1").physics("lms").feature("Cs").set("c", "cs");
    model.component("comp1").physics("lms").feature("Cs").set("includeForce", false);
    model.component("comp1").physics("lms").feature("Cs").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("M5", "Mass", -1);
    model.component("comp1").physics("lms").feature("M5").tag("Ms");
    model.component("comp1").physics("lms").feature("Ms").setIndex("Connections", "a", 0, 0);
    model.component("comp1").physics("lms").feature("Ms").setIndex("Connections", "b", 1, 0);
    model.component("comp1").physics("lms").feature("Ms").set("m", "ms");
    model.component("comp1").physics("lms").feature("Ms").set("includeForce", false);
    model.component("comp1").physics("lms").create("K6", "Spring", -1);
    model.component("comp1").physics("lms").feature("K6").tag("Kg");
    model.component("comp1").physics("lms").feature("Kg").setIndex("Connections", "b", 0, 0);
    model.component("comp1").physics("lms").feature("Kg").setIndex("Connections", "c", 1, 0);
    model.component("comp1").physics("lms").feature("Kg").set("k", "kg");
    model.component("comp1").physics("lms").feature("Kg").set("includeForce", false);
    model.component("comp1").physics("lms").feature("Kg").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("disp1", "DisplacementNode", -1);
    model.component("comp1").physics("lms").feature("disp1").setIndex("Connections", "c", 0, 0);
    model.component("comp1").physics("lms").feature("disp1").set("up1", "ub");

    model.study("std1").feature("eig").set("eigsolver", "lapack");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u7279\u5f81\u9891\u7387 (lms)");
    model.result().evaluationGroup("eg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature().create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "off");
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"2*lms.freq*pi", "imag(lms.freq)/abs(lms.freq)", "0.5*abs(lms.freq)/imag(lms.freq)"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u89d2\u9891\u7387", "\u963b\u5c3c\u6bd4", "\u54c1\u8d28\u56e0\u5b50"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "off");
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "off");
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "off");
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "off");
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/lms", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "m1", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "kg", 0);
    model.study("std2").feature("param").setIndex("pname", "m1", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "kg", 0);
    model.study("std2").feature("param").setIndex("pname", "kg", 0);
    model.study("std2").feature("param").setIndex("plistarr", "99 359 880", 0);
    model.study("std2").feature("param").setIndex("punit", "kN/m", 0);
    model.study("std2").feature("freq").set("plist", "range(0.01,0.01,100)");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f (M1)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Displacement");
    model.result("pg1").set("ylabel", "Displacement");
    model.result("pg1").feature().create("glob1", "Global");
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M1.uAmp"});
    model.result("pg1").feature("glob1").set("hasmultilevel", "off");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("linemarker", "cycle");
    model.result("pg1").feature("glob1").set("hasmultilevel", "off");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("hasmultilevel", "off");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("hasmultilevel", "off");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("hasmultilevel", "off");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("glob1").set("showunitcombo", "off");
    model.result("pg1").feature("glob1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u4f4d\u79fb\u76f8\u4f4d (M1)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Phase");
    model.result("pg2").set("ylabel", "Phase");
    model.result("pg2").feature().create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.M1.uPhase"});
    model.result("pg2").feature("glob1").set("hasmultilevel", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("hasmultilevel", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("hasmultilevel", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("hasmultilevel", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("hasmultilevel", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg2").feature("glob1").set("showunitcombo", "off");
    model.result("pg2").feature("glob1").set("data", "parent");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u529b\u5927\u5c0f (K1)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "Force");
    model.result("pg3").set("ylabel", "Force");
    model.result("pg3").feature().create("glob1", "Global");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K1.fAmp"});
    model.result("pg3").feature("glob1").set("hasmultilevel", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("hasmultilevel", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("hasmultilevel", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("hasmultilevel", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("hasmultilevel", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg3").feature("glob1").set("showunitcombo", "off");
    model.result("pg3").feature("glob1").set("data", "parent");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u529b\u76f8\u4f4d (K1)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Phase");
    model.result("pg4").set("ylabel", "Phase");
    model.result("pg4").feature().create("glob1", "Global");
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K1.fPhase"});
    model.result("pg4").feature("glob1").set("hasmultilevel", "off");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("glob1").set("showunitcombo", "off");
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("hasmultilevel", "off");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("glob1").set("showunitcombo", "off");
    model.result("pg4").feature("glob1").set("hasmultilevel", "off");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("glob1").set("showunitcombo", "off");
    model.result("pg4").feature("glob1").set("hasmultilevel", "off");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("glob1").set("showunitcombo", "off");
    model.result("pg4").feature("glob1").set("hasmultilevel", "off");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("glob1").set("showunitcombo", "off");
    model.result("pg4").feature("glob1").set("data", "parent");
    model.result("pg3").label("\u529b\u5927\u5c0f (lms)");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K2.fAmp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u5f39\u7c27\u529b\u5927\u5c0f (K2)"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.K2.fAmp"});
    model.result("pg4").label("\u529b\u76f8\u4f4d (lms)");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K2.fPhase"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K2)"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K1.fPhase", "lms.K2.fPhase"});
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f (lms)");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M2.uAmp"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u5927\u5c0f (M1)", "\u4f4d\u79fb\u5927\u5c0f (M2)"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M1.uAmp", "lms.M2.uAmp"});
    model.result("pg2").label("\u4f4d\u79fb\u76f8\u4f4d (lms)");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.M2.uPhase"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u76f8\u4f4d (M1)", "\u4f4d\u79fb\u76f8\u4f4d (M2)"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.M1.uPhase", "lms.M2.uPhase"});
    model.result("pg3").label("\u529b\u5927\u5c0f (lms) 1");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K3.fAmp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u5f39\u7c27\u529b\u5927\u5c0f (K2)", "\u5f39\u7c27\u529b\u5927\u5c0f (K3)"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.K2.fAmp", "lms.K3.fAmp"});
    model.result("pg4").label("\u529b\u76f8\u4f4d (lms) 1");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K3.fPhase"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K2)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K3)"});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.K2.fPhase", "lms.K3.fPhase"});
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f (lms) 1");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M3.uAmp"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u5927\u5c0f (M1)", "\u4f4d\u79fb\u5927\u5c0f (M2)", "\u4f4d\u79fb\u5927\u5c0f (M3)"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M1.uAmp", "lms.M2.uAmp", "lms.M3.uAmp"});
    model.result("pg2").label("\u4f4d\u79fb\u76f8\u4f4d (lms) 1");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.M3.uPhase"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u76f8\u4f4d (M1)", "\u4f4d\u79fb\u76f8\u4f4d (M2)", "\u4f4d\u79fb\u76f8\u4f4d (M3)"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"lms.M1.uPhase", "lms.M2.uPhase", "lms.M3.uPhase"});
    model.result("pg3").label("\u529b\u5927\u5c0f (lms)");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K4.fAmp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u5f39\u7c27\u529b\u5927\u5c0f (K2)", "\u5f39\u7c27\u529b\u5927\u5c0f (K3)", "\u5f39\u7c27\u529b\u5927\u5c0f (K4)"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"lms.K1.fAmp", "lms.K2.fAmp", "lms.K3.fAmp", "lms.K4.fAmp"});
    model.result("pg4").label("\u529b\u76f8\u4f4d (lms)");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K4.fPhase"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K2)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K3)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K4)"});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.K2.fPhase", "lms.K3.fPhase", "lms.K4.fPhase"});
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f (lms)");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.M4.uAmp"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u5927\u5c0f (M1)", "\u4f4d\u79fb\u5927\u5c0f (M2)", "\u4f4d\u79fb\u5927\u5c0f (M3)", "\u4f4d\u79fb\u5927\u5c0f (M4)"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"lms.M1.uAmp", "lms.M2.uAmp", "lms.M3.uAmp", "lms.M4.uAmp"});
    model.result("pg2").label("\u4f4d\u79fb\u76f8\u4f4d (lms)");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.M4.uPhase"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u76f8\u4f4d (M1)", "\u4f4d\u79fb\u76f8\u4f4d (M2)", "\u4f4d\u79fb\u76f8\u4f4d (M3)", "\u4f4d\u79fb\u76f8\u4f4d (M4)"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"lms.M1.uPhase", "lms.M2.uPhase", "lms.M3.uPhase", "lms.M4.uPhase"});
    model.result("pg3").label("\u529b\u5927\u5c0f (lms) 1");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.K5.fAmp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u5f39\u7c27\u529b\u5927\u5c0f (K2)", "\u5f39\u7c27\u529b\u5927\u5c0f (K3)", "\u5f39\u7c27\u529b\u5927\u5c0f (K4)", "\u5f39\u7c27\u529b\u5927\u5c0f (K5)"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"lms.K1.fAmp", "lms.K2.fAmp", "lms.K3.fAmp", "lms.K4.fAmp", "lms.K5.fAmp"});
    model.result("pg4").label("\u529b\u76f8\u4f4d (lms) 1");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.K5.fPhase"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K2)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K3)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K4)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K5)"});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.K2.fPhase", "lms.K3.fPhase", "lms.K4.fPhase", "lms.K5.fPhase"});
    model.result("pg3").label("\u529b\u5927\u5c0f (lms)");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.Ks.fAmp"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u5f39\u7c27\u529b\u5927\u5c0f (K2)", "\u5f39\u7c27\u529b\u5927\u5c0f (K3)", "\u5f39\u7c27\u529b\u5927\u5c0f (K4)", "\u5f39\u7c27\u529b\u5927\u5c0f (K5)", "\u5f39\u7c27\u529b\u5927\u5c0f (Ks)"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"lms.K1.fAmp", "lms.K2.fAmp", "lms.K3.fAmp", "lms.K4.fAmp", "lms.K5.fAmp", "lms.Ks.fAmp"});
    model.result("pg4").label("\u529b\u76f8\u4f4d (lms)");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"lms.Ks.fPhase"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K2)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K3)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K4)", "\u5f39\u7c27\u529b\u76f8\u4f4d (K5)", "\u5f39\u7c27\u529b\u76f8\u4f4d (Ks)"});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.K2.fPhase", "lms.K3.fPhase", "lms.K4.fPhase", "lms.K5.fPhase", "lms.Ks.fPhase"});
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f (lms) 1");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").set("expr", new String[]{"lms.Ms.uAmp"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u5927\u5c0f (M1)", "\u4f4d\u79fb\u5927\u5c0f (M2)", "\u4f4d\u79fb\u5927\u5c0f (M3)", "\u4f4d\u79fb\u5927\u5c0f (M4)", "\u4f4d\u79fb\u5927\u5c0f (Ms)"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"lms.M1.uAmp", "lms.M2.uAmp", "lms.M3.uAmp", "lms.M4.uAmp", "lms.Ms.uAmp"});
    model.result("pg2").label("\u4f4d\u79fb\u76f8\u4f4d (lms) 1");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"lms.Ms.uPhase"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb\u76f8\u4f4d (M1)", "\u4f4d\u79fb\u76f8\u4f4d (M2)", "\u4f4d\u79fb\u76f8\u4f4d (M3)", "\u4f4d\u79fb\u76f8\u4f4d (M4)", "\u4f4d\u79fb\u76f8\u4f4d (Ms)"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"lms.M1.uPhase", "lms.M2.uPhase", "lms.M3.uPhase", "lms.M4.uPhase", "lms.Ms.uPhase"});
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("ylabel", "\u4f4d\u79fb (m)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").set("xlog", true);
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("ylabel", "\u76f8\u4f4d (rad)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("ylabel", "\u529b (N)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg3").set("xlog", true);
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("ylabel", "\u76f8\u4f4d (rad)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f20\u9012\u7387 (M2)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"lms.M2.uAmp"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u4f4d\u79fb\u5927\u5c0f (M2)"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg5").feature("glob1").setIndex("expr", "comp1.lms.M2.uAmp/ub", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u4f20\u9012\u7387", 0);
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result("pg5").set("xlog", true);
    model.result("pg1").run();

    model.title("\u4eba\u4f53\u96c6\u603b\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u5177\u6709\u4e94\u4e2a\u81ea\u7531\u5ea6\u7684\u4eba\u4f53\u96c6\u603b\u6a21\u578b\uff0c\u5176\u4e2d\u5305\u542b\u4eba\u4f53\u6240\u7a7f\u978b\u5b50\u4e0e\u5730\u9762\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u4f7f\u7528\u201c\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u201d\u63a5\u53e3\u7684\u201c\u8d28\u91cf\u201d\u3001\u201c\u5f39\u7c27\u201d\u548c\u201c\u963b\u5c3c\u5668\u201d\u8282\u70b9\u4e3a\u4eba\u4f53\uff08\u5305\u62ec\u978b\u5b50\uff09\u548c\u5730\u9762\u5efa\u6a21\u3002\u9996\u5148\uff0c\u6267\u884c\u7279\u5f81\u9891\u7387\u7814\u7a76\u6765\u786e\u5b9a\u7cfb\u7edf\u7684\u56fa\u6709\u9891\u7387\uff0c\u7136\u540e\u6267\u884c\u9891\u7387\u54cd\u5e94\u5206\u6790\u6765\u8ba1\u7b97\u6307\u5b9a\u57fa\u5ea7\u6fc0\u52b1\u7684\u7cfb\u7edf\u54cd\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("lumped_human_body.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
