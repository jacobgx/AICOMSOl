/*
 * engine_coolant_properties.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:28 by COMSOL 6.3.0.290. */
public class engine_coolant_properties {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
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

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"ethylene glycol", "107-21-1", "C2H6O2", "COMSOL"}, {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp1").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "UNIFAC");
    model.thermodynamics().feature("pp1").set("EOS", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "UNIFAC");
    model.thermodynamics().feature("pp1").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("EOS", "IdealGas");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VLSurfaceTension", "Ideal");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp1").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp1").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp1").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp1").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp1").set("LiqVolume", "IdealMixing");
    model.thermodynamics().feature("pp1").set("PoyntingFactor", "off");
    model.thermodynamics().feature("pp1")
         .set("property", new String[]{"Automatic", "Ideal", "KineticTheory", "Brokaw", "WesselinghKrishna", "Automatic", "None", "Ideal", "LogarithmicMassMixing", "IdealMixing", 
         "off"});
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u5bc6\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "Density_ethylene_glycol_water_Liquid11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("compounds", new String[]{"ethylene glycol", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Liquid");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethylene_glycol", "1", "\u8d28\u91cf\u5206\u6570 ethylene glycol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethylene_glycol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Density_ethylene_glycol_water_Liquid11_Dtemperature", "Density_ethylene_glycol_water_Liquid11_Dpressure", "Density_ethylene_glycol_water_Liquid11_Dmassfraction_ethylene_glycol", "Density_ethylene_glycol_water_Liquid11_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("funcname", "HeatCapacityCp_ethylene_glycol_water_Liquid12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("compounds", new String[]{"ethylene glycol", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Liquid");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethylene_glycol", "1", "\u8d28\u91cf\u5206\u6570 ethylene glycol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethylene_glycol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_ethylene_glycol_water_Liquid12_Dtemperature", "HeatCapacityCp_ethylene_glycol_water_Liquid12_Dpressure", "HeatCapacityCp_ethylene_glycol_water_Liquid12_Dmassfraction_ethylene_glycol", "HeatCapacityCp_ethylene_glycol_water_Liquid12_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "ThermalConductivity_ethylene_glycol_water_Liquid13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("compounds", new String[]{"ethylene glycol", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Liquid");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethylene_glycol", "1", "\u8d28\u91cf\u5206\u6570 ethylene glycol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethylene_glycol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"ThermalConductivity_ethylene_glycol_water_Liquid13_Dtemperature", "ThermalConductivity_ethylene_glycol_water_Liquid13_Dpressure", "ThermalConductivity_ethylene_glycol_water_Liquid13_Dmassfraction_ethylene_glycol", "ThermalConductivity_ethylene_glycol_water_Liquid13_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("funcname", "Viscosity_ethylene_glycol_water_Liquid14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("compounds", new String[]{"ethylene glycol", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Liquid");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethylene_glycol", "1", "\u8d28\u91cf\u5206\u6570 ethylene glycol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethylene_glycol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"Viscosity_ethylene_glycol_water_Liquid14_Dtemperature", "Viscosity_ethylene_glycol_water_Liquid14_Dpressure", "Viscosity_ethylene_glycol_water_Liquid14_Dmassfraction_ethylene_glycol", "Viscosity_ethylene_glycol_water_Liquid14_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").tag("mat_singlephase1");
    model.thermodynamics().feature("pp1").feature("mat_singlephase1").set("funcname", "Densitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase2").tag("mat_singlephase2");
    model.thermodynamics().feature("pp1").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp1");
    model.thermodynamics().feature("pp1").feature("singlephase3").tag("mat_singlephase3");
    model.thermodynamics().feature("pp1").feature("mat_singlephase3").set("funcname", "ThermalConductivitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase4").tag("mat_singlephase4");
    model.thermodynamics().feature("pp1").feature("mat_singlephase4").set("funcname", "Viscositypp1");
    model.thermodynamics()
         .createMaterial("comp1", "pp1", "Liquid", new String[]{"ethylene glycol", "water"}, new String[]{"0.5", "0.5"}, new String[]{}, new String[][]{{"density", "Densitypp1"}, {"heatcapacitycp", "HeatCapacityCppp1"}, {"thermalconductivity", "ThermalConductivitypp1"}, {"viscosity", "Viscositypp1"}}, "Thermodynamics", new String[][]{{"8", "273", "373", "20", "101325", "201325", "15"}, 
         {"72", "273", "373", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"64", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mass"});

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("w_EG", "0.527", "\u8d28\u91cf\u5206\u6570\uff0c\u4e59\u4e8c\u9187");
    model.param().set("w_W", "1-w_EG", "\u8d28\u91cf\u5206\u6570\uff0c\u6c34");
    model.param().set("Vel", "1.0[m/s]", "\u7ba1\u9053\u5165\u53e3\u901f\u5ea6");
    model.param().set("r_p", "1.5[cm]", "\u7ba1\u534a\u5f84");
    model.param().set("l_p", "20[cm]", "\u7ba1\u957f");
    model.param().set("r_c", "10[cm]", "\u8154\u534a\u5f84");
    model.param().set("l_c", "10[cm]", "\u8154\u957f\u5ea6");
    model.param().set("zpos_c", "l_p/4", "\u6cbf\u7ba1\u9053\u65b9\u5411\u7684\u8154\u4f4d\u7f6e");
    model.param().set("r_s1", "3[cm]", "\u56fa\u4f53\u90e8\u5206 1\uff0c\u534a\u5f84");
    model.param().set("l_s1", "0.5[cm]", "\u56fa\u4f53\u90e8\u5206 1\uff0c\u957f\u5ea6");
    model.param().set("r_s2", "0.35[cm]", "\u56fa\u4f53\u90e8\u5206 2\uff0c\u534a\u5f84");
    model.param().set("l_s2", "l_p-zpos_s", "\u56fa\u4f53\u90e8\u5206 2\uff0c\u957f\u5ea6");
    model.param()
         .set("zpos_s", "zpos_c+0.3*l_c", "\u6cbf\u7ba1\u9053\u65b9\u5411\u7684\u56fa\u4f53\u90e8\u5206\u4f4d\u7f6e");
    model.param().set("Tc", "353.15[K]", "\u51b7\u5374\u6db2\u6e29\u5ea6");
    model.param().set("pRef", "2[atm]", "\u51b7\u5374\u6db2\u538b\u529b");
    model.param().set("n", "0", "\u76f8\u5206\u6570");
    model.param().set("P0", "50[kW]", "\u71c3\u70e7\u70ed\u901a\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_p", "l_p"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r_c", "l_c"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "zpos_c"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"r_s1", "l_s1"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "zpos_s"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"r_s2", "l_s2"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "zpos_s"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r3", "r4");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 5);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni2", 6, 7, 9, 10);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.3[cm]");
    model.component("comp1").geom("geom1").runPre("fin");

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6df7\u5408\u7269\u5c5e\u6027\u53c2\u6570\u5316");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0 0.527 1", 0);
    model.study("std1").feature("stat").setIndex("pname", "Tc", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(273,10,473)", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5bc6\u5ea6");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"Densitypp1(temperature, pressure, massfraction_ethylene_glycol, massfraction_water)"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5bc6\u5ea6 1"});
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").setIndex("expr", "Densitypp1(Tc,pRef,w_EG,w_W)", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "", 0);
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("autodescr", false);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg1").set("ylabel", "\u5bc6\u5ea6 (kg/m<sup>3</sup>)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9ecf\u5ea6");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"Viscositypp1(temperature, pressure, massfraction_ethylene_glycol, massfraction_water)"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u9ecf\u5ea6 1"});
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").setIndex("expr", "Viscositypp1(Tc,pRef,w_EG,w_W)", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "", 0);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg2").set("ylabel", "\u9ecf\u5ea6 (Pa*s)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5bfc\u70ed\u7cfb\u6570");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"ThermalConductivitypp1(temperature, pressure, massfraction_ethylene_glycol, massfraction_water)"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u5bfc\u70ed\u7cfb\u6570 1"});
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").setIndex("expr", "ThermalConductivitypp1(Tc,pRef,w_EG,w_W)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "", 0);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u5bfc\u70ed\u7cfb\u6570 (W/(m*K))");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u70ed\u5bb9");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"HeatCapacityCppp1(temperature, pressure, massfraction_ethylene_glycol, massfraction_water)"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u70ed\u5bb9 (Cp) 1"});
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").feature("glob1").setIndex("expr", "HeatCapacityCppp1(Tc,pRef,w_EG,w_W)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "", 0);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("autodescr", false);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u70ed\u5bb9 (J/(kg*K))");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();

    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature().remove("flashcalc1");
    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("funcname", "flashcalc1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("property", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("propertydescr", "\u5e73\u8861\u8ba1\u7b97");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("dvars", new String[]{"0", "0", "0", "0"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("compounds", new String[]{"ethylene glycol", "water"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("functions", new String[][]{{"Flash1_1_PhaseExist_Vapor", "1", "Presence of Vapor phase"}, 
         {"Flash1_1_PhaseExist_Liquid", "1", "Presence of Liquid phase"}, 
         {"Flash1_1_Temperature", "K", "Temperature"}, 
         {"Flash1_1_PhaseAmount_Vapor", "mol", "Amount in Vapor phase"}, 
         {"Flash1_1_PhaseAmount_Liquid", "mol", "Amount in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_ethylene_glycol", "mol/mol", "Fraction of ethylene glycol in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_water", "mol/mol", "Fraction of water in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_ethylene_glycol", "mol/mol", "Fraction of ethylene glycol in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_water", "mol/mol", "Fraction of water in Liquid phase"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("cond1", new String[]{"pressure"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("cond2", new String[]{"phasefraction", "mole", "Vapor"});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("inphase", "Flash1_1_PhaseExist");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("temperature", "Flash1_1_Temperature");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("pressure", "Flash1_1_Pressure");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("amounts", "Flash1_1_PhaseAmount");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("composition", "Flash1_1_PhaseComposition");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("soltype", "undefined");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("phases", new String[]{"Vapor", "Liquid"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("args", new String[][]{{"pressure", "Pa", "\u538b\u529b"}, 
         {"phasefraction", "mol/mol", "Vapor \u6469\u5c14\u5206\u6570"}, 
         {"ethylene_glycol", "mol", "\u6570\u91cf ethylene glycol"}, 
         {"water", "mol", "\u6570\u91cf water"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("plotargs", new String[][]{{"pressure", "101325", "101325"}, 
         {"phasefraction", "0", "0"}, 
         {"ethylene_glycol", "0", "0"}, 
         {"water", "0", "0"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("derivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivativeIndices", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("plotfunction", "Flash1_1_PhaseExist_Vapor");
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u76f8\u5305\u7edc\u7ebf");
    model.func("an1").set("funcname", "T_x_y");
    model.func("an1").set("expr", "Flash1_1_Temperature(p,n,w_EG,w_W)");
    model.func("an1").set("args", "p, n, w_EG, w_W");
    model.func("an1").setIndex("argunit", "Pa", 0);
    model.func("an1").setIndex("argunit", 1, 1);
    model.func("an1").setIndex("argunit", "mol/mol", 2);
    model.func("an1").setIndex("argunit", "mol/mol", 3);
    model.func("an1").set("fununit", "K");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "", 1);
    model.study("std2").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "", 1);
    model.study("std2").feature("stat").setIndex("pname", "Vel", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("stat").setIndex("punit", "m/s", 2);
    model.study("std2").feature("stat").setIndex("pname", "Vel", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "", 2);
    model.study("std2").feature("stat").setIndex("punit", "m/s", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 0);
    model.study("std2").feature("stat").setIndex("pname", "n", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "0 1", 1);
    model.study("std2").feature("stat").setIndex("pname", "pRef", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "1[atm] 2[atm]", 2);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u76f8\u5305\u7edc\u7ebf\u53c2\u6570\u5316");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u6469\u5c14\u5206\u6570\u4e59\u4e8c\u9187");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "T_x_y(pRef, n, w_EG, w_W)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "", 0);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "level3");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").label("\u76f8\u5305\u7edc\u7ebf");

    model.component("comp1").material("pp1mat1").selection().set(1);

    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "Vel");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(11);
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(2);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "Tc");
    model.component("comp1").physics("ht").feature("ifl1").set("specifyUpstreamAbsolutePressure", true);
    model.component("comp1").physics("ht").feature("ifl1").set("pustr", "pRef");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(11);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(18);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "HeatRate");
    model.component("comp1").physics("ht").feature("hf1").set("P0", "P0");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").selection().set(2);

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u6c34");
    model.study("std3").create("stat2", "Stationary");
    model.study("std3").feature("stat2").set("useparam", true);
    model.study("std3").feature("stat2").setIndex("pname", "w_EG", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat2").setIndex("punit", "", 0);
    model.study("std3").feature("stat2").setIndex("pname", "w_EG", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat2").setIndex("punit", "", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", 0, 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s2").feature("se1").set("segstabacc", "segaacc");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u901f\u5ea6 (spf)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u538b\u529b (spf)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset3");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(5, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("line1", "Line");
    model.result("pg9").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("smooth", "internal");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("data", "parent");
    model.result("pg9").feature("line1").feature().create("hght1", "Height");
    model.result("pg9").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg9").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg9").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6e29\u5ea6 (ht)");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "nitf1.T");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg11").feature().create("surf2", "Surface");
    model.result("pg11").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg11").feature("surf2").set("showsolutionparams", "on");
    model.result("pg11").feature("surf2").set("solutionparams", "parent");
    model.result("pg11").feature("surf2").set("expr", "nitf1.T");
    model.result("pg11").feature("surf2").set("smooth", "internal");
    model.result("pg11").feature("surf2").set("showsolutionparams", "on");
    model.result("pg11").feature("surf2").set("data", "parent");
    model.result("pg11").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf2").feature("sel1").selection().set(2);
    model.result("pg11").feature("surf2").set("inheritplot", "surf1");
    model.result("pg11").feature().create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("solutionparams", "parent");
    model.result("pg11").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg11").feature("arws1").set("xnumber", 30);
    model.result("pg11").feature("arws1").set("ynumber", 30);
    model.result("pg11").feature("arws1").set("arrowtype", "cone");
    model.result("pg11").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("data", "parent");
    model.result("pg11").feature("arws1").feature().create("col1", "Color");
    model.result("pg11").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg11").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg11").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg6").run();
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").set("startangle", 0);
    model.result().dataset("rev2").set("revangle", 360);
    model.result().dataset().duplicate("rev3", "rev1");
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection().all();
    model.result().dataset("rev3").selection().geom("geom1", 1);
    model.result().dataset("rev3").selection()
         .set(1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg8").set("edges", false);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("colortable", "Passiflora");
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("data", "rev2");
    model.result("pg8").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf2").feature("mtrl1").set("family", "steelanodized");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").create("sel1", "Selection");
    model.result("pg8").feature("surf2").feature("sel1").selection().set(2);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").set("data", "rev3");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("sel1").selection().set(1);
    model.result("pg8").feature("surf3").feature("sel1").set("evalstartcap", false);
    model.result("pg8").feature("surf3").feature("sel1").set("evalendcap", false);
    model.result("pg8").run();
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg10");
    model.result().remove("pg11");
    model.result("pg8").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset3");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("rangecolormax", 400);
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").label("\u6e29\u5ea6");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6e29\u5ea6 (K)");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "r (m)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u6e29\u5ea6 (K)");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");

    return model;
  }

  public static Model run3(Model model) {
    model.study("std4").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std4").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "w_EG", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "", 1);
    model.study("std4").feature("stat").setIndex("pname", "w_W", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "", 1);
    model.study("std4").feature("stat").setIndex("plistarr", 0.527, 0);
    model.study("std4").feature("stat").setIndex("pname", "Vel", 1);
    model.study("std4").feature("stat").setIndex("plistarr", 1, 1);
    model.study("std4").feature().duplicate("stat1", "stat");
    model.study("std4").feature("stat1").setIndex("plistarr", 1.15, 1);
    model.study("std4").feature("stat").set("useinitsol", true);
    model.study("std4").feature("stat").set("initmethod", "sol");
    model.study("std4").feature("stat").set("initstudy", "std3");
    model.study("std4").showAutoSequences("all");

    model.sol("sol5").feature("s1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol5").feature("s2").feature("se1").set("segstabacc", "segaacc");

    model.study("std4").label("\u7814\u7a76 4\uff1a\u4e59\u4e8c\u9187\u548c\u6c34");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();
    model.sol("sol5").feature("su1").label("Vel = 1.0 m/s");
    model.sol("sol5").label("Vel = 1.15 m/s");

    model.result("pg10").run();
    model.result("pg10").set("data", "dset6");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset5");
    model.result("pg10").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "r_c*0.5", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "r_c*0.5", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 1, 1, 1);
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset6");
    model.result().dataset().duplicate("cln3", "cln2");
    model.result().dataset("cln3").set("data", "dset5");
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").set("data", "cln1");
    model.result("pg11").feature("lngr1").set("expr", "ht.Cp");
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "manual");
    model.result("pg11").feature("lngr1").setIndex("legends", "\u6c34", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("lngr2", "lngr1");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").set("data", "cln2");
    model.result("pg11").feature("lngr2").setIndex("legends", "\u4e59\u4e8c\u9187/\u6c34", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("lngr3", "lngr2");
    model.result("pg11").run();
    model.result("pg11").feature("lngr3").set("data", "cln3");
    model.result("pg11").feature("lngr3").setIndex("legends", "\u4e59\u4e8c\u9187/\u6c34\uff0cVel = 1.15 m/s", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").set("legendpos", "middleright");
    model.result("pg11").set("titletype", "none");
    model.result("pg11").label("\u70ed\u5bb9\uff0c\u8154\u622a\u7ebf");
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").set("data", "dset6");
    model.result().numerical("av1").selection().set(1);
    model.result().numerical("av1").setIndex("expr", "ht.rho", 0);
    model.result().numerical("av1").setIndex("expr", "ht.Cp", 1);
    model.result().numerical("av1").setIndex("expr", "ht.krr", 2);
    model.result().numerical("av1").setIndex("expr", "spf.mu", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();

    model.param().set("rhoC", "1010[kg/m^3]");
    model.param().descr("rhoC", "\u5e73\u5747\u6052\u5b9a\u5bc6\u5ea6");
    model.param().set("CpC", "3486[J/kg/K]");
    model.param().descr("CpC", "\u5e73\u5747\u6052\u5b9a\u70ed\u5bb9");
    model.param().set("kC", "0.574[W/m/K]");
    model.param().descr("kC", "\u5e73\u5747\u6052\u5b9a\u70ed\u5bfc\u7387");
    model.param().set("muC", "9.07e-4[Pa*s]");
    model.param().descr("muC", "\u5e73\u5747\u6052\u5b9a\u9ecf\u5ea6");

    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1")
         .set("k", new String[]{"kC", "0", "0", "0", "kC", "0", "0", "0", "kC"});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rhoC");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "CpC");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "muC");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std5").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std5").feature("stat").set("useinitsol", true);
    model.study("std5").feature("stat").set("initmethod", "sol");
    model.study("std5").feature("stat").set("initstudy", "std3");
    model.study("std5").label("\u7814\u7a76 5\uff1a\u4e59\u4e8c\u9187\u548c\u6c34\uff0c\u6052\u5b9a\u5c5e\u6027");
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg10").run();
    model.result("pg10").set("data", "dset7");
    model.result("pg10").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(6);
    model.result().numerical("pev1").setIndex("unit", "", 0);
    model.result().numerical("pev1").setIndex("descr", "", 0);
    model.result().numerical("pev1").set("data", "dset3");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl2");
    model.result().numerical("pev1").setResult();
    model.result().numerical("pev1").set("data", "dset6");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl3");
    model.result().numerical("pev1").setResult();
    model.result().numerical("pev1").set("data", "dset5");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl4");
    model.result().numerical("pev1").setResult();
    model.result().numerical("pev1").set("data", "dset7");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl5");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("av2", "AvLine");
    model.result().numerical("av2").set("intsurface", true);
    model.result().numerical("av2").selection().set(2);
    model.result().numerical("av2").setIndex("expr", "p", 0);
    model.result().numerical("av2").setIndex("unit", "", 0);
    model.result().numerical("av2").setIndex("descr", "", 0);
    model.result().numerical("av2").set("data", "dset3");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").appendResult();
    model.result().numerical("av2").set("data", "dset6");
    model.result().numerical("av2").set("table", "tbl3");
    model.result().numerical("av2").appendResult();
    model.result().numerical("av2").set("data", "dset5");
    model.result().numerical("av2").set("table", "tbl4");
    model.result().numerical("av2").appendResult();
    model.result().numerical("av2").set("data", "dset7");
    model.result().numerical("av2").set("table", "tbl5");
    model.result().numerical("av2").appendResult();
    model.result().numerical().create("av3", "AvLine");
    model.result().numerical("av3").set("intsurface", true);
    model.result().numerical("av3").selection().set(11);
    model.result().numerical("av3").setIndex("expr", "ht.rho", 0);
    model.result().numerical("av3").setIndex("unit", "", 0);
    model.result().numerical("av3").setIndex("descr", "", 0);
    model.result().numerical("av3").set("data", "dset3");
    model.result().numerical("av3").set("table", "tbl2");
    model.result().numerical("av3").appendResult();
    model.result().numerical("av3").set("data", "dset6");
    model.result().numerical("av3").set("table", "tbl3");
    model.result().numerical("av3").appendResult();
    model.result().numerical("av3").set("data", "dset5");
    model.result().numerical("av3").set("table", "tbl4");
    model.result().numerical("av3").appendResult();
    model.result().numerical("av3").set("data", "dset7");
    model.result().numerical("av3").set("table", "tbl5");
    model.result().numerical("av3").appendResult();

    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "from_mat");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "from_mat");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "from_mat");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "from_mat");

    model.result("pg10").run();
    model.result("pg10").set("data", "dset6");
    model.result("pg10").run();

    model.title("\u53d1\u52a8\u673a\u51b7\u5374\u6db2\u5c5e\u6027");

    model
         .description("\u6c7d\u8f66\u7684\u53d1\u52a8\u673a\u7f38\u4f53\u5e26\u6709\u4e00\u4e2a\u51b7\u5374\u5939\u5957\uff0c\u7528\u6765\u6d88\u9664\u71c3\u70e7\u4ea7\u751f\u7684\u591a\u4f59\u70ed\u91cf\u3002\u8fd9\u4e2a\u51b7\u5374\u5939\u5957\u7531\u6c14\u7f38\u4f53\u548c\u6c14\u7f38\u76d6\u4e2d\u7684\u5f00\u653e\u7a7a\u95f4\u7ec4\u6210\u3002\u5f53\u53d1\u52a8\u673a\u8fd0\u884c\u65f6\uff0c\u51b7\u5374\u6db2\u901a\u8fc7\u5939\u5957\u6cf5\u5165\uff0c\u4ee5\u9632\u6b62\u53d1\u52a8\u673a\u8fc7\u70ed\u3002\u4f18\u5316\u6563\u70ed\u6548\u679c\u5bf9\u4e8e\u5c3d\u53ef\u80fd\u51cf\u5c11\u51b7\u5374\u6db2\u6cb8\u817e\u3001\u9632\u6b62\u53d1\u52a8\u673a\u6545\u969c\uff0c\u4ee5\u53ca\u8fd1\u6765\u51fa\u73b0\u7684\u901a\u8fc7\u4f59\u70ed\u56de\u6536\u63d0\u9ad8\u6574\u4f53\u6548\u7387\u975e\u5e38\u91cd\u8981\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u70ed\u529b\u5b66\u201d\u7279\u5f81\u6765\u8bc4\u4f30\u4e0d\u540c\u53d1\u52a8\u673a\u51b7\u5374\u6db2\u7684\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("engine_coolant_properties.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
