/*
 * vertical_cavity_surface_emitting_laser.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class vertical_cavity_surface_emitting_laser {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom1");

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
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd2", true);

    model.component("comp1").geom("geom1")
         .insertFile("vertical_cavity_surface_emitting_laser_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u901a\u7528\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lda0", "980.35[nm]", "\u6ce2\u957f");
    model.param("par2").set("k0", "2*pi/lda0", "\u771f\u7a7a\u6ce2\u6570");
    model.param("par2").set("f0", "c_const/lda0", "\u9891\u7387");
    model.param("par2").set("gain_QW", "1200[1/cm]", "\u91cf\u5b50\u9631\u589e\u76ca");
    model.param().create("par3");
    model.param("par3").label("\u6750\u6599\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("n_GaAs", "3.53", "\u6298\u5c04\u7387\uff0cGaAs");
    model.param("par3").set("n_AlGaAs", "3.08", "\u6298\u5c04\u7387\uff0cAlGaAs");
    model.param("par3").set("n_AlOx", "1.60", "\u6298\u5c04\u7387\uff0cAlOx");
    model.param("par3").set("n_AlAs", "2.95", "\u6298\u5c04\u7387\uff0cAlAs");
    model.param("par3").set("n_air", "1", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param("par3")
         .set("kappa_QW_gain", "-gain_QW/k0/2", "\u6298\u5c04\u7387\uff0c\u91cf\u5b50\u9631\uff0c\u589e\u76ca\u57df\uff0c\u865a\u90e8");
    model.param("par3")
         .set("kappa_QW_loss", "0.01", "\u6298\u5c04\u7387\uff0c\u91cf\u5b50\u9631\uff0c\u635f\u8017\u57df\uff0c\u865a\u90e8");
    model.param("par3").set("n_QW", "n_GaAs", "\u6298\u5c04\u7387\uff0c\u91cf\u5b50\u9631");

    model.component("comp1").physics("ewfd").prop("outofplanewavenumber").set("mFloquet", 1);
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection()
         .set(236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349);
    model.component("comp1").physics("ewfd").feature("sctr1").set("WaveType", "CylindricalWave");
    model.component("comp1").physics("ewfd").create("imp1", "Impedance", 1);
    model.component("comp1").physics("ewfd").feature("imp1").selection().set(2, 229);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14\u4e0a\u5c42");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().set(229);
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_air"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("GaAs");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_GaAs"});
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").label("AlGaAs");
    model.component("comp1").material("mat3").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"n_AlGaAs"});
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").label("QW \u589e\u76ca");
    model.component("comp1").material("mat4").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").set("n", new String[]{"n_QW"});
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").set("ki", new String[]{"kappa_QW"});
    model.component("comp1").material().duplicate("mat5", "mat4");
    model.component("comp1").material("mat5").label("QW \u635f\u8017");
    model.component("comp1").material("mat5").selection().named("geom1_csel4_dom");
    model.component("comp1").material("mat5").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"kappa_QW_loss"});
    model.component("comp1").material().duplicate("mat6", "mat3");
    model.component("comp1").material("mat6").label("AlAs");
    model.component("comp1").material("mat6").selection().named("geom1_csel5_dom");
    model.component("comp1").material("mat6").propertyGroup("RefractiveIndex").set("n", new String[]{"n_AlAs"});
    model.component("comp1").material().duplicate("mat7", "mat6");
    model.component("comp1").material("mat7").label("AlOx");
    model.component("comp1").material("mat7").selection().named("geom1_csel6_dom");
    model.component("comp1").material("mat7").propertyGroup("RefractiveIndex").set("n", new String[]{"n_AlOx"});
    model.component("comp1").material().duplicate("mat8", "mat1");
    model.component("comp1").material("mat8").label("GaAs \u57fa\u5e95");
    model.component("comp1").material("mat8").selection().set(2);
    model.component("comp1").material("mat8").propertyGroup("RefractiveIndex").set("n", new String[]{"n_GaAs"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("kappa_QW", "kappa_QW_gain");
    model.component("comp1").variable("var1")
         .descr("kappa_QW", "\u6298\u5c04\u7387\uff0c\u91cf\u5b50\u9631\uff0c\u589e\u76ca\u57df\uff0c\u865a\u90e8");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").label("GaAs");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_csel2_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "lda0/6/n_GaAs");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").label("AlGaAs");
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "lda0/6/n_AlGaAs");
    model.component("comp1").mesh("mesh1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("size3").label("QW \u589e\u76ca");
    model.component("comp1").mesh("mesh1").feature("size3").selection().named("geom1_csel3_dom");
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", "lda0/6/n_QW");
    model.component("comp1").mesh("mesh1").feature().duplicate("size4", "size3");
    model.component("comp1").mesh("mesh1").feature("size4").label("QW \u635f\u8017");
    model.component("comp1").mesh("mesh1").feature("size4").selection().named("geom1_csel4_dom");
    model.component("comp1").mesh("mesh1").feature().duplicate("size5", "size4");
    model.component("comp1").mesh("mesh1").feature("size5").label("AlAs");
    model.component("comp1").mesh("mesh1").feature("size5").selection().named("geom1_csel5_dom");
    model.component("comp1").mesh("mesh1").feature("size5").set("hmax", "lda0/6/n_AlAs");
    model.component("comp1").mesh("mesh1").feature().duplicate("size6", "size5");
    model.component("comp1").mesh("mesh1").feature("size6").label("AlOx");
    model.component("comp1").mesh("mesh1").feature("size6").selection().named("geom1_csel6_dom");
    model.component("comp1").mesh("mesh1").feature("size6").set("hmax", "lda0/6/n_AlOx");
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("shift", "f0");
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd2", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"ewfd2"});
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
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.freq", "ewfd.Qfactor"});
    model.result().numerical("gev1").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical("gev1").setIndex("expr", "ewfd.lambda0", 2);
    model.result().numerical("gev1").setIndex("unit", "nm", 2);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u70b9\u8ba1\u7b97");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(115);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq", "freq1", 0);
    model.component("comp1").physics("ewfd2").prop("outofplanewavenumber").set("mFloquet", 1);
    model.component("comp1").physics("ewfd2").feature("init1")
         .set("E2", new String[]{"withsol('sol1',ewfd.Er)", "withsol('sol1',ewfd.Ephi)", "withsol('sol1',ewfd.Ez)"});
    model.component("comp1").physics("ewfd2").feature().copy("sctr1", "ewfd/sctr1");
    model.component("comp1").physics("ewfd2").feature().copy("imp1", "ewfd/imp1");
    model.component("comp1").physics("ewfd2").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ewfd2").feature("ge1").label("\u9891\u7387");
    model.component("comp1").physics("ewfd2").feature("ge1").setIndex("name", "freq1", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge1")
         .setIndex("equation", "intop1(real(withsol('sol1',ewfd.Er)))-intop1(real(ewfd2.Er))", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge1")
         .setIndex("initialValueU", "withsol('sol1',ewfd.freq)", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge1").setIndex("description", "\u9891\u7387", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge1").set("valueType", "real");
    model.component("comp1").physics("ewfd2").feature("ge1").set("DependentVariableQuantity", "frequency");
    model.component("comp1").physics("ewfd2").feature("ge1").set("SourceTermQuantity", "electricfield");
    model.component("comp1").physics("ewfd2").feature().duplicate("ge2", "ge1");
    model.component("comp1").physics("ewfd2").feature("ge2").label("\u589e\u76ca");
    model.component("comp1").physics("ewfd2").feature("ge2").setIndex("name", "kappa_QW", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge2")
         .setIndex("equation", "intop1(imag(withsol('sol1',ewfd.Er)))-intop1(imag(ewfd2.Er))", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge2").setIndex("initialValueU", "kappa_QW_gain", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge2")
         .setIndex("description", "\u6298\u5c04\u7387\uff0cQW\uff0c\u865a\u90e8", 0, 0);
    model.component("comp1").physics("ewfd2").feature("ge2").set("DependentVariableQuantity", "dimensionless");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledvariables", new String[]{"var1"});
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ewfd"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("st1").set("splitcomplex", true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd2)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u589e\u76ca\u8ba1\u7b97 (ewfd2)");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "freq1", 0);
    model.result().numerical("gev2").setIndex("unit", "THz", 0);
    model.result().numerical("gev2").setIndex("expr", "c_const/freq1", 1);
    model.result().numerical("gev2").setIndex("unit", "nm", 1);
    model.result().numerical("gev2").setIndex("descr", "\u6ce2\u957f", 1);
    model.result().numerical("gev2").setIndex("expr", "kappa_QW", 2);
    model.result().numerical("gev2").setIndex("expr", "-2*kappa_QW*k0", 3);
    model.result().numerical("gev2").setIndex("unit", "1/cm", 3);
    model.result().numerical("gev2").setIndex("descr", "\u9608\u503c\u6750\u6599\u589e\u76ca", 3);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u589e\u76ca\u8ba1\u7b97 (ewfd2)");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_csel2_dom");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "2");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").set("titletype", "none");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg2").run();

    model.param().set("t_GaAs_substrate", "1500[nm]");
    model.param().descr("t_GaAs_substrate", "GaAs \u57fa\u5e95");

    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("r11").label("GaAs \u57fa\u5e95");
    model.component("comp1").geom("geom1").feature("r11").set("size", new String[]{"d_outer/2", "t_GaAs_substrate"});
    model.component("comp1").geom("geom1").feature("r11").set("pos", new String[]{"0", "-t_GaAs_substrate"});
    model.component("comp1").geom("geom1").feature("r11").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r11");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "d_outer");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1")
         .set("pos", new String[]{"0", "t_bottom_DBR+t_GaAs_cavity+t_QW/2"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").label("\u7a7a\u6c14\u57df");
    model.component("comp1").material("mat9").selection().set(1);
    model.component("comp1").material("mat9").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").set("n", new String[]{"n_air"});

    model.component("comp1").physics().create("ewfd3", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/ewfd3", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ewfd3", true);

    model.component("comp1").physics("ewfd3").prop("outofplanewavenumber").set("mFloquet", 1);
    model.component("comp1").physics("ewfd3").create("ffd1", "FarFieldDomain", 2);
    model.component("comp1").physics("ewfd3").feature("ffd1").selection().set(1);
    model.component("comp1").physics("ewfd3").feature("ffd1").feature("ffc1").selection().geom("geom1", 1);
    model.component("comp1").physics("ewfd3").feature("ffd1").feature("ffc1").selection().set(356, 357);
    model.component("comp1").physics("ewfd3").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd3").feature("sctr1").selection().set(356, 357);

    model.component("comp1").mesh("mesh1").create("size7", "Size");
    model.component("comp1").mesh("mesh1").feature("size7").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size7").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("size7").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size7").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size7").set("hmax", "lda0/6");
    model.component("comp1").mesh("mesh1").feature().move("size7", 7);
    model.component("comp1").mesh("mesh1").run();

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
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd2", true);
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd3", true);
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 1);
    model.study("std3").feature("eig").set("shift", "f0");
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd", false);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"ewfd"});
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd2", false);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"ewfd", "ewfd2"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ewfd3)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "none");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").set("data", "dset3");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u7279\u5f81\u9891\u7387 (ewfd3)");
    model.result().numerical("gev3").set("data", "dset3");
    model.result().numerical("gev3").set("expr", new String[]{"ewfd3.freq", "ewfd3.Qfactor"});
    model.result().numerical("gev3").set("unit", new String[]{"THz", "1"});
    model.result().table().create("tbl3", "Table");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").run();
    model.result().numerical("gev3").setResult();
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").label("\u4e8c\u7ef4\u8fdc\u573a (ewfd3)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("legend", "on");
    model.result("pg4").feature("rp1").set("phidisc", "180");
    model.result("pg4").feature("rp1").set("expr", new String[]{"ewfd3.normEfar"});
    model.result("pg4").feature("rp1").create("exp1", "Export");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u8fdc\u573a (ewfd3)");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("view", "new");
    model.result("pg5").set("edges", "off");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("data", "dset3");
    model.result("pg5").feature("rp1").set("expr", new String[]{"ewfd3.normEfar"});
    model.result("pg5").feature("rp1").set("colorexpr", new String[]{"ewfd3.normEfar"});
    model.result("pg5").feature("rp1").set("useradiusascolor", true);
    model.result("pg5").feature("rp1").set("directivityexpr", new String[]{"ewfd3.normEfar^2"});
    model.result("pg5").feature("rp1").set("thetadisc", "180");
    model.result("pg5").feature("rp1").set("phidisc", "90");
    model.result("pg5").feature("rp1").set("directivity", "on");
    model.result("pg5").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg5").feature("rp1").create("exp1", "Export");
    model.result("pg5").feature("rp1").feature("exp1").setIndex("expr", "comp1.ewfd3.theta", 0);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.ewfd3.theta", 0);
    model.result("pg3").run();
    model.result().numerical("gev3").setIndex("expr", "ewfd3.lambda0", 2);
    model.result().numerical("gev3").setIndex("unit", "nm", 2);
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").appendResult();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").run();
    model.result("pg4").feature("rp1").set("phidisc", 540);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("thetadisc", 540);
    model.result("pg5").run();

    model.component("comp1").physics().create("ewfd4", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/ewfd4", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ewfd4", true);
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd4", true);

    model.component("comp1").physics("ewfd4").prop("outofplanewavenumber").set("mFloquet", 1);
    model.component("comp1").physics("ewfd4").feature("init1")
         .set("E4", new String[]{"withsol('sol3',ewfd3.Er)", "withsol('sol3',ewfd3.Er)", "withsol('sol3',ewfd3.Er)"});
    model.component("comp1").physics("ewfd4").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ewfd4").feature("ge1").label("\u9891\u7387");
    model.component("comp1").physics("ewfd4").feature("ge1").setIndex("name", "freq2", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge1")
         .setIndex("equation", "intop1(real(withsol('sol3',ewfd3.Er)))-intop1(real(ewfd4.Er))", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge1")
         .setIndex("initialValueU", "withsol('sol3',ewfd3.freq)", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge1").setIndex("description", "\u9891\u7387", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge1").set("valueType", "real");
    model.component("comp1").physics("ewfd4").feature("ge1").set("DependentVariableQuantity", "frequency");
    model.component("comp1").physics("ewfd4").feature("ge1").set("SourceTermQuantity", "electricfield");
    model.component("comp1").physics("ewfd4").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("ewfd4").feature("ge2").label("\u589e\u76ca");
    model.component("comp1").physics("ewfd4").feature("ge2").setIndex("name", "kappa_QW2", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge2")
         .setIndex("equation", "intop1(imag(withsol('sol3',ewfd3.Er)))-intop1(imag(ewfd4.Er))", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge2").setIndex("initialValueU", "kappa_QW_gain", 0, 0);
    model.component("comp1").physics("ewfd4").feature("ge2")
         .setIndex("description", "\u6298\u5c04\u7387\uff0cQW\uff0c\u865a\u90e8", 0, 0);

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"ewfd"});
    model.study("std4").feature("stat").setSolveFor("/physics/ewfd2", false);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"ewfd", "ewfd2", "ewfd3"});
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u573a (ewfd4)");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("hght1", "Height");
    model.result("pg6").run();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("\u589e\u76ca\u8ba1\u7b97 (ewfd4)");
    model.result().numerical("gev4").set("data", "dset4");
    model.result().numerical("gev4").setIndex("expr", "freq2", 0);
    model.result().numerical("gev4").setIndex("unit", "THz", 0);
    model.result().numerical("gev4").setIndex("expr", "c_const/freq2", 1);
    model.result().numerical("gev4").setIndex("unit", "nm", 1);
    model.result().numerical("gev4").setIndex("descr", "\u6ce2\u957f", 1);
    model.result().numerical("gev4").setIndex("expr", "kappa_QW2", 2);
    model.result().numerical("gev4").setIndex("expr", "-2*kappa_QW2*k0", 3);
    model.result().numerical("gev4").setIndex("unit", "1/cm", 3);
    model.result().numerical("gev4").setIndex("descr", "\u9608\u503c\u6750\u6599\u589e\u76ca", 3);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u589e\u76ca\u8ba1\u7b97 (ewfd4)");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();

    model.component("comp1").physics("ewfd").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);
    model.component("comp1").physics("ewfd2").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);

    model.study("std1").feature("eig").setSolveFor("/physics/ewfd3", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"ewfd2", "ewfd3"});
    model.study("std1").feature("eig").setSolveFor("/physics/ewfd4", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"ewfd2", "ewfd3", "ewfd4"});
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ewfd", "ewfd3", "ewfd4"});
    model.study("std3").feature("eig").setSolveFor("/physics/ewfd4", false);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"ewfd", "ewfd2", "ewfd4"});

    model
         .title("\u5782\u76f4\u8154\u9762\u53d1\u5c04\u6fc0\u5149\u5668 (VCSEL) \u7684\u9608\u503c\u589e\u76ca\u8ba1\u7b97");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u7279\u5f81\u9891\u7387\u7814\u7a76\u6765\u786e\u5b9a\u6c27\u5316\u9650\u5236 GaAs \u578b\u5782\u76f4\u8154\u9762\u53d1\u5c04\u6fc0\u5149\u5668 (VCSEL) \u7684\u8c10\u632f\u9891\u7387\u548c\u9608\u503c\u589e\u76ca\u3002\n\n\u4eff\u771f\u5206\u4e24\u4e2a\u6b65\u9aa4\u6267\u884c\uff0c\u9996\u5148\u6267\u884c\u7684\u5e38\u89c4\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u4e3a\u540e\u7eed\u7684\u975e\u7ebf\u6027\u7279\u5f81\u9891\u7387\u5206\u6790\u786e\u5b9a\u826f\u597d\u7684\u521d\u59cb\u503c\u3002\n\n\u672c\u4f8b\u9488\u5bf9\u4e0d\u540c\u5668\u4ef6\u51e0\u4f55\u5f62\u72b6\u8ba1\u7b97\u8c10\u632f\u9891\u7387\u548c\u9608\u503c\u589e\u76ca\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u8bba\u6587\u6536\u96c6\u7684\u7ed3\u679c\uff08\u4f7f\u7528\u4e0d\u540c\u7684\u8ba1\u7b97\u65b9\u6cd5\u5bf9\u8fd9\u4e2a\u57fa\u51c6\u95ee\u9898\u8fdb\u884c\u8ba1\u7b97\uff09\u8fdb\u884c\u6bd4\u8f83\uff0c\u4e24\u8005\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("vertical_cavity_surface_emitting_laser.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
