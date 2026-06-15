/*
 * continuous_casting_apparent_heat_capacity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:30 by COMSOL 6.3.0.290. */
public class continuous_casting_apparent_heat_capacity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Processing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_m", "1356[K]", "\u7194\u5316\u6e29\u5ea6");
    model.param().set("dT", "30[K]", "\u6e29\u5ea6\u8fc7\u6e21\u533a\u534a\u5bbd");
    model.param().set("dH", "205[kJ/kg]", "\u51dd\u56fa\u6f5c\u70ed");
    model.param().set("Cp_s", "380[J/(kg*K)]", "\u6052\u538b\u70ed\u5bb9\uff0c\u56fa\u76f8");
    model.param().set("Cp_l", "530[J/(kg*K)]", "\u6052\u538b\u70ed\u5bb9\uff0c\u6db2\u76f8");
    model.param().set("T_in", "1473[K]", "\u7194\u4f53\u5165\u53e3\u6e29\u5ea6");
    model.param().set("v_cast", "-1.6[mm/s]", "\u94f8\u9020\u901f\u5ea6");
    model.param().set("epsilon", "1e-3", "\u4f53\u79ef\u529b\u963b\u5c3c\u5e38\u6570");
    model.param().set("A_mush", "6e4[kg/(m^3*s)]", "\u4f53\u79ef\u529b\u963b\u5c3c\u5e38\u6570");
    model.param().set("h_br", "25[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u5236\u52a8\u73af");
    model.param().set("h_mold", "800[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u6a21\u5177");
    model.param().set("h_air", "10[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u7a7a\u6c14\u7167\u5c04");
    model.param().set("eps_s", "0.8", "\u8868\u9762\u53d1\u5c04\u7387\uff0c\u7a7a\u6c14\u7167\u5c04");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().all();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("alpha", "ht.alpha12", "\u6db2\u76f8\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("f", "(1-alpha)^2/(alpha^3+epsilon)", "\u4f53\u79ef\u529b\u524d\u56e0\u5b50");
    model.component("comp1").variable("var1")
         .set("Fr", "-f*A_mush*u", "\u4f53\u79ef\u529b\u5bc6\u5ea6\uff0cr \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Fz", "-f*A_mush*(w-v_cast)", "\u4f53\u79ef\u529b\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("D", "d(alpha,T)", "\u6e29\u5ea6\u4f9d\u5b58\u6027\uff0c\u6f5c\u70ed");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.065, 0.1});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -0.1});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.0625, 0.025});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, -0.125});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.11575, 1.4075});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, -1.5725});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 0.6, 0);
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 0.4, 1);
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 0.3675, 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.125, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.165, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.11575, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.165, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.0625, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.125, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.125, 4, 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u56fa\u6001\u91d1\u5c5e\u5408\u91d1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"0.0434[Pa*s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"200[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8500[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_s"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6db2\u6001\u91d1\u5c5e\u5408\u91d1");
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"0.0434[Pa*s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"200[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"8500[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp_l"});

    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"0", "0", "v_cast"});
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(15);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(2);
    model.component("comp1").physics("spf").feature("out1").set("BoundaryCondition", "Velocity");
    model.component("comp1").physics("spf").feature("out1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("out1").set("u0", new String[]{"0", "0", "v_cast"});
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(20, 21, 22);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"Fr", "0", "Fz"});

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "300[K]");

    model.component("comp1").physics("ht").feature("fluid1").create("phc1", "PhaseChangeMaterial", 2);
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("T_pc12", "T_m");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("dT_pc12", "2*dT");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("L_pc12", "dH");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase1", "mat1");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase2", "mat2");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(15);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(23);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_br");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(22);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_mold");
    model.component("comp1").physics("ht").feature("hf2").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf3").selection().set(20, 21);
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("h", "h_air");
    model.component("comp1").physics("ht").feature("hf3").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(2);
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 1);
    model.component("comp1").physics("ht").feature("sar1").selection().set(20, 21);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", "eps_s");
    model.component("comp1").physics("ht").feature("sar1").set("Tamb_src", "root.comp1.ampr1.T_amb");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("bl1");
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(16, 17, 18, 19, 20, 21);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "dT", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "300 100 50 30", 0);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("errestandadap", "adaption");
    model.study("std1").feature("stat2").set("meshadaptmethod", "modify");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("adDef").set("allowcoarsening", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "dT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "25 20 16 13 10", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol7").feature("s1").create("fc1", "FullyCoupled");

    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().dataset("dset4").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset4");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "nitf1.T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg5").feature().create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("solutionparams", "parent");
    model.result("pg5").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg5").feature("arws1").set("xnumber", 30);
    model.result("pg5").feature("arws1").set("ynumber", 30);
    model.result("pg5").feature("arws1").set("arrowtype", "cone");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("data", "parent");
    model.result("pg5").feature("arws1").feature().create("col1", "Color");
    model.result("pg5").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "mm/s");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.003, 0.05});
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u6db2\u76f8\u5206\u6570");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "alpha");
    model.result("pg6").feature("surf1").set("descr", "\u6db2\u76f8\u5206\u6570");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\u548c\u6d41\u7ebf");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("posmethod", "magnitude");
    model.result("pg7").feature("str1").set("mdist", new double[]{0.004, 0.08});
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u4f20\u5bfc\u70ed\u901a\u91cf");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "ht.dfluxMag");
    model.result("pg8").feature("surf1").set("descr", "\u4f20\u5bfc\u70ed\u901a\u91cf\u5927\u5c0f");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("posmethod", "magnitude");
    model.result("pg8").feature("str1").set("mdist", new double[]{0.004, 0.08});
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u901a\u8fc7\u5916\u8fb9\u754c\u7684\u4f20\u5bfc\u70ed\u901a\u91cf");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u901a\u8fc7\u5916\u8fb9\u754c\u7684\u4f20\u5bfc\u70ed\u901a\u91cf");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "z \u5750\u6807 (m)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6cd5\u5411\u4f20\u5bfc\u70ed\u901a\u91cf (W/m^2)");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().set(20, 21, 22, 23);
    model.result("pg9").feature("lngr1").set("expr", "ht.ndflux");
    model.result("pg9").feature("lngr1").set("descr", "\u6cd5\u5411\u4f20\u5bfc\u70ed\u901a\u91cf");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("xdatadescr", "z \u5750\u6807");
    model.result("pg9").feature("lngr1").set("recover", "pprint");
    model.result("pg9").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0.045, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", -0.42, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.085, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", -0.43, 1, 1);
    model.result().dataset("cln1").set("data", "dset4");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u6e29\u5ea6\u4f9d\u8d56\u6027\uff0c\u6f5c\u70ed");
    model.result("pg10").set("data", "cln1");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").set("expr", "D");
    model.result("pg10").feature("lngr1").set("descr", "\u6e29\u5ea6\u4f9d\u5b58\u6027\uff0c\u6f5c\u70ed");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").run();
    model.result("pg3").run();

    model.title("\u8fde\u94f8 - \u8868\u89c2\u70ed\u5bb9\u6cd5");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5728\u8fde\u94f8\u5de5\u827a\u4e2d\uff0c\u4ece\u7194\u878d\u91d1\u5c5e\u5230\u56fa\u6001\u91d1\u5c5e\u7684\u51b7\u5374\u51dd\u56fa\u8fc7\u7a0b\uff0c\u5176\u4e2d\u5305\u542b\u975e\u7b49\u6e29\u5c5e\u6027\u3001\u6e29\u5ea6\u5206\u5e03\u3001\u6d41\u573a\u548c\u76f8\u53d8\u3002\u672c\u4f8b\u4f7f\u7528\u8868\u89c2\u70ed\u5bb9\u6cd5\u5bf9\u76f8\u53d8\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("continuous_casting_apparent_heat_capacity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
