/*
 * membrane_varying_thickness.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:31 by COMSOL 6.3.0.290. */
public class membrane_varying_thickness {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);

    model.param().label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ri", "10[mm]", "\u5706\u67f1\u4f53\u534a\u5f84");
    model.param().set("Hi", "80[mm]", "\u5706\u67f1\u4f53\u9ad8\u5ea6");
    model.param().set("thm", "0.1[mm]", "\u5706\u67f1\u4f53\u5e73\u5747\u539a\u5ea6");
    model.param().set("M", "0.5", "\u539a\u5ea6\u53c2\u6570");
    model.param().set("C1", "0.2111[MPa]", "Mooney-Rivlin \u6750\u6599\u53c2\u6570 C1");
    model.param().set("C2", "0.01*C1", "Mooney-Rivlin \u6750\u6599\u53c2\u6570 C2");
    model.param().set("rho_w", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("z_w", "0[mm]", "\u6c34\u67f1\u9ad8\u5ea6");
    model.param().set("stretch", "2[1]", "\u5916\u52a0\u4f38\u957f\u7387");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 1);
    model.component("comp1").func("step1").set("smooth", 0.008);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("th", "thm*(2*(1-M)*Z/Hi+M)", "\u539a\u5ea6\u53d8\u5316");
    model.component("comp1").variable("var1").set("w_appl", "(stretch-1)*Hi", "\u8f74\u5411\u4f4d\u79fb");
    model.component("comp1").variable("var1")
         .set("stch1", "sqrt(mbrn.Cel11)", "\u7b2c\u4e00\u4e3b\u4f38\u957f\u7387");
    model.component("comp1").variable("var1")
         .set("stch2", "sqrt(mbrn.Cel22)", "\u7b2c\u4e8c\u4e3b\u4f38\u957f\u7387");
    model.component("comp1").variable("var1")
         .set("stch3", "sqrt(mbrn.Cel33)", "\u7b2c\u4e09\u4e3b\u4f38\u957f\u7387");
    model.component("comp1").variable("var1")
         .set("WF", "C1*(stch1^2+stch2^2+1/(stch1^2*stch2^2)-3)+C2*(1/stch1^2+1/stch2^2+stch1^2*stch2^2-3)", "\u5168\u5e94\u53d8\u80fd\uff0cMooney-Rivlin");
    model.component("comp1").variable("var1")
         .set("WR", "C1*(stch1^2+2/stch1-3)+C2*(1/stch1^2+2*stch1-3)", "\u677e\u5f1b\u5e94\u53d8\u80fd\uff0cMooney-Rivlin");
    model.component("comp1").variable("var1")
         .set("WT", "WF*step1(stch2*sqrt(stch1))+WR*(1-step1(stch2*sqrt(stch1)))", "\u603b\u5e94\u53d8\u80fd\uff0cMooney-Rivlin");
    model.component("comp1").variable("var1").set("P", "rho_w*g_const*z_w*(z_w>(Z+w))", "\u6d41\u4f53\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("iswrinkled", "(stch2*sqrt(stch1)<=1)", "\u8936\u76b1\uff08\u677e\u5f1b\u5e94\u53d8\u80fd\u6cd5\uff09");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"Ri", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"Ri", "Hi"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbrn").feature("to1").set("d", "th");
    model.component("comp1").physics("mbrn").create("hmm1", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("hmm1")
         .label("\u8d85\u5f39\u6027\u6750\u6599\uff08\u4fee\u6b63\u53d8\u5f62\u68af\u5ea6\uff09");
    model.component("comp1").physics("mbrn").feature("hmm1").set("MaterialModel", "MooneyRivlin");
    model.component("comp1").physics("mbrn").feature("hmm1").set("Compressibility_MooneyRivlin", "Incompressible");
    model.component("comp1").physics("mbrn").feature("hmm1").set("C10_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm1").set("C10", "C1");
    model.component("comp1").physics("mbrn").feature("hmm1").set("C01_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("hmm1").set("C01", "C2");
    model.component("comp1").physics("mbrn").feature("hmm1").create("wr1", "Wrinkling", 1);
    model.component("comp1").physics("mbrn").feature("hmm1").feature("wr1").set("termination", "steporresi");
    model.component("comp1").physics("mbrn").create("hmm2", "HyperelasticModel", 1);
    model.component("comp1").physics("mbrn").feature("hmm2").selection().all();
    model.component("comp1").physics("mbrn").feature("hmm2")
         .label("\u8d85\u5f39\u6027\u6750\u6599\uff08\u677e\u5f1b\u5e94\u53d8\u80fd\uff09");
    model.component("comp1").physics("mbrn").feature("hmm2").set("MaterialModel", "userDefined");
    model.component("comp1").physics("mbrn").feature("hmm2").set("Ws", "WT");
    model.component("comp1").physics("mbrn").create("weak1", "WeakContribution", 1);
    model.component("comp1").physics("mbrn").feature("weak1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("weak1").set("weakExpression", "(-1+mbrn.Jel)*test(mbrn.unn)");
    model.component("comp1").physics("mbrn").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("disp2", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp2")
         .label("\u6307\u5b9a\u4f4d\u79fb\uff08\u9884\u62c9\u4f38\uff09");
    model.component("comp1").physics("mbrn").feature("disp2").selection().set(2);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("U0", "w_appl", 2);
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 1);
    model.component("comp1").physics("mbrn").feature("fl1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "P");
    model.component("comp1").physics("mbrn").feature("fl1")
         .label("\u9762\u8f7d\u8377\uff08\u6d41\u4f53\u538b\u529b\uff09");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff08\u4fee\u6b63\u53d8\u5f62\u68af\u5ea6\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").label("\u9884\u62c9\u4f38");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mbrn/hmm2", "mbrn/weak1", "mbrn/fl1"});
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u586b\u5145\u6c34");
    model.study("std1").feature("stat2").set("useadvanceddisable", true);
    model.study("std1").feature("stat2").set("disabledphysics", new String[]{"mbrn/hmm2", "mbrn/weak1"});
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "Ri", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "Ri", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "z_w", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "range(0,5,80)", 0);
    model.study("std1").feature("stat2").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").label("\u7814\u7a76\uff08\u677e\u5f1b\u5e94\u53d8\u80fd\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").label("\u9884\u62c9\u4f38");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"mbrn/fl1"});
    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").label("\u586b\u5145\u6c34");
    model.study("std2").feature("stat2").set("useparam", true);
    model.study("std2").feature("stat2").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "m", 0);
    model.study("std2").feature("stat2").setIndex("pname", "Ri", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "m", 0);
    model.study("std2").feature("stat2").setIndex("pname", "z_w", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "range(0,5,80)", 0);
    model.study("std2").feature("stat2").setIndex("punit", "mm", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("dset1mbrnrev", "Revolve2D");
    model.result().dataset("dset1mbrnrev").set("data", "dset1");
    model.result().dataset("dset1mbrnrev").set("revangle", 225);
    model.result().dataset("dset1mbrnrev").set("startangle", -90);
    model.result().dataset("dset1mbrnrev").set("hasspacevars", true);
    model.result().dataset("dset1mbrnrev").set("defaultPlotIDs", new String[]{"stress3D|mbrn", "faceLoads|mbrn"});
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u5e94\u529b, 3D (mbrn)");
    model.result("pg1").set("data", "dset1mbrnrev");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"mbrn.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("surf1").feature("def").set("descractive", true);
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").label("\u5e94\u529b, 3D (mbrn)");
    model.result("pg1").run();
    model.result().dataset("dset1mbrnrev").set("startangle", 0);
    model.result().dataset("dset1mbrnrev").set("revangle", 360);
    model.result("pg1").run();
    model.result("pg1").label("\u8936\u76b1\u533a\u57df");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("solutionintitle", false);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("relpadding", 2);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "mbrn.iswrinkled");
    model.result("pg1").feature("surf1").set("descr", "\u5e26\u8936\u76b1");
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("smooth", "none");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset1mbrnrev");
    model.result("pg1").feature("surf2").setIndex("looplevel", 5, 0);
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").setIndex("looplevel", 9, 0);
    model.result("pg1").feature().duplicate("surf4", "surf3");
    model.result("pg1").feature("surf4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 5, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -1, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1")
         .setIndex("localtablematrix", "\\[\\textrm{z}_\\textrm{w}=\\textrm{0[mm]}\\]", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 55, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -1, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1")
         .setIndex("localtablematrix", "\\[\\textrm{z}_\\textrm{w}=\\textrm{20[mm]}\\]", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 120, 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -1, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1")
         .setIndex("localtablematrix", "\\[\\textrm{z}_\\textrm{w}=\\textrm{40[mm]}\\]", 2, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 180, 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -1, 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 3, 2);
    model.result("pg1").feature("tlan1")
         .setIndex("localtablematrix", "\\[\\textrm{z}_\\textrm{w}=\\textrm{80[mm]}\\]", 3, 3);
    model.result("pg1").feature("tlan1").set("latexmarkup", true);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.view("view3").set("showgrid", false);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").setIndex("looplevel", 17, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u5e94\u529b, 3D (mbrn)");
    model.result("pg2").set("data", "dset1mbrnrev");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"mbrn.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg2").label("\u5e94\u529b, 3D (mbrn)");
    model.result("pg2").run();
    model.result("pg2").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbrn.sp1");
    model.result("pg2").run();

    model.view("view4").set("showgrid", false);

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbrn.sp2");
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u4fee\u6b63\u53d8\u5f62\u68af\u5ea6");
    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("\u677e\u5f1b\u5e94\u53d8\u80fd");

    model.result().dataset().duplicate("dset1mbrnrev1", "dset1mbrnrev");
    model.result().dataset("dset1mbrnrev1").set("data", "dset3");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "iswrinkled");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset1mbrnrev1");
    model.result("pg4").feature("surf2").set("expr", "iswrinkled");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("data", "dset1mbrnrev1");
    model.result("pg4").feature("surf3").set("expr", "iswrinkled");
    model.result("pg4").feature("surf4").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").set("data", "dset1mbrnrev1");
    model.result("pg4").feature("surf4").set("expr", "iswrinkled");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset1mbrnrev1");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("data", "dset1mbrnrev1");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("data", "dset1mbrnrev1");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u9884\u62c9\u4f38\u540e\u7684\u7b2c\u4e09\u4e3b\u5e94\u53d8");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u5e94\u53d8\u5f20\u91cf\uff0c33 \u5206\u91cf (1)");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1").set("expr", "mbrn.el33");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "Z");
    model.result("pg7").feature("lngr1").set("linemarker", "cycle");
    model.result("pg7").feature("lngr1").set("markerpos", "interp");
    model.result("pg7").feature("lngr1").set("markers", 6);
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u4fee\u6b63\u53d8\u5f62\u68af\u5ea6", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("data", "dset4");
    model.result("pg7").feature("lngr2").set("titletype", "none");
    model.result("pg7").feature("lngr2").set("markers", 8);
    model.result("pg7").feature("lngr2").setIndex("legends", "\u677e\u5f1b\u5e94\u53d8\u80fd", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u9884\u62c9\u4f38\u540e\u7684\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg8").set("ylabel", "\u7b2c\u4e00\u4e3b\u5e94\u529b (MPa)");
    model.result("pg8").set("legendpos", "upperright");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "mbrn.sp1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "mbrn.sp1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u9884\u62c9\u4f38\u540e\u7684\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg9").set("ylabel", "\u7b2c\u4e8c\u4e3b\u5e94\u529b (MPa)");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "mbrn.sp2");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "mbrn.sp2");
    model.result("pg9").run();
    model.result("pg1").run();

    model.title("\u4e0d\u540c\u539a\u5ea6\u5706\u7b52\u819c\u7684\u8d77\u76b1");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7531\u4e0d\u53ef\u538b\u7f29 Mooney-Rivlin \u6750\u6599\u5236\u6210\u7684\u4e0d\u5747\u5300\u539a\u819c\u7684\u8d77\u76b1\u73b0\u8c61\u3002\u5728\u8fdb\u884c\u9884\u62c9\u4f38\u548c\u5145\u6c14\u64cd\u4f5c\u65f6\uff0c\u819c\u7684\u67d0\u4e9b\u90e8\u5206\u4f1a\u51fa\u73b0\u8936\u76b1\uff0c\u800c\u8fd9\u4e9b\u8936\u76b1\u5728\u4e00\u5b9a\u5185\u538b\u4e0b\u4f1a\u9010\u6e10\u6d88\u5931\u3002\n\n\u672c\u4f8b\u5c06\u91c7\u7528\u53d8\u5f62\u68af\u5ea6\u6cd5\u7684\u7ed3\u679c\u4e0e\u91c7\u7528\u677e\u5f1b\u5e94\u53d8\u80fd\u6cd5\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("membrane_varying_thickness.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
