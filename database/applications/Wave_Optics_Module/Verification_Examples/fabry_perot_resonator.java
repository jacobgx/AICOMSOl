/*
 * fabry_perot_resonator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class fabry_perot_resonator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1[um]", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("h_cav", "2[mm]", "\u57df\u9ad8\u5ea6");
    model.param().set("l_in", "5[cm]", "\u8154\u524d\u957f\u5ea6");
    model.param().set("l_out", "5[cm]", "\u8154\u540e\u957f\u5ea6");
    model.param().set("l_total", "l_cav+l_in+l_out", "\u603b\u957f\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u8154\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("rho1", "100[mm]", "\u53cd\u5c04\u955c 1 \u7684\u66f2\u7387\u534a\u5f84");
    model.param("par2").set("rho2", "100[mm]", "\u53cd\u5c04\u955c 2 \u7684\u66f2\u7387\u534a\u5f84");
    model.param("par2").set("l_cav", "50[mm]", "\u8154\u4f53\u957f\u5ea6");
    model.param("par2").set("g1", "1-l_cav/rho1", "\u7a33\u5b9a\u6027\u53c2\u6570 1");
    model.param("par2").set("g2", "1-l_cav/rho2", "\u7a33\u5b9a\u6027\u53c2\u6570 2");
    model.param("par2")
         .set("w0", "sqrt(l_cav*lda0/pi)*(g1*g2*(1-g1*g2)/((g1+g2-2*g1*g2)^2))^(1/4)", "\u675f\u8170\u534a\u5f84");
    model.param("par2")
         .set("w1", "sqrt(l_cav*lda0/pi)*(g2/(g1*(1-g1*g2)))^(1/4)", "\u5de6\u955c\u7684\u5149\u675f\u534a\u5f84");
    model.param("par2")
         .set("w2", "sqrt(l_cav*lda0/pi)*(g1/(g2*(1-g1*g2)))^(1/4)", "\u53f3\u955c\u7684\u5149\u675f\u534a\u5f84");
    model.param("par2").set("FSR", "c_const/(2*l_cav)", "\u81ea\u7531\u5149\u8c31\u8303\u56f4");
    model.param("par2").set("F", "pi*(R*R)^(1/4)/(1-sqrt(R*R))", "\u7cbe\u7ec6\u5ea6");
    model.param("par2")
         .set("R", "abs((sqrt(Rl)-exp(2*i*phi)*sqrt(Rr))/(1-exp(2*i*phi)*sqrt(Rl*Rr)))^2", "\u53cd\u5c04\u955c\u5c42\u7684\u53cd\u5c04\u7387");
    model.param("par2")
         .set("phi", "2*pi*d*n/(lda0)", "\u901a\u8fc7\u53cd\u5c04\u955c\u5c42\u7684\u76f8\u4f4d\u5ef6\u8fdf");
    model.param("par2").set("d", "lda0/100", "\u53cd\u5c04\u955c\u5c42\u539a\u5ea6");
    model.param("par2").set("n", "15", "\u53cd\u5c04\u955c\u5c42\u6298\u5c04\u7387");
    model.param("par2").set("n1", "1", "\u53cd\u5c04\u955c\u5c42\u524d\u7684\u6298\u5c04\u7387");
    model.param("par2").set("n2", "1", "\u53cd\u5c04\u955c\u5c42\u540e\u7684\u6298\u5c04\u7387");
    model.param("par2")
         .set("r1", "(n1-n)/(n1+n)", "\u7b2c\u4e00\u53cd\u5c04\u955c\u5c42\u754c\u9762\u7684\u53cd\u5c04\u7387");
    model.param("par2")
         .set("r2", "(n-n2)/(n+n2)", "\u7b2c\u4e8c\u53cd\u5c04\u955c\u5c42\u754c\u9762\u7684\u53cd\u5c04\u7387");
    model.param("par2")
         .set("Rl", "r1^2", "\u7b2c\u4e00\u5c42\u53cd\u5c04\u955c\u754c\u9762\u7684\u53cd\u5c04\u7387");
    model.param("par2")
         .set("Rr", "r2^2", "\u7b2c\u4e8c\u5c42\u53cd\u5c04\u955c\u754c\u9762\u7684\u53cd\u5c04\u7387");
    model.param("par2").set("df", "FSR/F", "\u9891\u7387\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "rho1");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"rho1", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "rho2");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"l_cav-rho2", "0"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "c2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"l_total", "h_cav/2"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-l_in", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("r1", "uni1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n1"});

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("ewbe").feature("symp1").selection().set(2, 4, 7);
    model.component("comp1").physics("ewbe").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("trans1").selection().set(9, 10);
    model.component("comp1").physics("ewbe").feature("trans1").set("n_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("n", "n");
    model.component("comp1").physics("ewbe").feature("trans1").set("ki_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("d", "d");
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("sctr1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("sctr1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("sctr1").set("p0", "l_in+l_cav/2");
    model.component("comp1").physics("ewbe").feature("sctr1").set("Eg0", new int[]{0, 0, 1});
    model.component("comp1").physics("ewbe").feature("sctr1").create("rpnt1", "ReferencePoint", 0);
    model.component("comp1").physics("ewbe").feature("sctr1").feature("rpnt1").selection().set(1);
    model.component("comp1").physics("ewbe").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr2").selection().set(8);
    model.component("comp1").physics("ewbe").feature("sctr2").set("inputWave", "SecondWave");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(8);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("R", "intop1(ewbe.nPoav2)/intop1(-ewbe.nPoav1)");
    model.component("comp1").variable("var1").descr("R", "\u53cd\u5c04\u7387");
    model.component("comp1").variable("var1").set("T", "intop2(ewbe.nPoav1)/intop1(-ewbe.nPoav1)");
    model.component("comp1").variable("var1").descr("T", "\u900f\u5c04\u7387");

    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountT", 60);
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountL", 30);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u81ea\u7531\u5149\u8c31\u8303\u56f4\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "range(f0,FSR/101,f0+FSR)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 2);
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewbe.Poav1x", "ewbe.Poav1y"});
    model.result("pg1").feature("arws1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747\uff0c\u7b2c\u4e00\u4e2a\u6ce2");
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 21);
    model.result("pg1").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 0.2);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "ewbe.normE2");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("trn1").set("move", new String[]{"0", "h_cav*0.55"});
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws2", "arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").setIndex("expr", "ewbe.Poav2x", 0);
    model.result("pg1").feature("arws2").setIndex("expr", "ewbe.Poav2y", 1);
    model.result("pg1").feature("arws2").set("inheritplot", "arws1");
    model.result("pg1").feature("arws2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").feature("trn1").set("move", new String[]{"0", "h_cav*0.55"});
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("text", "\u7b2c\u4e00\u4e2a\u6ce2");
    model.result("pg1").feature("ann1").set("posxexpr", "l_cav/2");
    model.result("pg1").feature("ann1").set("posyexpr", "h_cav/4");
    model.result("pg1").feature("ann1").set("anchorpoint", "center");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("text", "\u7b2c\u4e8c\u4e2a\u6ce2");
    model.result("pg1").feature("ann2").set("posyexpr", "h_cav*0.55+h_cav/4");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5728\u4e00\u4e2a\u81ea\u7531\u5149\u8c31\u8303\u56f4\u5185\u626b\u63cf");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "T", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "R", 1);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq-f0");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "middleright");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/ewbe", true);
    model.study("std2").label("\u7814\u7a76 2 - \u8c10\u632f\u626b\u63cf");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "range(f0+0.5[GHz],0.001[GHz],f0+0.7[GHz])");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a\uff0c\u8c10\u632f");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").setIndex("looplevel", 92, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u7535\u573a");
    model.result("pg3").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg3").feature("surf1").set("colortable", "Twilight");
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("hght1").set("scale", 0.001);
    model.result("pg3").run();

    model.view("view2").camera().set("viewscaletype", "automatic");

    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8c10\u632f\u5f62\u72b6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "T", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "R", 1);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "freq-f0");
    model.result("pg4").feature("glob1").set("xdataunit", "GHz");
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().remove("surf2");
    model.result("pg5").run();
    model.result("pg5").feature().remove("arws2");
    model.result("pg5").run();
    model.result("pg5").feature().remove("ann1");
    model.result("pg5").feature().remove("ann2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg5").run();
    model.result("pg5").feature().remove("arws1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir2").setIndex("genpoints", 0, 1, 1);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("data", "mir2");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").setIndex("looplevel", 21, 0);
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").create("sel1", "Selection");
    model.result("pg5").feature("line1").feature("sel1").selection().set(5, 9, 10);
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "gray");
    model.result("pg5").feature().duplicate("line2", "line1");
    model.result("pg5").run();
    model.result("pg5").feature("line2").set("data", "mir2");
    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("text", "\u5165\u5c04\u548c\u53cd\u5c04\u5149\u675f");
    model.result("pg5").feature("ann1").set("posxexpr", "-l_in/2");
    model.result("pg5").feature("ann1").set("posyexpr", "h_cav/4");
    model.result("pg5").feature("ann1").set("anchorpoint", "center");
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").feature().duplicate("ann2", "ann1");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ann3", "ann2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("ann2").set("text", "\u8154\u4f53");
    model.result("pg5").feature("ann2").set("posxexpr", "l_cav/2");
    model.result("pg5").run();
    model.result("pg5").feature("ann3").set("posxexpr", "l_cav+l_out/2");
    model.result("pg5").feature().duplicate("ann4", "ann3");
    model.result("pg5").run();
    model.result("pg5").feature("ann4").set("text", "\u524d\u955c");
    model.result("pg5").feature("ann4").set("posxexpr", 0);
    model.result("pg5").feature("ann4").set("posyexpr", "-h_cav/4");
    model.result("pg5").feature("ann4").set("anchorpoint", "middleleft");
    model.result("pg5").feature("ann4").set("showpoint", true);
    model.result("pg5").feature().duplicate("ann5", "ann4");
    model.result("pg5").run();
    model.result("pg5").feature("ann5").set("text", "\u540e\u955c");
    model.result("pg5").feature("ann5").set("posxexpr", "l_cav");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ann6", "ann1");
    model.result("pg5").run();
    model.result("pg5").feature("ann6").set("text", "\u7a7a\u6c14");
    model.result("pg5").feature("ann6").set("posyexpr", "-3*h_cav/8");
    model.result("pg5").feature().duplicate("ann7", "ann6");
    model.result("pg5").run();
    model.result("pg5").feature("ann7").set("posxexpr", "l_cav/2");
    model.result("pg5").feature().duplicate("ann8", "ann7");
    model.result("pg5").run();
    model.result("pg5").feature("ann8").set("posxexpr", "l_cav+l_out/2");
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().dataset().remove("mir2");
    model.result("pg1").run();

    model.title("\u6cd5\u5e03\u91cc-\u73c0\u7f57\u8c10\u632f\u8154");

    model
         .description("\u6cd5\u5e03\u91cc-\u73c0\u7f57\u8c10\u632f\u8154\u662f\u57fa\u672c\u7684\u5149\u5b66\u5668\u4ef6\u4e4b\u4e00\uff0c\u5177\u6709\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u5e38\u89c1\u7684\u5e94\u7528\u793a\u4f8b\u5305\u62ec\u957f\u5ea6\u6d4b\u91cf\u3001\u9891\u7387\u548c/\u6216\u6ce2\u957f\u6d4b\u91cf\u6216\u7279\u5b9a\u7a7a\u95f4\u6a21\u5f0f\u7684\u6ee4\u6ce2\u3002\n\n\u8be5\u6a21\u578b\u4f7f\u7528\u53cc\u5411\u516c\u5f0f\u4e2d\u7684\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6765\u6709\u6548\u5730\u8ba1\u7b97\u5b8f\u89c2\u6cd5\u5e03\u91cc-\u73c0\u7f57\u8c10\u632f\u8154\u7684\u632f\u578b\u548c\u53cd\u5c04/\u900f\u5c04\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("fabry_perot_resonator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
