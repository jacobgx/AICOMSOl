/*
 * time_to_frequency_fft_distributed_bragg_reflector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:37 by COMSOL 6.3.0.290. */
public class time_to_frequency_fft_distributed_bragg_reflector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewt", "ElectromagneticWavesTransient", "geom1");
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/ewt", true);
    model.study("std1").feature("time").setSolveFor("/physics/ewfd", true);
    model.study("std1").create("tffft", "TimeToFreqFFT");
    model.study("std1").feature("tffft").set("fftinputstudy", "current");
    model.study("std1").feature("tffft").set("ftplistmethod", "manual");
    model.study("std1").feature("tffft").set("fftstarttime", "0");
    model.study("std1").feature("tffft").set("fftendtime", "1");
    model.study("std1").feature("tffft").set("fftmaxfreq", "10");
    model.study("std1").feature("tffft").set("fftwincutoff", "1");
    model.study("std1").feature("tffft").set("fftwinminfw", "0");
    model.study("std1").feature("tffft").set("fftwinmaxfw", "1");
    model.study("std1").feature("tffft").set("notsolnum", "auto");
    model.study("std1").feature("tffft").set("outputmap", new String[]{});
    model.study("std1").feature("tffft").setSolveFor("/physics/ewt", true);
    model.study("std1").feature("tffft").setSolveFor("/physics/ewfd", true);
    model.study("std1").create("cmbsol", "CombineSolution");
    model.study("std1").feature("cmbsol").set("soloper", "remsol");
    model.study("std1").feature("cmbsol").set("excmethod", "implicit");
    model.study("std1").feature("cmbsol").set("remsolfromexprexc", "0");
    model.study("std1").feature("cmbsol").setSolveFor("/physics/ewt", true);
    model.study("std1").feature("cmbsol").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1[um]", "\u4e2d\u5fc3\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u4e2d\u5fc3\u9891\u7387");
    model.param().set("omega0", "2*pi*f0", "\u4e2d\u5fc3\u89d2\u9891\u7387");
    model.param().set("n0", "1", "\u6298\u5c04\u7387\uff0c\u8986\u677f");
    model.param().set("nH", "2.5", "\u6298\u5c04\u7387\uff0c\u9ad8\u6298\u5c04\u7387\u5c42");
    model.param().set("nL", "1.5", "\u6298\u5c04\u7387\uff0c\u4f4e\u6298\u5c04\u7387\u5c42");
    model.param().set("ns", "1.5", "\u6298\u5c04\u7387\uff0c\u57fa\u677f");
    model.param().set("E0", "1[V/m]", "\u7535\u573a\u5927\u5c0f");
    model.param().set("T0", "1/f0", "\u4e2d\u5fc3\u9891\u7387\u5468\u671f");
    model.param().set("df0", "f0*4/pi*asin((nH-nL)/(nH+nL))", "\u53cd\u5c04\u955c\u963b\u5e26");
    model.param().set("d0", "lda0/(4*n0)", "\u539a\u5ea6\uff0c\u8986\u677f");
    model.param().set("dH", "lda0/(4*nH)", "\u539a\u5ea6\uff0c\u9ad8\u6298\u5c04\u7387\u5c42");
    model.param().set("dL", "lda0/(4*nL)", "\u539a\u5ea6\uff0c\u4f4e\u6298\u5c04\u7387\u5c42");
    model.param()
         .set("dPeriod", "dH+dL", "\u539a\u5ea6\uff0c\u4e00\u5bf9\u4f4e\u6298\u5c04\u7387\u5c42\u548c\u9ad8\u6298\u5c04\u7387\u5c42");
    model.param().set("ds", "lda0/(4*ns)", "\u539a\u5ea6\uff0c\u57fa\u677f");
    model.param().set("NPeriod", "20", "\u5468\u671f\u6570");
    model.param().set("L_tot", "NPeriod*dPeriod+dH", "\u603b\u539a\u5ea6");
    model.param().set("deltaf", "1[THz]", "\u9891\u7387\u5206\u8fa8\u7387");
    model.param().set("Tend", "1/deltaf", "\u7ed3\u675f\u65f6\u95f4");
    model.param().set("Tc", "3*Td", "\u5ef6\u8fdf\u65f6\u95f4");
    model.param().set("Td", "1/(2*f0)", "\u8109\u51b2\u6301\u7eed\u65f6\u95f4");
    model.param().set("Tmax", "max(200*Td,Tend)", "\u4eff\u771f\u7ed3\u675f\u65f6\u95f4");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u4e0a\u5c42");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d0", "lda0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-d0", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").label("\u9ad8\u6298\u5c04\u7387\u5c42");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"dH", "lda0"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u9ad8\u6298\u5c04\u7387\u6750\u6599");
    model.component("comp1").geom("geom1").feature("r2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").label("\u4f4e\u6298\u5c04\u7387\u5c42");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"dL", "lda0"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"dH", "0"});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u4f4e\u6298\u5c04\u7387\u6750\u6599");
    model.component("comp1").geom("geom1").feature("r3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5468\u671f");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"NPeriod", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"dPeriod", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r2");
    model.component("comp1").geom("geom1").feature("r4").label("\u6700\u540e\u7684\u9ad8\u6298\u5c04\u7387\u5c42");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"L_tot-dH", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r1");
    model.component("comp1").geom("geom1").feature("r5").label("\u57fa\u5e95");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"ds", "lda0"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"L_tot", "0"});
    model.component("comp1").geom("geom1").feature("r5").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u4e8c\u6c27\u5316\u949b");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nH"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u4e8c\u6c27\u5316\u7845");
    model.component("comp1").material("mat3").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"nL"});

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u8c03\u5236\u9ad8\u65af\u8f93\u5165\u573a");
    model.component("comp1").func("an1").set("funcname", "Ein");
    model.component("comp1").func("an1").set("expr", "E0*exp(-(t-Tc)^2/Td^2)*sin(omega0*(t-Tc))");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);
    model.component("comp1").func("an1").set("fununit", "V/m");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9876\u90e8\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1")
         .set(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 63, 66, 69, 72, 75, 78, 81, 84, 87, 90, 93, 96, 99, 102, 105, 108, 111, 114, 117, 120, 123, 126, 129);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u5e95\u90e8\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel2")
         .set(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35, 38, 41, 44, 47, 50, 53, 56, 59, 62, 65, 68, 71, 74, 77, 80, 83, 86, 89, 92, 95, 98, 101, 104, 107, 110, 113, 116, 119, 122, 125, 128);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u9876/\u5e95\u90e8\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u9876\u90e8\u9ad8\u6298\u5c04\u7387\u6750\u6599\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 1);
    model.component("comp1").selection("int1").set("input", new String[]{"sel1", "geom1_csel1_bnd"});
    model.component("comp1").selection().duplicate("int2", "int1");
    model.component("comp1").selection("int2").label("\u9876\u90e8\u4f4e\u6298\u5c04\u7387\u6750\u6599\u8fb9\u754c");
    model.component("comp1").selection("int2").set("input", new String[]{"sel1", "geom1_csel2_bnd"});

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "L_tot+ds", 0);
    model.component("comp1").probe("pdom1").set("bndsnap2", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "ewt.Ez");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set(130);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Pin", "0.5*intop1(abs(EinODE)^2/Z0_const*ewt.nxx)");
    model.component("comp1").variable("var1").descr("Pin", "\u8f93\u5165\u529f\u7387");
    model.component("comp1").variable("var1").set("Pt", "intop2(ewt.nPoav)");
    model.component("comp1").variable("var1").descr("Pt", "\u900f\u5c04\u529f\u7387");

    model.component("comp1").physics("ewt").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewt").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewt").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewt").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewt").feature("sctr1").set("E0i", new String[]{"0", "0", "Ein(t)"});
    model.component("comp1").physics("ewt").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewt").feature("sctr2").selection().set(130);
    model.component("comp1").physics("ewt").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp1").physics("ewt").feature("pmc1").selection().named("uni1");
    model.component("comp1").physics("ewt").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ewt").feature("ge1").setIndex("name", "EinODE", 0, 0);
    model.component("comp1").physics("ewt").feature("ge1").setIndex("equation", "EinODE-Ein(t)", 0, 0);
    model.component("comp1").physics("ewt").feature("ge1").setIndex("initialValueU", "Ein(0)", 0, 0);
    model.component("comp1").physics("ewt").feature("ge1").setIndex("initialValueUt", "d(Ein(t),t)", 0, 0);
    model.component("comp1").physics("ewt").feature("ge1").setIndex("description", "\u8f93\u5165\u7535\u573a", 0, 0);
    model.component("comp1").physics("ewt").feature("ge1").set("DependentVariableQuantity", "electricfield");
    model.component("comp1").physics("ewt").feature("ge1").set("SourceTermQuantity", "electricfield");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "lda0/2/n0/6");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "lda0/2/nH/6");
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("size3").selection().named("int2");
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", "lda0/2/nL/6");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").named("sel1");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").named("sel2");
    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "fs");
    model.study("std1").feature("time").set("tlist", "range(0,0.25*T0,Tmax)");
    model.study("std1").feature("tffft").set("tunit", "fs");
    model.study("std1").feature("tffft").set("fftendtime", "Tend");
    model.study("std1").feature("tffft").set("punit", "THz");
    model.study("std1").feature("tffft").set("fftmaxfreq", "2*f0");
    model.study("std1").feature("tffft").setSolveFor("/physics/ewfd", false);
    model.study("std1").feature("cmbsol").set("remsolfromexprexc", "freq<0.5*f0||freq>1.5*f0");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.01*T0");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("solvertype", "none");
    model.result("pg2").set("defaultPlotID", "TransientElectromagneticWaves/phys1/pdef1/pcond2/pg1");
    model.result("pg2").set("weight", 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u573a (ewt, FD)");
    model.result("pg2").set("looplevel", new int[]{151});
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a (ewt, TD)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ewt.Ez");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 49, 0);
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u900f\u5c04\u7387");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "Pt/Pin", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u900f\u5c04\u7387 (ewt)", 0);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u80fd\u91cf");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop3(ewt.W)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u80fd\u91cf", 0);
    model.result("pg5").run();
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymin", "1e-30");
    model.result("pg5").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewfd").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr1").set("E0i", new String[]{"0", "0", "E0"});
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().set(130);
    model.component("comp1").physics("ewfd").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp1").physics("ewfd").feature("pmc1").selection().named("uni1");

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
    model.study("std2").feature("freq").setSolveFor("/physics/ewt", false);
    model.study("std2").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("freq").set("plist", "range(0.5*f0,deltaf,1.5*f0)");
    model.study("std2").feature("freq").set("probesel", "none");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"ewt"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (ewfd)");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 300, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("defaultPlotID", "ElectromagneticWavesFrequencyDomain/phys1/pdef1/pcond2/pg1");
    model.result("pg6").set("weight", 0);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 151, 0);
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("data", "dset4");
    model.result("pg4").feature("glob2").setIndex("expr", "intop2(ewfd.nPoav)/intop1(0.5*E0^2/Z0_const*n0)", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u900f\u5c04\u7387 (ewfd)", 0);
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "4");
    model.result("pg7").feature("surf1").set("colortable", "Cividis");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "2");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf2");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").set("expr", "3");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("sel1").selection().named("geom1_csel2_dom");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf4", "surf3");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").set("expr", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").feature("sel1").selection().set(43);
    model.result("pg7").run();
    model.result("pg7").create("ann1", "Annotation");
    model.result("pg7").feature("ann1").set("text", "\u4e0a\u5c42");
    model.result("pg7").feature("ann1").set("posxexpr", "-d0/2");
    model.result("pg7").feature("ann1").set("posyexpr", "lda0/2");
    model.result("pg7").feature("ann1").set("showpoint", false);
    model.result("pg7").feature("ann1").set("anchorpoint", "center");
    model.result("pg7").feature("ann1").set("orientation", "vertical");
    model.result("pg7").feature().duplicate("ann2", "ann1");
    model.result("pg7").run();
    model.result("pg7").feature("ann2").set("text", "\u57fa\u5e95");
    model.result("pg7").feature("ann2").set("posxexpr", "L_tot+ds/2");
    model.result("pg7").feature().duplicate("ann3", "ann2");
    model.result("pg7").run();
    model.result("pg7").feature("ann3").set("text", "\u9ad8\u6298\u5c04\u7387");
    model.result("pg7").feature("ann3").set("posxexpr", "2*dPeriod+dH/2");
    model.result("pg7").feature().duplicate("ann4", "ann3");
    model.result("pg7").run();
    model.result("pg7").feature("ann4").set("posxexpr", "3*dPeriod-dL/2");
    model.result("pg7").feature("ann4").set("text", "\u4f4e\u6298\u5c04\u7387");
    model.result("pg7").run();
    model.result("pg7").feature("ann2").set("color", "white");
    model.result("pg7").run();
    model.result("pg7").feature("ann3").set("color", "white");
    model.result("pg7").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg2").run();

    model.title("\u5206\u5e03\u5f0f\u5e03\u62c9\u683c\u53cd\u5c04\u955c\u7684\u65f6\u9891 FFT \u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4e3a\u5206\u5e03\u5f0f\u5e03\u62c9\u683c\u53cd\u5c04\u955c (DBR) \u7ed3\u6784\u8bbe\u7f6e\u201c\u65f6\u9891 FFT\u201d\u7814\u7a76\u3002\u5176\u4e2d\u4f7f\u7528\u8c03\u5236\u7684\u9ad8\u65af\u8f93\u5165\u8109\u51b2\u6fc0\u52b1\u8be5 DBR \u7ed3\u6784\uff1b\u968f\u540e\uff0c\u4f7f\u7528\u5feb\u901f\u5085\u91cc\u53f6\u53d8\u6362 (FFT) \u5c06\u65f6\u57df\u89e3\u8f6c\u6362\u5230\u9891\u57df\u3002\n\n\u5206\u6790\u7ed3\u679c\u4e0e\u5e38\u89c4\u9891\u57df\u7814\u7a76\u7684\u7ed3\u679c\u543b\u5408\u826f\u597d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("time_to_frequency_fft_distributed_bragg_reflector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
