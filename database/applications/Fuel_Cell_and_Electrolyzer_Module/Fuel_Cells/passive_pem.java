/*
 * passive_pem.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class passive_pem {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("passive_pem_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9633\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u9634\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel2").set(5);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("GDL");
    model.component("comp1").selection("sel3").set(2, 4);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u819c");
    model.component("comp1").selection("sel4").set(3);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u9634\u6781 - \u7a7a\u6c14\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5")
         .set(46, 49, 56, 59, 66, 69, 76, 79, 86, 89, 96, 99, 106, 109, 116, 119, 126, 129, 136, 139);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u9633\u6781\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(1);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u9634\u6781\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(147);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u9633\u6781 GDE");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(32);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u9634\u6781 GDE");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(35);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u9633\u6781 GDL");
    model.component("comp1").selection("sel10").set(2);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u9634\u6781 GDL");
    model.component("comp1").selection("sel11").set(4);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel12").set(1, 5);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_gde", "1e-7[m]", "\u8584 GDE \u539a\u5ea6");
    model.param().set("V_cell", "1[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("T_amb", "25[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("RH_amb", "0.65", "\u9634\u6781\u76f8\u5bf9\u6e7f\u5ea6");
    model.param()
         .set("i0_refa", "1000[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("i0_refc", "10[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("Av", "1e7[1/m]", "\u8584 GDE \u7684\u6d3b\u6027\u6bd4\u8868\u9762\u79ef");
    model.param().set("htc", "50[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", false);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("fc").create("cc1", "CurrentCollector", 3);
    model.component("comp1").physics("fc").feature("cc1").selection().named("sel12");
    model.component("comp1").physics("fc").feature("cc1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("sel10");
    model.component("comp1").physics("fc").feature("h2gdl1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("sel11");
    model.component("comp1").physics("fc").feature("o2gdl1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("sel4");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("sel8");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "d_gde");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_refa");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("sel9");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "d_gde");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_refc");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("o2gasph1").set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").set("RH_hum", "RH_amb");
    model.component("comp1").physics("fc").feature("o2gasph1").set("T_hum", "T_amb");
    model.component("comp1").physics("fc").feature("icph1").feature("init1").set("initphil", "-0.1[V]");
    model.component("comp1").physics("fc").feature("ecph1").create("init2", "InitialValues", 3);
    model.component("comp1").physics("fc").feature("ecph1").feature("init2").selection().set(4, 5);
    model.component("comp1").physics("fc").feature("ecph1").feature("init2").set("initphis", "V_cell");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("sel6");
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().named("sel7");
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "V_cell");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel5");
    model.component("comp1").physics("ht").feature("hf1").selection()
         .set(39, 46, 49, 56, 59, 66, 69, 76, 79, 86, 89, 96, 99, 106, 109, 116, 119, 126, 129, 136, 139);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_amb");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().named("sel5");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.8);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "T_amb");
    model.component("comp1").physics("ht").create("sar2", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar2").selection().set(39);
    model.component("comp1").physics("ht").feature("sar2").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar2").set("epsilon_rad", 0.3);
    model.component("comp1").physics("ht").feature("sar2").set("Tamb", "T_amb");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"100"});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"25"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"700"});
    model.component("comp1").material("mat3").label("GDL");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u819c");
    model.component("comp1").material("mat4").selection().named("sel4");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"0.2"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"4000"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(39);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1.5e-3");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1e-3");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("sel5");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmax", "0.9e-3");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmin", "0.6e-3");
    model.component("comp1").mesh("mesh1").run("ftri3");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").label("\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6\u63a2\u9488");
    model.component("comp1").probe("bnd1").set("probename", "I_cell");
    model.component("comp1").probe("bnd1").selection().named("sel8");
    model.component("comp1").probe("bnd1").set("expr", "fc.iloc_th2gder1");
    model.component("comp1").probe("bnd1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").probe("bnd1").set("expr", "fc.iloc_th2gder1*Av*d_gde");
    model.component("comp1").probe().create("bnd2", "Boundary");
    model.component("comp1").probe("bnd2").set("intsurface", true);
    model.component("comp1").probe("bnd2").label("\u7535\u6c60\u5e73\u5747\u6e29\u5ea6\u63a2\u9488");
    model.component("comp1").probe("bnd2").set("probename", "T_avg");
    model.component("comp1").probe("bnd2").selection().named("sel9");
    model.component("comp1").probe("bnd2").set("expr", "T");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,-0.1,0.4)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "init");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subjtech", "onfirst");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");
    model.component("comp1").probe("bnd2").genResult("none");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u4f4d");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "fc.phis");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u6e29\u5ea6");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", "H_film+H_GDL+H_membrane/2");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u819c\u6e29\u5ea6");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "cpl1");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u819c\u7684\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "cpl1");
    model.result("pg6").feature("surf1").set("expr", "fc.Ilz");
    model.result("pg6").feature("surf1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6781\u5316\u56fe");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "V_cell", 0);
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "I_cell");
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6 vs. \u7535\u6d41\u5bc6\u5ea6");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "T_avg", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u9634\u6781\u5e73\u5747\u6e29\u5ea6", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "I_cell");
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").run();
    model.result("pg4").run();

    model
         .title("\u88ab\u52a8\u5f0f\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60\u4e2d\u7684\u6b27\u59c6\u635f\u8017\u548c\u6e29\u5ea6\u5206\u5e03");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6d88\u8d39\u7535\u5b50\u4ea7\u54c1\u5e94\u7528\u4e2d\u7684\u5c0f\u578b\u88ab\u52a8\u51b7\u5374\u8d28\u5b50\u4ea4\u6362\u819c (PEM) \u71c3\u6599\u7535\u6c60\u3002\u6a21\u578b\u5305\u542b\u7535\u5316\u5b66\u7535\u6d41\u548c\u4f20\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("passive_pem.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
