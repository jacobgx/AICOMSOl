/*
 * whispering_gallery_mode_resonator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:44 by COMSOL 6.3.0.290. */
public class whispering_gallery_mode_resonator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd", true);

    model.param().label("\u8c10\u632f\u5668\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("h_air", "r_res", "\u7a7a\u6c14\u57df\u7684\u9ad8\u5ea6");
    model.param().set("r_air", "r_res*1.5", "\u7a7a\u6c14\u57df\u7684\u534a\u5f84");
    model.param().set("r_res", "30[um]", "\u8c10\u632f\u5668\u534a\u5f84");
    model.param().set("r_cut", "r_res*0.5", "\u51e0\u4f55\u622a\u65ad\u534a\u5f84");
    model.param().set("t_PML", "1[um]", "PML \u7684\u539a\u5ea6");
    model.param().set("n_m", "250", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("n_res", "1.4457", "\u6298\u5c04\u7387\uff0c\u5b9e\u90e8");
    model.param().set("k_res", "1e-10", "\u6298\u5c04\u7387\uff0c\u865a\u90e8");
    model.param().set("n_a", "1", "\u6298\u5c04\u7387\uff0c\u4e3b\u4ecb\u8d28");
    model.param().set("m", "n_res/n_a", "\u76f8\u5bf9\u6298\u5c04\u7387");
    model.param().set("nu_fsr", "c_const/(2*pi*n_res*r_res)", "\u81ea\u7531\u5149\u8c31\u8303\u56f4");
    model.param().set("alpha", "4*pi*k_res/(lam_res)", "\u8870\u51cf\u7cfb\u6570");
    model.param().set("Q_mat", "2*pi*n_res/(alpha*lam_res)", "\u6750\u6599\u635f\u8017");
    model.param().set("damp", "0.5*omega_res/Q_mat", "\u6750\u6599\u635f\u8017\u5f15\u8d77\u7684\u963b\u5c3c");
    model.param().create("par2");
    model.param("par2").label("\u89e3\u6790\u9ad8\u9636\u8fd1\u4f3c - Schiller");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("p", "1", "1 \u8868\u793a TE\uff0c1/m^2 \u8868\u793a TM");
    model.param("par2").set("l", "1", "\u5f84\u5411\u6a21\u6570");
    model.param("par2").set("nu", "n_m+1/2");
    model.param("par2")
         .set("xn", "nu/m - Ai(l)/m*(nu/2)^(1/3) + dk(0)/(nu^(0/3) * (m^2 - 1)^((0+1)/2)) + dk(1)/(nu^(1/3) * (m^2 - 1)^((1+1)/2)) + dk(2)/(nu^(2/3) * (m^2 - 1)^((2+1)/2)) + dk(3)/(nu^(3/3) * (m^2 - 1)^((3+1)/2))", "\u8c10\u632f\u5927\u5c0f\u53c2\u6570");
    model.param("par2").set("omega_res", "xn *c_const/(n_a*r_res)", "\u89d2\u9891\u7387");
    model.param("par2").set("f_res", "omega_res/(2*pi)", "\u8c10\u632f\u9891\u7387");
    model.param("par2").set("lam_res", "c_const/f_res", "\u8c10\u632f\u6ce2\u957f");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u827e\u91cc\u51fd\u6570\u96f6\u70b9");
    model.func("int1").set("funcname", "Ai");
    model.func("int1")
         .set("table", new String[][]{{"1", "-2.33811"}, 
         {"2", "-4.08795"}, 
         {"3", "-5.52056"}, 
         {"4", "-6.7867144"}, 
         {"5", "-7.94413"}, 
         {"6", "-9.02265"}});
    model.func().create("pw1", "Piecewise");
    model.func("pw1").set("funcname", "dk");
    model.func("pw1")
         .set("pieces", new String[][]{{"0", "0.5", "-p"}, 
         {"0.5", "1.5", "2^(1/3)*3*(m^2-1)*Ai(l)^2/(20*m)"}, 
         {"1.5", "2.5", "-2^(2/3)*m^2*p*(-3+2*p^2)*Ai(l)/6"}, 
         {"2.5", "3.5", "(350*m^4*(1-p)*p*(-1+p+p^2)+(m^2-1)^2*(10+Ai(l)^3))/(700*m)"}});

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_air-r_cut", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "h_air", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"r_cut", "-h_air/2"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_res");
    model.component("comp1").geom("geom1").feature("c1").set("rot", 45);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("del1", 24);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.25, 0);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.75, 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5bbf\u4e3b\u4ecb\u8d28");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_a"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7403\u4f53");
    model.component("comp1").material("mat2").selection().set(1, 2, 3);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_res"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("ki", new String[]{"k_res"});

    model.component("comp1").physics("ewfd").prop("outofplanewavenumber").set("mFloquet", "n_m");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "res");
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "air");
    model.component("comp1").cpl("intop2").selection().set(6);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("ModeLoc", "res(ewfd.normE)/(res(ewfd.normE)+air(ewfd.normE))");
    model.component("comp1").variable("var1").descr("ModeLoc", "\u6a21\u5f0f\u5c40\u90e8\u5316");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 3, 4, 5, 7, 8, 9);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1.25);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.009);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgrad", 1.04);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(26);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 0.15);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u8fc7\u6ee4\u540e\u7684\u7279\u5f81\u9891\u7387\u7814\u7a76");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 200);
    model.study("std1").feature("eig").set("shift", "f_res");
    model.study("std1").create("cmbsol", "CombineSolution");
    model.study("std1").feature("cmbsol").set("soloper", "remsol");
    model.study("std1").feature("cmbsol").set("excludeorinclude", "include");
    model.study("std1").feature("cmbsol").set("incmethod", "implicit");
    model.study("std1").feature("cmbsol").set("remsolfromexprinc", "comp1.ModeLoc>0.5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7279\u5f81\u9891\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.freq", "ewfd.Qfactor"});
    model.result().numerical("gev1").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a - \u8fc7\u6ee4\u540e\u7684\u6a21\u5f0f");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Er", "ewfd.Ez"});
    model.result("pg1").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg1").feature("arws1").set("xnumber", 40);
    model.result("pg1").feature("arws1").set("ynumber", 40);
    model.result("pg1").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{45});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6a21\u5f0f\u5c40\u90e8\u5316");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("ylogsec", true);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "ModeLoc", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "ewfd.freq");
    model.result("pg2").feature("glob1").set("xdataunit", "THz");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linemarker", "circle");
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob2").setIndex("expr", "ewfd.Qfactor", 0);
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "ewfd.freq");
    model.result("pg2").feature("glob2").set("xdataunit", "THz");
    model.result("pg2").feature("glob2").set("linemarker", "point");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "middleleft");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a - \u6240\u6709\u6a21\u5f0f");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("ftplistmethod", "manual");
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/ewfd", true);
    model.study("std2").label("\u7279\u5f81\u9891\u7387\u533a\u57df\u641c\u7d22");
    model.study("std2").feature("eig").set("eigmethod", "region");
    model.study("std2").feature("eig").set("appnreigs", 100);
    model.study("std2").feature("eig").set("eigsr", "f_res-5[THz]");
    model.study("std2").feature("eig").set("eiglr", "f_res+20[THz]");
    model.study("std2").feature("eig").set("eigli", "(damp+500[kHz])/(2*pi)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ewfd)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset3");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u7279\u5f81\u9891\u7387 (ewfd) 1");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").set("expr", new String[]{"ewfd.freq", "ewfd.Qfactor"});
    model.result().numerical("gev2").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl2", "Table");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").run();
    model.result().numerical("gev2").setResult();
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u573a - \u533a\u57df\u641c\u7d22");
    model.result("pg4").set("looplevel", new int[]{27});
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature().copy("arws1", "pg1/arws1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u8fc7\u6ee4\u6807\u51c6");
    model.result("pg5").set("twoyaxes", false);
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"ewfd.damp"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u968f\u65f6\u95f4\u8870\u51cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"Hz"});
    model.result("pg5").feature("glob1").setIndex("unit", "THz", 0);
    model.result("pg5").feature("glob1").set("linemarker", "point");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset3");
    model.result("pg5").feature("glob2").setIndex("expr", "(damp+500[kHz])", 0);
    model.result("pg5").feature("glob2").setIndex("unit", "THz", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u641c\u7d22\u533a\u57df", 0);
    model.result("pg5").feature("glob2").set("linecolor", "black");
    model.result("pg5").feature("glob2").set("linemarker", "none");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob3", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob3").set("data", "dset3");
    model.result("pg5").feature("glob3").set("linemarker", "circle");
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("ftplistmethod", "manual");
    model.study("std3").feature("eig").set("linpsolnum", "auto");
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd", true);
    model.study("std3").label("\u53c2\u6570\u5316\u626b\u63cf\u7279\u5f81\u9891\u7387\u7814\u7a76");
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 1);
    model.study("std3").feature("eig").set("shift", "f_res");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "alpha", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "1/m", 0);
    model.study("std3").feature("param").setIndex("pname", "alpha", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "1/m", 0);
    model.study("std3").feature("param").setIndex("pname", "r_res", 0);
    model.study("std3").feature("param").setIndex("plistarr", "10 20 30", 0);
    model.study("std3").feature("param").setIndex("punit", "um", 0);
    model.study("std3").feature("param").setIndex("pname", "alpha", 1);
    model.study("std3").feature("param").setIndex("plistarr", "", 1);
    model.study("std3").feature("param").setIndex("punit", "1/m", 1);
    model.study("std3").feature("param").setIndex("pname", "alpha", 1);
    model.study("std3").feature("param").setIndex("plistarr", "", 1);
    model.study("std3").feature("param").setIndex("punit", "1/m", 1);
    model.study("std3").feature("param").setIndex("pname", "n_m", 1);
    model.study("std3").feature("param").setIndex("plistarr", "80 165 250", 1);
    model.study("std3").create("param2", "Parametric");
    model.study("std3").feature().move("param2", 1);
    model.study("std3").feature("param2").setIndex("pname", "alpha", 0);
    model.study("std3").feature("param2").setIndex("plistarr", "", 0);
    model.study("std3").feature("param2").setIndex("punit", "1/m", 0);
    model.study("std3").feature("param2").setIndex("pname", "alpha", 0);
    model.study("std3").feature("param2").setIndex("plistarr", "", 0);
    model.study("std3").feature("param2").setIndex("punit", "1/m", 0);
    model.study("std3").feature("param2").setIndex("pname", "p", 0);
    model.study("std3").feature("param2").setIndex("plistarr", "1 1/m^2", 0);
    model.study("std3").feature("param2").setIndex("pname", "alpha", 1);
    model.study("std3").feature("param2").setIndex("plistarr", "", 1);
    model.study("std3").feature("param2").setIndex("punit", "1/m", 1);
    model.study("std3").feature("param2").setIndex("pname", "alpha", 1);
    model.study("std3").feature("param2").setIndex("plistarr", "", 1);
    model.study("std3").feature("param2").setIndex("punit", "1/m", 1);
    model.study("std3").feature("param2").setIndex("pname", "l", 1);
    model.study("std3").feature("param2").setIndex("plistarr", "1 2 3", 1);
    model.study("std3").feature("param2").set("sweeptype", "filled");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p2").run("compute");

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (ewfd)");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").setIndex("looplevel", 6, 2);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "none");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").set("data", "dset5");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u7279\u5f81\u9891\u7387 (ewfd) 2");
    model.result().numerical("gev3").set("data", "dset5");
    model.result().numerical("gev3").set("expr", new String[]{"ewfd.freq", "ewfd.Qfactor"});
    model.result().numerical("gev3").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl3", "Table");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").run();
    model.result().numerical("gev3").setResult();
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u573a - \u4e0d\u540c\u914d\u7f6e");
    model.result("pg6").set("looplevel", new int[]{1, 2, 2});
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").feature().copy("arws1", "pg4/arws1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e09\u7ef4\u6247\u533a");
    model.result("pg7").set("data", "rev3");
    model.result("pg7").set("looplevel", new int[]{1, 3, 1});
    model.result("pg7").create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("xnumber", "0");
    model.result("pg7").feature("mslc1").set("ynumber", "0");
    model.result("pg7").feature("mslc1").set("colortable", "AuroraBorealis");
    model.result().dataset("rev3").set("layermethod", "custom");
    model.result().dataset("rev3").set("revlayers", 100);
    model.result().dataset("rev3").set("startangle", -105);
    model.result().dataset("rev3").set("revangle", 15);
    model.result("pg7").run();
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("revcoordsys", "cylindrical");
    model.result("pg7").feature("arwv1").setIndex("expr", "ewfd.Er*exp(-i*n_m*rev3phi)", 0);
    model.result("pg7").feature("arwv1").setIndex("expr", "ewfd.Ephi*exp(-i*n_m*rev3phi)", 1);
    model.result("pg7").feature("arwv1").setIndex("expr", "ewfd.Ez*exp(-i*n_m*rev3phi)", 2);
    model.result("pg7").feature("arwv1").set("xnumber", 100);
    model.result("pg7").feature("arwv1").set("ynumber", 100);
    model.result("pg7").feature("arwv1").set("znumber", 1);
    model.result("pg7").run();
    model.result("pg7").create("iso1", "Isosurface");
    model.result("pg7").feature("iso1").set("expr", "ewfd.Ephi*exp(-i*n_m*rev3phi)");
    model.result("pg7").feature("iso1").set("levelmethod", "levels");
    model.result("pg7").feature("iso1").set("levels", "range(-1.2e6,0.5e6,1.2e6)");
    model.result("pg7").feature("iso1").set("colortable", "Wave");
    model.result("pg7").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").feature("iso1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature("tran1").set("transparency", 0.45);
    model.result("pg7").run();
    model.result("pg2").run();
    model.result().duplicate("pg8", "pg2");
    model.result("pg8").run();
    model.result("pg8").label("\u4e0d\u540c\u6a21\u5f0f\u548c\u534a\u5f84\u7684\u54c1\u8d28\u56e0\u5b50");
    model.result("pg8").set("data", "dset5");
    model.result("pg8").set("twoyaxes", false);
    model.result("pg8").set("ylog", true);
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "Q_mat", 0);
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg8").feature("glob1").set("xdataexpr", "r_res");
    model.result("pg8").feature("glob1").set("linestyle", "dashed");
    model.result("pg8").feature("glob1").set("linecolor", "black");
    model.result("pg8").feature("glob1").set("linemarker", "none");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg8").feature("glob2").set("xdataexpr", "r_res");
    model.result("pg8").feature("glob2").set("linestyle", "none");
    model.result("pg8").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "ewfd.lambda0", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u6ce2\u957f", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "ewfd.Qfactor", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "Q_mat", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "ewfd.damp", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "kHz", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "damp", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "kHz", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "ewfd.Qfactor*c_const/(ewfd.omega*n_res*r_res)", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u7cbe\u7ec6\u5ea6", 5);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset5");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "f_res", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "THz", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "ewfd.Qfactor", 1);
    model.result().evaluationGroup("eg2").run();
    model.result("pg7").run();

    model.title("\u56de\u97f3\u58c1\u6a21\u5f0f\u8c10\u632f\u5668");

    model
         .description("\u4ecb\u7535\u5fae\u7403\u53ef\u4ee5\u652f\u6301\u5177\u6709\u9ad8\u5149\u5b66\u54c1\u8d28\u56e0\u5b50\u7684\u56de\u97f3\u58c1\u6a21\u5f0f\u3002\u8be5\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u4e0d\u540c\u7684\u7279\u5f81\u6a21\u6001\u548c\u8c10\u632f\u9891\u7387\u3002\u5176\u4e2d\uff0c\u8c10\u632f\u9891\u7387\u65e2\u53ef\u4ee5\u901a\u8fc7\u5b83\u4eec\u5728\u8c10\u632f\u5668\u4e2d\u7684\u7a7a\u95f4\u5b9a\u4f4d\u6765\u8fc7\u6ee4\uff0c\u4e5f\u53ef\u4ee5\u901a\u8fc7\u6bd4\u8f83\u675f\u7f1a\u6a21\u7684\u635f\u8017\u4e0e\u6b64\u7c7b\u5e94\u7528\u4e2d\u4e0d\u5e0c\u671b\u51fa\u73b0\u7684\u7a7a\u6c14\u6a21\u7684\u635f\u8017\u8fdb\u884c\u8fc7\u6ee4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();

    model.label("whispering_gallery_mode_resonator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
