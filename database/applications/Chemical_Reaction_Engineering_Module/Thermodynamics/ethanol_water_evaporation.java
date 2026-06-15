/*
 * ethanol_water_evaporation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:28 by COMSOL 6.3.0.290. */
public class ethanol_water_evaporation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", new String[][]{{"wEth", "wW", "wN2"}});
    model.component("comp1").physics("tcs").prop("AdvancedSettings").set("UsePseudoTime", "1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.component("comp1").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 2);
    model.component("comp1").multiphysics("nirf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nirf1").set("Species_physics", "tcs");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nirf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("abv", "0.35", "0.35 Alcohol by volume");
    model.param().set("T0", "23[degC]", "296.15 K Initial temperature");
    model.param().set("p0", "1[atm]", "1.0133E5 Pa Initial pressure");
    model.param().set("relH", "0.3", "0.3 Relative humidity");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"ethanol", "64-17-5", "C2H6O", "COMSOL"}, 
         {"nitrogen", "7727-37-9", "N2", "COMSOL"}, 
         {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp1").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp1").set("EOSModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VLSurfaceTension", "Ideal");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp1").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp1").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp1").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp1").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp1").set("LiqVolume", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("tdep1", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp1").feature("tdep1").label("Ln \u84b8\u6c7d\u538b 1");
    model.thermodynamics().feature("pp1").feature("tdep1").set("funcname", "LnVaporPressure_ethanol11");
    model.thermodynamics().feature("pp1").feature("tdep1").set("property", "LnVaporPressure");
    model.thermodynamics().feature("pp1").feature("tdep1").set("propertydescr", "Ln \u84b8\u6c7d\u538b");
    model.thermodynamics().feature("pp1").feature("tdep1").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("tdep1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("tdep1").set("compounds", new String[]{"ethanol"});
    model.thermodynamics().feature("pp1").feature("tdep1").comments("REFPROP");
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[163.57 ,514.71]"}});
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("derivatives", new String[]{"LnVaporPressure_ethanol11_Dtemperature"});
    model.thermodynamics().feature("pp1").feature("tdep1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("tdep2", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp1").feature("tdep2").label("Ln \u84b8\u6c7d\u538b 2");
    model.thermodynamics().feature("pp1").feature("tdep2").set("funcname", "LnVaporPressure_water12");
    model.thermodynamics().feature("pp1").feature("tdep2").set("property", "LnVaporPressure");
    model.thermodynamics().feature("pp1").feature("tdep2").set("propertydescr", "Ln \u84b8\u6c7d\u538b");
    model.thermodynamics().feature("pp1").feature("tdep2").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("tdep2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("tdep2").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("tdep2")
         .comments("Riedel corresponding states method for vapor pressure of non-polar compounds, Riedel (1954) and its modifications for acids and alcohols, Vetere (1991)");
    model.thermodynamics().feature("pp1").feature("tdep2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[250.00 ,647.13]"}});
    model.thermodynamics().feature("pp1").feature("tdep2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp1").feature("tdep2")
         .set("derivatives", new String[]{"LnVaporPressure_water12_Dtemperature"});
    model.thermodynamics().feature("pp1").feature("tdep2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("tdep3", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp1").feature("tdep3").label("\u6c7d\u5316\u70ed 1");
    model.thermodynamics().feature("pp1").feature("tdep3").set("funcname", "HeatOfVaporization_ethanol13");
    model.thermodynamics().feature("pp1").feature("tdep3").set("property", "HeatOfVaporization");
    model.thermodynamics().feature("pp1").feature("tdep3").set("propertydescr", "\u6c7d\u5316\u70ed");
    model.thermodynamics().feature("pp1").feature("tdep3").set("unit", "J/kg");
    model.thermodynamics().feature("pp1").feature("tdep3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("tdep3").set("compounds", new String[]{"ethanol"});
    model.thermodynamics().feature("pp1").feature("tdep3").comments("REFPROP");
    model.thermodynamics().feature("pp1").feature("tdep3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[159.36 ,514.71]"}});
    model.thermodynamics().feature("pp1").feature("tdep3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp1").feature("tdep3")
         .set("derivatives", new String[]{"HeatOfVaporization_ethanol13_Dtemperature"});
    model.thermodynamics().feature("pp1").feature("tdep3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("tdep4", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp1").feature("tdep4").label("\u6c7d\u5316\u70ed 2");
    model.thermodynamics().feature("pp1").feature("tdep4").set("funcname", "HeatOfVaporization_water14");
    model.thermodynamics().feature("pp1").feature("tdep4").set("property", "HeatOfVaporization");
    model.thermodynamics().feature("pp1").feature("tdep4").set("propertydescr", "\u6c7d\u5316\u70ed");
    model.thermodynamics().feature("pp1").feature("tdep4").set("unit", "J/kg");
    model.thermodynamics().feature("pp1").feature("tdep4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("tdep4").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("tdep4").comments("Steam tables");
    model.thermodynamics().feature("pp1").feature("tdep4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[273.15 ,647.10]"}});
    model.thermodynamics().feature("pp1").feature("tdep4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp1").feature("tdep4")
         .set("derivatives", new String[]{"HeatOfVaporization_water14_Dtemperature"});
    model.thermodynamics().feature("pp1").feature("tdep4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature().create("pp2", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp2")
         .set("compoundlist", new String[][]{{"ethanol", "64-17-5", "C2H6O", "COMSOL"}, {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp2").set("phase_list", new String[][]{{"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp2").label("\u6db2\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp2").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp2").set("manager_version", "1.0");
    model.thermodynamics().feature("pp2").set("packagename", "pp2");
    model.thermodynamics().feature("pp2").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp2").set("managerindex", "0");
    model.thermodynamics().feature("pp2").set("packageid", "COMSOL2");
    model.thermodynamics().feature("pp2").set("ThermodynamicModel", "UNIQUAC");
    model.thermodynamics().feature("pp2").set("EOS", "IdealGas");
    model.thermodynamics().feature("pp2").set("LiquidPhaseModel", "UNIQUAC");
    model.thermodynamics().feature("pp2").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp2").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp2").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp2").set("GasEOSCard", "EOSModel");
    model.thermodynamics().feature("pp2").set("EOS", "IdealGas");
    model.thermodynamics().feature("pp2").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp2").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp2").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp2").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp2").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp2").set("LiqVolume", "IdealMixing");
    model.thermodynamics().feature("pp2").set("PoyntingFactor", "off");
    model.thermodynamics().feature("pp2").storePersistenceData();
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp2").set("EOSModel", "PengRobinson");
    model.thermodynamics().feature("pp2").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase1").label("\u6d3b\u5ea6\u7cfb\u6570 1");
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("funcname", "ActivityCoefficient_ethanol_water_Liquid_Liquid21");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("property", "ActivityCoefficient[ethanol]");
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("propertydescr", "\u6d3b\u5ea6\u7cfb\u6570 [ethanol]");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("unit", "1");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("derivatives", new String[]{"ActivityCoefficient_ethanol_water_Liquid_Liquid21_Dtemperature", "ActivityCoefficient_ethanol_water_Liquid_Liquid21_Dpressure", "ActivityCoefficient_ethanol_water_Liquid_Liquid21_Dmassfraction_ethanol", "ActivityCoefficient_ethanol_water_Liquid_Liquid21_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase2").label("\u6d3b\u5ea6\u7cfb\u6570 2");
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("funcname", "ActivityCoefficient_ethanol_water_Liquid_Liquid22");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("property", "ActivityCoefficient[water]");
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("propertydescr", "\u6d3b\u5ea6\u7cfb\u6570 [water]");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("unit", "1");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("derivatives", new String[]{"ActivityCoefficient_ethanol_water_Liquid_Liquid22_Dtemperature", "ActivityCoefficient_ethanol_water_Liquid_Liquid22_Dpressure", "ActivityCoefficient_ethanol_water_Liquid_Liquid22_Dmassfraction_ethanol", "ActivityCoefficient_ethanol_water_Liquid_Liquid22_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp2").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase3").label("\u5bc6\u5ea6 1");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("funcname", "Density_ethanol_Liquid23");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("property", "Density");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("compounds", new String[]{"ethanol"});
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("derivatives", new String[]{"Density_ethanol_Liquid23_Dtemperature", "Density_ethanol_Liquid23_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase4").label("\u5bc6\u5ea6 2");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("funcname", "Density_water_Liquid24");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("property", "Density");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("derivatives", new String[]{"Density_water_Liquid24_Dtemperature", "Density_water_Liquid24_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "Psat_Water");
    model.func("an1").set("expr", "exp(LnVaporPressure_water12(T))");
    model.func("an1").set("args", "T");
    model.func("an1").set("fununit", "Pa");
    model.func("an1").setIndex("argunit", "K", 0);
    model.func("an1").setIndex("plotargs", 273, 0, 1);
    model.func("an1").setIndex("plotargs", 313, 0, 2);
    model.func().duplicate("an2", "an1");
    model.func("an2").set("funcname", "Psat_Ethanol");
    model.func("an2").set("expr", "exp(LnVaporPressure_ethanol11(T))");

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1").set("wW0", "(Psat_Water(T0)/p0)*relH", "Initial water mass fraction");
    model.variable("var1").set("wEth0", "0", "Initial ethanol mass fraction");
    model.variable("var1").set("rhoEthLiq0", "Density_ethanol_Liquid23(T0,p0)", "Liquid density, ethanol");
    model.variable("var1").set("rhoWLiq0", "Density_water_Liquid24(T0,p0)", "Liquid density, water");
    model.variable("var1").set("rhoLiqFrac", "rhoEthLiq0/rhoWLiq0", "Density ratio");
    model.variable("var1").set("wEthLiq", "abv*rhoLiqFrac/(1-abv*(1-rhoLiqFrac))", "Liquid mass fraction, ethanol");
    model.variable("var1").set("wWLiq", "1-wEthLiq", "Liquid mass fraction, water");
    model.variable("var1")
         .set("gammaEth0", "ActivityCoefficient_ethanol_water_Liquid_Liquid21(T0,p0,wEthLiq,wWLiq)", "Activity coefficient, ethanol");
    model.variable("var1")
         .set("gammaW0", "ActivityCoefficient_ethanol_water_Liquid_Liquid22(T0,p0,wEthLiq,wWLiq)", "Activity coefficient, water");
    model.variable("var1").set("rhorefChem", "chempp1rho_mixture(T0,p0,wEth0,wW0,0)");

    model.component("comp1").geom("geom1")
         .insertFile("H:\\hub\\build\\patch\\daily\\test\\tapplications\\Chemical_Reaction_Engineering_Module\\Thermodynamics\\ethanol_water_evaporation_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel5");

    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("gammaEth", "ActivityCoefficient_ethanol_water_Liquid_Liquid21(T,P,wEthLiq,wWLiq)", "Activity coefficient, ethanol");
    model.component("comp1").variable("var2")
         .set("gammaW", "ActivityCoefficient_ethanol_water_Liquid_Liquid22(T,P,wEthLiq,wWLiq)", "Activity coefficient, water");
    model.component("comp1").variable("var2").set("Mn_vap", "tcs.Mn", "Mean molar mass vapor");
    model.component("comp1").variable("var2")
         .set("Mn_liq", "(wEthLiq/chem.M_C2H6O+wWLiq/chem.M_H2O)^-1", "Mean molar mass liquid");
    model.component("comp1").variable("var2")
         .set("wEth_surf", "gammaEth*wEthLiq*Psat_Ethanol(T)/P*(Mn_liq/Mn_vap)", "Vapor mass fraction, ethanol");
    model.component("comp1").variable("var2")
         .set("wW_surf", "gammaW*wWLiq*Psat_Water(T)/P*(Mn_liq/Mn_vap)", "Vapor mass fraction, water");
    model.component("comp1").variable("var2")
         .set("Qvap", "tcs.ntflux_wW*HeatOfVaporization_water14(T)+tcs.ntflux_wEth*HeatOfVaporization_ethanol13(T)", "Heat of vaporization");
    model.component("comp1").variable("var2").set("P", "max(p0,p)", "Interface Pressure");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(8);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(8);

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().set(8);
    model.component("comp1").variable("var3").set("wEth_surfAv", "aveop1(wEth_surf)");
    model.component("comp1").variable("var3").descr("wEth_surfAv", "Vapor mass fraction, ethanol");
    model.component("comp1").variable("var3").set("wW_surfAv", "aveop1(wW_surf)");
    model.component("comp1").variable("var3").descr("wW_surfAv", "Vapor mass fraction, water");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "0.05[s]");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 1);
    model.component("comp1").view("view1").hideEntities("hide1").setMeshControl(34);

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "C2H6O");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "N2");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics("chem").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "ethanol", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.33", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "water", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.33", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "nitrogen", 2);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.33", 2);
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wEth", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wW", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wN2", 2, 0);

    model.component("comp1").multiphysics("nirf1").set("Chemistry_physics", "chem");
    model.component("comp1").multiphysics("nirf1").set("Heat_physics", "ht");

    model.thermodynamics().feature("pp2").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase5").label("\u5bc6\u5ea6 3");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("funcname", "Density_ethanol_water_Liquid25");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("property", "Density");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("derivatives", new String[]{"Density_ethanol_water_Liquid25_Dtemperature", "Density_ethanol_water_Liquid25_Dpressure", "Density_ethanol_water_Liquid25_Dmassfraction_ethanol", "Density_ethanol_water_Liquid25_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase6", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase6").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp2").feature("singlephase6")
         .set("funcname", "HeatCapacityCp_ethanol_water_Liquid26");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase6")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});

    return model;
  }

  public static Model run2(Model model) {
    model.thermodynamics().feature("pp2").feature("singlephase6")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase6")
         .set("derivatives", new String[]{"HeatCapacityCp_ethanol_water_Liquid26_Dtemperature", "HeatCapacityCp_ethanol_water_Liquid26_Dpressure", "HeatCapacityCp_ethanol_water_Liquid26_Dmassfraction_ethanol", "HeatCapacityCp_ethanol_water_Liquid26_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase6").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase6").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase6").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase6").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase7", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase7").label("\u70ed\u5bb9\u6bd4 (Cp/Cv) 1");
    model.thermodynamics().feature("pp2").feature("singlephase7")
         .set("funcname", "HeatCapacityRatioCpCv_ethanol_water_Liquid27");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("unit", "1");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase7")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase7")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase7")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_ethanol_water_Liquid27_Dtemperature", "HeatCapacityRatioCpCv_ethanol_water_Liquid27_Dpressure", "HeatCapacityRatioCpCv_ethanol_water_Liquid27_Dmassfraction_ethanol", "HeatCapacityRatioCpCv_ethanol_water_Liquid27_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase7").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase7").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase7").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase7").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase8", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase8").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp2").feature("singlephase8")
         .set("funcname", "ThermalConductivity_ethanol_water_Liquid28");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("unit", "W/m/K");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase8")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase8")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase8")
         .set("derivatives", new String[]{"ThermalConductivity_ethanol_water_Liquid28_Dtemperature", "ThermalConductivity_ethanol_water_Liquid28_Dpressure", "ThermalConductivity_ethanol_water_Liquid28_Dmassfraction_ethanol", "ThermalConductivity_ethanol_water_Liquid28_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase8").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase8").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase8").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase8").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature().create("singlephase9", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase9").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp2").feature("singlephase9")
         .set("funcname", "Viscosity_ethanol_water_Liquid29");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("property", "Viscosity");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("unit", "Pa*s");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp2").feature("singlephase9")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ethanol", "1", "\u8d28\u91cf\u5206\u6570 ethanol"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp2").feature("singlephase9")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ethanol", "0.5", "0.5"}, 
         {"massfraction_water", "0.5", "0.5"}});
    model.thermodynamics().feature("pp2").feature("singlephase9")
         .set("derivatives", new String[]{"Viscosity_ethanol_water_Liquid29_Dtemperature", "Viscosity_ethanol_water_Liquid29_Dpressure", "Viscosity_ethanol_water_Liquid29_Dmassfraction_ethanol", "Viscosity_ethanol_water_Liquid29_Dmassfraction_water"});
    model.thermodynamics().feature("pp2").feature("singlephase9").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase9").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase9").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("comp_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase9").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase5").tag("mat_singlephase1");
    model.thermodynamics().feature("pp2").feature("mat_singlephase1").set("funcname", "Densitypp2");
    model.thermodynamics().feature("pp2").feature("singlephase6").tag("mat_singlephase2");
    model.thermodynamics().feature("pp2").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp2");
    model.thermodynamics().feature("pp2").feature("singlephase7").tag("mat_singlephase3");
    model.thermodynamics().feature("pp2").feature("mat_singlephase3").set("funcname", "HeatCapacityRatioCpCvpp2");
    model.thermodynamics().feature("pp2").feature("singlephase8").tag("mat_singlephase4");
    model.thermodynamics().feature("pp2").feature("mat_singlephase4").set("funcname", "ThermalConductivitypp2");
    model.thermodynamics().feature("pp2").feature("singlephase9").tag("mat_singlephase5");
    model.thermodynamics().feature("pp2").feature("mat_singlephase5").set("funcname", "Viscositypp2");
    model.thermodynamics()
         .createMaterial("comp1", "pp2", "Liquid", new String[]{"ethanol", "water"}, new String[]{"0.5", "0.5"}, new String[]{}, new String[][]{{"density", "Densitypp2"}, {"heatcapacitycp", "HeatCapacityCppp2"}, {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp2"}, {"thermalconductivity", "ThermalConductivitypp2"}, {"viscosity", "Viscositypp2"}}, "Thermodynamics", new String[][]{{"8", "273", "373", "20", "101325", "201325", "15"}, 
         {"72", "273", "373", "20", "101325", "201325", "15"}, 
         {"80", "273", "373", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"64", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mass"});

    model.component("comp1").material("pp2mat1").selection().named("geom1_sel1");
    model.component("comp1").material("pp2mat1").propertyGroup("def").set("xw1", new String[]{"wEthLiq"});
    model.component("comp1").material("pp2mat1").propertyGroup("def").set("xw2", new String[]{"wWLiq"});
    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silica glass");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customambient", new int[]{1, 1, 1});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("anisotropy", 0);
    model.component("comp1").material("mat1").set("flipanisotropy", false);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("customambient", new double[]{1, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("customdiffuse", new double[]{1, 1, 1});
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{1, 1, 1});
    model.component("comp1").material("mat1").set("noisecolor", "custom");
    model.component("comp1").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat1").set("noisescale", 0);
    model.component("comp1").material("mat1").set("noise", "off");
    model.component("comp1").material("mat1").set("noisefreq", 1);
    model.component("comp1").material("mat1").set("normalnoisebrush", "0");
    model.component("comp1").material("mat1").set("normalnoisetype", "0");
    model.component("comp1").material("mat1").set("alpha", 1);
    model.component("comp1").material("mat1").set("anisotropyaxis", new double[]{0, 0, 1});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").label("Wood (pine)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("wc");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "wc");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "0"}, {"0.55", "45"}, {"0.75", "80"}, {"0.97", "185"}, {"1", "870"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Dw");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "Dw");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "1.32e-13"}, {"0.65", "1.32e-13"}, {"1", "8.03e-11"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("delta_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "delta_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "max(7e-13*exp(2.84*phi),0)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"phi"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"phi", "0", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").label("k");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("funcname", "k");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "0.1"}, {"0.97", "0.15"}, {"1", "0.6"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
         .set("fununit", new String[]{"W/(m*K)"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("mu_vrf");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "mu_vrf");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "max(2.01E-7*T^0.81/p/delta_p(phi),0)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("args", new String[]{"T", "p", "phi"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("argunit", new String[]{"K", "Pa", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "293.15", "293.15"}, {"p", "1e5", "1e5"}, {"phi", "0", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "532");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "2700");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(phi)", "0", "0", "0", "k(phi)", "0", "0", "0", "k(phi)"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"Dw(phi)", "0", "0", "0", "Dw(phi)", "0", "0", "0", "Dw(phi)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("watercontent", "wc(phi)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("vaporpermeability", new String[]{"delta_p(phi)", "0", "0", "0", "delta_p(phi)", "0", "0", "0", "delta_p(phi)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("vaporresistancefactor", "mu_vrf(T,pA,phi)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").set("family", "plastic");
    model.component("comp1").material("mat1").selection().named("geom1_sel2");
    model.component("comp1").material("mat2").selection().named("geom1_sel4");

    model.component("comp1").physics("spf").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p0");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(8);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "NavierSlip");
    model.component("comp1").physics("tcs").selection().named("geom1_sel3");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 3);
    model.component("comp1").physics("tcs").feature("cdm1").set("rho_src", "root.comp1.chem.rho_chem");
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C2H6O_H2O", 0, 0);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C2H6O_N2", 1, 0);
    model.component("comp1").physics("tcs").feature("cdm1").setIndex("DiffusivityFrom", "comp1.chem.D_H2O_N2", 2, 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "wEth0", 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "wW0", 1);
    model.component("comp1").physics("tcs").create("mf1", "MassFraction", 1);
    model.component("comp1").physics("tcs").feature("mf1").selection().named("geom1_sel5");
    model.component("comp1").physics("tcs").feature("mf1").setIndex("species", true, 0);
    model.component("comp1").physics("tcs").feature("mf1")
         .setIndex("wbnd", "wEth_surf*step1(t)+wEth0*(1-step1(t))", 0);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("species", true, 1);
    model.component("comp1").physics("tcs").feature("mf1").setIndex("wbnd", "wW_surf*step1(t)+wW0*(1-step1(t))", 1);
    model.component("comp1").physics("tcs").feature("mf1").set("uStefanActive", true);
    model.component("comp1").physics("tcs").feature("mf1").set("weakConstraints", true);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").selection().set(1, 2, 3);
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").selection().named("geom1_sel5");
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input", "Qvap");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size3", 3);
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(32);
    model.component("comp1").mesh("mesh1").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("size4").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size4").selection().set(35, 36);
    model.component("comp1").mesh("mesh1").feature("size4").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size4").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size4").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmax", "0.75[cm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.15);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection()
         .set(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 2);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection()
         .set(7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 28, 31, 32, 34);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size5", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size5", 4);
    model.component("comp1").mesh("mesh1").feature("size5").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size5").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("size5").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size5").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature().move("ftri2", 1);
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", 0.0015);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size2").set("hmax", "3e-4");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size3").selection().set(25, 26);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size3").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size3").set("hcurve", 0.1);
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "abv", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "abv", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.01 0.15 0.4 0.99", 0);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(4);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(4);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,1)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.005);
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.05);

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1")
         .set("atolmethod", new String[]{"comp1_p", "scaled", "comp1_T", "global", "comp1_u", "global", "comp1_wEth", "global", "comp1_wEth_lm", "global", 
         "comp1_wW", "global", "comp1_wW_lm", "global"});
    model.sol("sol1").feature("t1")
         .set("atol", new String[]{"comp1_p", "1e-3", "comp1_T", "1e-3", "comp1_u", "1e-3", "comp1_wEth", "1e-3", "comp1_wEth_lm", "1e-3", 
         "comp1_wW", "1e-3", "comp1_wW_lm", "1e-3"});
    model.sol("sol1").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_p", "factor", "comp1_T", "factor", "comp1_u", "factor", "comp1_wEth", "factor", "comp1_wEth_lm", "factor", 
         "comp1_wW", "factor", "comp1_wW_lm", "factor"});
    model.sol("sol1").feature("t1")
         .set("atolfactor", new String[]{"comp1_p", "1", "comp1_T", "0.1", "comp1_u", "0.1", "comp1_wEth", "0.1", "comp1_wEth_lm", "0.1", 
         "comp1_wW", "0.1", "comp1_wW_lm", "0.1"});
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").set("rhoinf", 0.5);
    model.sol("sol1").feature("t1").set("predictor", "constant");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("stabcntrl", true);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("seDef", "Segregated");
    model.sol("sol1").feature("t1").create("se1", "Segregated");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_u", "comp1_p", "comp1_T", "comp1_wEth", "comp1_wW", "comp1_wEth_lm", "comp1_wW_lm"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subdamp", 0.5);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1").label("\u76f4\u63a5\uff0creacting flow");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "d1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").label("\u53cd\u5e94\u6d41");
    model.sol("sol1").feature("t1").feature("se1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdim", 5);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("se1").set("segaaccdelay", 1);
    model.sol("sol1").feature("t1").feature("se1").set("maxsegiter", 10);
    model.sol("sol1").feature("t1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("t1").feature("se1").feature("ll1").set("lowerlimit", "comp1.T 0 ");
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i1").label("AMG\uff0creacting flow");
    model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_wEth_lm", "comp1_wW_lm"});
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_wEth_lm", "comp1_wW_lm"});
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").feature().remove("seDef");
    model.sol("sol1").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"abv"});
    model.batch("p1").set("plistarr", new String[]{"0.01 0.15 0.4 0.99"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");

    model.study("std1").feature("time")
         .set("tlist", "range(0,0.1,1) range(1.25,0.25,4) range(5,1,20) range(25,5,120) range(130,10,240) range(270,30,900)");

    model.sol("sol1").feature("v1").feature("comp1_T").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_T").set("scaleval", "T0");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", 0.01);
    model.sol("sol1").feature("v1").feature("comp1_wEth").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_wW").set("scalemethod", "manual");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg2");
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
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pcond1/pg1");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u6d53\u5ea6, Eth (tcs)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 Eth:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"tcs.c_wEth"});
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tcs.tflux_wEthr", "tcs.tflux_wEthz"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").label("\u6d53\u5ea6, Eth, 3D (tcs)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"tcs.c_wEth"});
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 Eth:");
    model.result("pg5").set("expressionintitle", false);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u6d53\u5ea6, W (tcs)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 W:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"tcs.c_wW"});
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"tcs.tflux_wWr", "tcs.tflux_wWz"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "rev1");
    model.result("pg7").label("\u6d53\u5ea6, W, 3D (tcs)");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"tcs.c_wW"});
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 W:");
    model.result("pg7").set("expressionintitle", false);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u6d53\u5ea6, N2 (tcs)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"tcs.c_wN2"});
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcs.tflux_wN2r", "tcs.tflux_wN2z"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev1");
    model.result("pg9").label("\u6d53\u5ea6, N2, 3D (tcs)");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"tcs.c_wN2"});
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg9").set("expressionintitle", false);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u4e09\u7ef4\u6e29\u5ea6 (ht)");
    model.result("pg10").set("defaultPlotID", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond2/pcond2/pg1");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u8868\u9762");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("defaultPlotID", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pg1");
    model.result("pg11").feature().create("con1", "Contour");
    model.result("pg11").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg11").feature("con1").set("expr", "T");
    model.result("pg11").feature("con1").set("levelrounding", false);
    model.result("pg11").feature("con1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("con1").set("smooth", "internal");
    model.result("pg11").feature("con1").set("data", "parent");

    model.batch("p1").getInitialValue();

    model.result("pg1").set("data", "dset2");
    model.result("pg2").set("data", "dset2");
    model.result().dataset("rev1").set("data", "dset2");
    model.result("pg11").set("data", "dset2");
    model.result("pg1").run();
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("removesymelem", true);
    model.result("pg12").run();
    model.result("pg12").set("data", "mir1");
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("paramindicator", "eval(abv*100)% Ethanol, Time =eval(t) s");
    model.result("pg12").set("showlegendsmaxmin", true);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").set("legendactive", true);
    model.result("pg12").set("legendnotation", "scientific");
    model.result("pg12").set("plotarrayenable", true);
    model.result("pg12").set("arrayshape", "square");
    model.result("pg12").set("order", "columnmajor");
    model.result("pg12").set("relrowpadding", 0.1);
    model.result("pg12").set("relcolumnpadding", 0.1);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").create("surf2", "Surface");
    model.result("pg12").feature("surf2").set("arraydim", "2");
    model.result("pg12").feature("surf2").set("expr", "wW");
    model.result("pg12").feature("surf2").set("colortable", "Disco");
    model.result("pg12").feature("surf2").set("manualindexing", true);
    model.result("pg12").feature("surf2").set("rowindex", -1);
    model.result("pg12").run();
    model.result("pg12").create("surf3", "Surface");
    model.result("pg12").feature("surf3").set("arraydim", "2");
    model.result("pg12").feature("surf3").set("expr", "T");
    model.result("pg12").feature("surf3").set("unit", "degC");
    model.result("pg12").feature("surf3").set("colortable", "HeatCamera");
    model.result("pg12").feature("surf3").set("manualindexing", true);
    model.result("pg12").feature("surf3").set("colindex", 1);
    model.result("pg12").feature("surf2").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("surf4", "surf2");
    model.result("pg12").feature("surf4").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").feature("surf4").set("expr", "wEth");
    model.result("pg12").feature("surf4").set("colindex", 1);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").set("legendpos", "rightdouble");
    model.result("pg12").create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").set("arraydim", "2");
    model.result("pg12").feature("arws1").set("xnumber", 12);
    model.result("pg12").feature("arws1").set("ynumber", 12);
    model.result("pg12").feature("arws1").set("arrowtype", "cone");
    model.result("pg12").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("arws1").set("logrange", 20);
    model.result("pg12").feature("arws1").set("color", "white");
    model.result("pg12").feature("arws1").set("manualindexing", true);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("ann1", "Annotation");
    model.result("pg12").feature("ann1").set("arraydim", "2");
    model.result("pg12").feature("ann1").set("posxexpr", -0.5);
    model.result("pg12").feature("ann1").set("posyexpr", 0.5);
    model.result("pg12").feature("ann1").set("showpoint", false);
    model.result("pg12").feature("ann1").set("belongstoplotarray", false);
    model.result("pg12").feature("ann1").set("anchorpoint", "lowerleft");
    model.result("pg12").feature().duplicate("ann2", "ann1");
    model.result("pg12").feature("ann2").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").feature("ann2").set("posxexpr", 0.6);
    model.result("pg12").feature().duplicate("ann3", "ann2");
    model.result("pg12").feature("ann3").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").feature("ann3").set("posxexpr", -0.5);
    model.result("pg12").feature("ann3").set("posyexpr", -0.7);
    model.result("pg12").feature().duplicate("ann4", "ann3");
    model.result("pg12").feature("ann4").set("arraydim", "2");
    model.result("pg12").run();
    model.result("pg12").feature("ann4").set("posxexpr", 0.6);

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg12");
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").feature("param").set("plot", true);
    model.study("std1").feature("param").set("plotgroup", "pg12");

    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result().dataset("mir1").set("data", "dset2");
    model.result("pg12").run();
    model.result("pg12").set("showlegends", false);
    model.result("pg12").setIndex("looplevel", 1, 1);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", 2, 1);
    model.result("pg12").run();
    model.result("pg12").set("showlegends", true);
    model.result("pg12").setIndex("looplevel", 1, 1);
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").run();
    model.result("pg13").set("data", "mir1");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("paramindicator", "Time =eval(t) s");
    model.result("pg13").set("plotarrayenable", true);
    model.result("pg13").set("arrayshape", "square");
    model.result("pg13").set("relrowpadding", 0.1);
    model.result("pg13").set("relcolumnpadding", 0.1);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("arraydim", "2");
    model.result("pg13").feature("surf1").set("expr", "withsol('sol2',spf.U,setval(abv,0.01,t,t))");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("surf2", "surf1");
    model.result("pg13").feature("surf2").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("surf2").set("expr", "withsol('sol2',spf.U,setval(abv,0.15,t,t))");
    model.result("pg13").feature("surf2").set("manualindexing", true);
    model.result("pg13").feature("surf2").set("rowindex", -1);
    model.result("pg13").feature("surf2").set("inheritplot", "surf1");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("surf3", "surf2");
    model.result("pg13").feature("surf3").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("surf3").set("expr", "withsol('sol2',spf.U,setval(abv,0.4,t,t))");
    model.result("pg13").feature("surf3").set("rowindex", 0);
    model.result("pg13").feature("surf3").set("colindex", 1);
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("surf4", "surf3");
    model.result("pg13").feature("surf4").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("surf4").set("expr", "withsol('sol2',spf.U,setval(abv,0.99,t,t))");
    model.result("pg13").feature("surf4").set("rowindex", -1);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").create("arws1", "ArrowSurface");
    model.result("pg13").feature("arws1").set("arraydim", "2");
    model.result("pg13").feature("arws1").set("expr", new String[]{"withsol('sol2',u,setval(abv,0.01,t,t))", "w"});
    model.result("pg13").feature("arws1").setIndex("expr", "withsol('sol2',w,setval(abv,0.01,t,t))", 1);
    model.result("pg13").feature("arws1").set("xnumber", 12);
    model.result("pg13").feature("arws1").set("ynumber", 12);
    model.result("pg13").feature("arws1").set("arrowtype", "cone");
    model.result("pg13").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg13").feature("arws1").set("logrange", 20);
    model.result("pg13").feature("arws1").set("color", "white");
    model.result("pg13").feature("arws1").set("manualindexing", true);
    model.result("pg13").feature().duplicate("arws2", "arws1");
    model.result("pg13").feature("arws2").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("arws2").setIndex("expr", "withsol('sol2',u,setval(abv,0.15,t,t))", 0);
    model.result("pg13").feature("arws2").setIndex("expr", "withsol('sol2',w,setval(abv,0.15,t,t))", 1);
    model.result("pg13").feature("arws2").set("rowindex", -1);
    model.result("pg13").feature().duplicate("arws3", "arws2");
    model.result("pg13").feature("arws3").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("arws3").setIndex("expr", "withsol('sol2',u,setval(abv,0.4,t,t))", 0);
    model.result("pg13").feature("arws3").setIndex("expr", "withsol('sol2',w,setval(abv,0.4,t,t))", 1);
    model.result("pg13").feature("arws3").set("rowindex", 0);
    model.result("pg13").feature("arws3").set("colindex", 1);
    model.result("pg13").feature().duplicate("arws4", "arws3");
    model.result("pg13").feature("arws4").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("arws4").setIndex("expr", "withsol('sol2',u,setval(abv,0.99,t,t))", 0);
    model.result("pg13").feature("arws4").setIndex("expr", "withsol('sol2',w,setval(abv,0.99,t,t))", 1);
    model.result("pg13").feature("arws4").set("rowindex", -1);
    model.result("pg13").run();
    model.result("pg13").feature("surf2").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").create("ann1", "Annotation");
    model.result("pg13").feature("ann1").set("arraydim", "2");
    model.result("pg13").feature("ann1").set("posxexpr", -0.5);
    model.result("pg13").feature("ann1").set("posyexpr", 0.5);
    model.result("pg13").feature("ann1").set("showpoint", false);
    model.result("pg13").feature("ann1").set("anchorpoint", "lowerleft");
    model.result("pg13").feature("ann1").set("belongstoplotarray", false);
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("ann2", "ann1");
    model.result("pg13").feature("ann2").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("ann2").set("posxexpr", 0.6);
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("ann3", "ann2");
    model.result("pg13").feature("ann3").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("ann3").set("posxexpr", -0.5);
    model.result("pg13").feature("ann3").set("posyexpr", -0.7);
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("ann4", "ann3");
    model.result("pg13").feature("ann4").set("arraydim", "2");
    model.result("pg13").run();
    model.result("pg13").feature("ann4").set("posxexpr", 0.6);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").set("showlegends", true);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").create("int1", "IntLine");
    model.result().evaluationGroup("eg1").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int1").set("tablecols", "level2");
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_sel5");
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "-tcs.ntflux_wW", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("unit", "g/h", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").set("dataseries", "integral");
    model.result().evaluationGroup("eg1").feature("int2").set("dataseriescumulative", true);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").create("tblp1", "Table");
    model.result("pg14").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg14").feature("tblp1").set("linewidth", "preference");
    model.result("pg14").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg14").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg14").feature("tblp1").set("plotcolumns", new int[]{2, 3, 4, 5});
    model.result("pg14").feature("tblp1").set("legend", true);
    model.result("pg14").feature("tblp1").set("legendmethod", "manual");

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg14").feature("tblp1").setIndex("legends", "abv=0.01", 0);
    model.result("pg14").feature("tblp1").setIndex("legends", "abv=0.15", 1);
    model.result("pg14").feature("tblp1").setIndex("legends", "abv=0.4", 2);
    model.result("pg14").feature("tblp1").setIndex("legends", "abv=0.99", 3);
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("tblp2", "tblp1");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").set("data", "none");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("twoyaxes", true);
    model.result("pg14").set("yseclabelactive", true);
    model.result("pg14").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg14").set("legendlayout", "outside");
    model.result("pg14").set("legendposoutside", "top");
    model.result("pg14").run();
    model.result("pg14").feature("tblp2").set("plotcolumns", new int[]{6, 7, 8, 9});
    model.result("pg14").feature("tblp2").set("linestyle", "dashed");
    model.result("pg14").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg14").feature("tblp2").setIndex("legends", "abv=0.01, Cumulative", 0);
    model.result("pg14").feature("tblp2").setIndex("legends", "abv=0.15, Cumulative", 1);
    model.result("pg14").feature("tblp2").setIndex("legends", "abv=0.4, Cumulative", 2);
    model.result("pg14").feature("tblp2").setIndex("legends", "abv=0.99, Cumulative", 3);
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").set("axislimits", true);
    model.result("pg14").set("ymin", -0.1);
    model.result("pg14").set("ymax", 0.2);
    model.result("pg14").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").feature("int1").set("expr", new String[]{"tcs.ntflux_wEth"});
    model.result().evaluationGroup("eg2").feature("int1")
         .set("descr", new String[]{"\u6cd5\u5411\u603b\u901a\u91cf"});
    model.result().evaluationGroup("eg2").feature("int1").set("unit", new String[]{"g/h"});
    model.result().evaluationGroup("eg2").feature("int1").setIndex("expr", "-tcs.ntflux_wEth", 0);
    model.result().evaluationGroup("eg2").feature("int2").set("expr", new String[]{"tcs.ntflux_wEth"});
    model.result().evaluationGroup("eg2").feature("int2")
         .set("descr", new String[]{"\u6cd5\u5411\u603b\u901a\u91cf"});
    model.result().evaluationGroup("eg2").feature("int2").set("unit", new String[]{"g/h"});
    model.result().evaluationGroup("eg2").feature("int2").setIndex("expr", "-tcs.ntflux_wEth", 0);
    model.result().evaluationGroup("eg2").run();
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg15").run();
    model.result("pg15").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").set("ymin", 0);
    model.result("pg15").set("ymax", 1);
    model.result("pg15").set("yminsec", 0);
    model.result("pg15").set("ymaxsec", 0.085);
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset2");
    model.result().evaluationGroup("eg3").create("int1", "IntSurface");
    model.result().evaluationGroup("eg3").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg3").feature("int1").set("tablecols", "level2");
    model.result().evaluationGroup("eg3").feature("int1").selection().named("geom1_sel3");
    model.result().evaluationGroup("eg3").feature("int1").setIndex("expr", "spf.rho*spf.U", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").set("data", "none");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("legendlayout", "outside");
    model.result("pg16").create("tblp1", "Table");
    model.result("pg16").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg16").feature("tblp1").set("linewidth", "preference");
    model.result("pg16").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg16").feature("tblp1").set("evaluationgroup", "eg3");
    model.result("pg16").feature("tblp1").set("legend", true);
    model.result("pg16").run();
    model.result("pg16").feature("tblp1").set("legendmethod", "manual");
    model.result("pg16").feature("tblp1").setIndex("legends", "abv=0.01", 0);
    model.result("pg16").feature("tblp1").setIndex("legends", "abv=0.15", 1);
    model.result("pg16").feature("tblp1").setIndex("legends", "abv=0.4", 2);
    model.result("pg16").feature("tblp1").setIndex("legends", "abv=0.99", 3);
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup1D");
    model.result("pg17").run();
    model.result("pg17").set("data", "dset2");
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("legendlayout", "outside");
    model.result("pg17").create("ptgr1", "PointGraph");
    model.result("pg17").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg17").feature("ptgr1").set("linewidth", "preference");
    model.result("pg17").feature("ptgr1").selection().set(4);
    model.result("pg17").feature("ptgr1").set("expr", "T");
    model.result("pg17").feature("ptgr1").set("unit", "degC");
    model.result("pg17").feature("ptgr1").set("legend", true);
    model.result("pg17").feature("ptgr1").set("autopoint", false);
    model.result("pg17").run();
    model.result("pg17").run();
    model.result().duplicate("pg18", "pg17");
    model.result("pg18").run();
    model.result("pg18").run();
    model.result("pg18").feature("ptgr1").set("expr", "tcs.c_wEth");
    model.result("pg18").feature("ptgr1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg18").run();
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol2");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("geom1_sel2");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().create("pg19", "PlotGroup3D");
    model.result("pg19").run();
    model.result("pg19").set("data", "rev2");
    model.result("pg19").setIndex("looplevel", 2, 1);
    model.result("pg19").setIndex("looplevel", 55, 0);
    model.result("pg19").set("edges", false);
    model.result("pg19").create("vol1", "Volume");
    model.result("pg19").feature("vol1").set("data", "rev2");
    model.result("pg19").feature("vol1").set("titletype", "none");
    model.result("pg19").feature("vol1").set("expr", "1");
    model.result("pg19").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg19").run();
    model.result("pg19").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg19").feature("vol1").feature("mtrl1").set("family", "custom");
    model.result("pg19").feature("vol1").feature("mtrl1")
         .set("customspecular", new double[]{0.9372549057006836, 0.9372549057006836, 0.9215686321258545});
    model.result("pg19").feature("vol1").feature("mtrl1")
         .set("customdiffuse", new double[]{0.9019607901573181, 0.9019607901573181, 1});
    model.result("pg19").feature("vol1").feature("mtrl1")
         .set("customambient", new double[]{0.9019607901573181, 0.9019607901573181, 1});
    model.result("pg19").feature("vol1").feature("mtrl1").set("noise", true);
    model.result("pg19").feature("vol1").feature("mtrl1").set("noisescale", 0.1);
    model.result("pg19").feature("vol1").feature("mtrl1").set("noisefreq", 30);
    model.result("pg19").feature("vol1").feature("mtrl1").set("colornoise", true);
    model.result("pg19").feature("vol1").feature("mtrl1").set("colornoisescale", 1);
    model.result("pg19").feature("vol1").feature("mtrl1").set("colornoisefrequency", 10);
    model.result("pg19").feature("vol1").feature("mtrl1").set("noisecolorblend", 0.55);
    model.result("pg19").feature("vol1").feature("mtrl1")
         .set("customnoisecolor", new double[]{0.062745101749897, 0.12156862765550613, 0.33725491166114807});
    model.result("pg19").feature("vol1").feature("mtrl1").set("alpha", 0.5);
    model.result("pg19").feature("vol1").feature("mtrl1").set("fresnel", 0.9);
    model.result("pg19").feature("vol1").feature("mtrl1").set("roughness", 0.25);
    model.result("pg19").feature("vol1").feature("mtrl1").set("metallic", 0.85);
    model.result("pg19").feature("vol1").feature("mtrl1").set("pearl", 0.05);
    model.result("pg19").feature("vol1").feature("mtrl1").set("diffusewrap", 0.45);
    model.result("pg19").feature("vol1").feature("mtrl1").set("clearcoat", 0.3);
    model.result("pg19").feature("vol1").feature("mtrl1").set("reflectance", 0.75);
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(1);
    model.result().dataset().duplicate("rev3", "rev2");
    model.result().dataset("rev3").set("data", "dset4");
    model.result("pg19").run();
    model.result("pg19").create("vol2", "Volume");
    model.result("pg19").feature("vol2").set("data", "rev3");
    model.result("pg19").feature("vol2").set("expr", "1");
    model.result("pg19").feature("vol2").set("titletype", "none");
    model.result("pg19").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg19").run();
    model.result("pg19").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg19").feature("vol2").feature("mtrl1").set("family", "wood");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result("pg19").run();
    model.result("pg19").create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("data", "cpl1");
    model.result("pg19").feature("surf1").set("solutionparams", "parent");
    model.result("pg19").feature("surf1").create("tran1", "Transparency");
    model.result("pg19").run();
    model.result("pg19").feature("surf1").feature("tran1").set("transparency", 0.4);
    model.result("pg19").run();
    model.result("pg19").run();
    model.result("pg19").create("arws1", "ArrowSurface");
    model.result("pg19").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg19").feature("arws1").set("data", "cpl1");
    model.result("pg19").feature("arws1").set("solutionparams", "parent");
    model.result("pg19").feature("arws1").set("arrowcount", 300);
    model.result("pg19").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg19").feature("arws1").set("color", "black");
    model.result("pg19").run();
    model.result("pg19").run();

    model.view("view4").camera().set("zoomanglefull", 7.55587244033813);
    model.view("view4").camera().setIndex("position", "6.0715093612670", 0);
    model.view("view4").camera().setIndex("position", -3.836441040039062, 1);
    model.view("view4").camera().setIndex("position", "3.41434192657470", 2);
    model.view("view4").camera().setIndex("target", -0.0537457466125488, 0);
    model.view("view4").camera().setIndex("target", -0.00538253784179687, 1);
    model.view("view4").camera().setIndex("target", 0.2091314792633056, 2);
    model.view("view4").camera().setIndex("up", -0.345619320869445, 0);
    model.view("view4").camera().setIndex("up", 0.1804702281951904, 1);
    model.view("view4").camera().setIndex("up", 0.806934475898742, 2);
    model.view("view4").camera().setIndex("rotationpoint", 0.0400786399841308, 0);
    model.view("view4").camera().setIndex("rotationpoint", 0.0116240978240966, 1);
    model.view("view4").camera().setIndex("rotationpoint", 0.1726796627044677, 2);
    model.view("view4").camera().setIndex("viewoffset", -0.0640072077512741, 0);
    model.view("view4").camera().setIndex("viewoffset", 0.0995445698499679, 1);

    model.thermodynamics().feature("pp1").feature("tdep3").createPlot("pg20");

    model.result("pg20").run();

    model.thermodynamics().feature("pp1").feature("tdep4").createPlot("pg21");

    model.result("pg21").run();
    model.result("pg21").run();
    model.result("pg20").feature().copy("plot2", "pg21/plot1");
    model.result("pg21").feature().remove("plot1");
    model.result("pg20").feature("plot2").set("legend", true);
    model.result("pg20").feature("plot2").set("legendmethod", "manual");
    model.result("pg20").feature("plot2").setIndex("legends", "Water", 0);
    model.result("pg20").run();
    model.result("pg20").feature("plot1").set("legend", true);
    model.result("pg20").feature("plot1").set("legendmethod", "manual");
    model.result("pg20").feature("plot1").setIndex("legends", "Ethanol", 0);
    model.result("pg20").run();
    model.result("pg20").set("titletype", "none");
    model.result("pg20").set("ylabelactive", true);
    model.result("pg20").set("legendpos", "middleright");
    model.result("pg20").set("axislimits", true);
    model.result("pg20").set("ymin", "-1e5");
    model.result("pg20").set("ymax", "3e6");
    model.result("pg20").run();
    model.result("pg21").run();
    model.result().remove("pg21");
    model.result("pg12").run();

    model.title("\u9152\u676f\u4e2d\u4e59\u9187\u548c\u6c34\u7684\u84b8\u53d1");

    model
         .description("\u5728\u4e13\u4e1a\u54c1\u9152\u8fc7\u7a0b\u4e2d\uff0c\u901a\u5e38\u4f1a\u4e00\u6b21\u54c1\u9274\u591a\u79cd\u9152\u6837\uff0c\u56e0\u6b64\u5fc5\u987b\u4e3a\u6bcf\u4e2a\u6837\u54c1\u76d6\u4e0a\u76d6\u5b50\uff0c\u56e0\u4e3a\u4e59\u9187\u548c\u6c34\u7684\u84b8\u53d1\u4f1a\u6539\u53d8\u9152\u7684\u53e3\u611f\u3002\u672c\u6a21\u578b\u6a21\u62df\u4e59\u9187\u548c\u6c34\u4ece\u9152\u676f\u4e2d\u7684\u84b8\u53d1\u8fc7\u7a0b\uff0c\u5229\u7528\u6269\u5c55\u7684\u62c9\u4e4c\u5c14\u5b9a\u5f8b\u6a21\u62df\u591a\u79cd\u7269\u8d28\u4ece\u975e\u7406\u60f3\u6db2\u4f53\u6df7\u5408\u7269\u4e2d\u7684\u84b8\u53d1\u3002\u7531\u4e8e\u84b8\u53d1\u5f15\u8d77\u7684\u6210\u5206\u53d8\u5316\u548c\u6c7d\u5316\u70ed\uff0c\u4f1a\u5728\u5468\u56f4\u6c14\u76f8\u4e2d\u4ea7\u751f\u81ea\u7136\u5bf9\u6d41\u3002\u672c\u4f8b\u7ed3\u5408\u4f7f\u7528\u201c\u5c42\u6d41\u201d\u3001\u201c\u6d53\u7269\u8d28\u4f20\u9012\u201d\u548c\u201c\u6d41\u4f53\u4f20\u70ed\u201d\u63a5\u53e3\u5efa\u7acb\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff0c\u5e76\u501f\u52a9\u201c\u70ed\u529b\u5b66\u201d\u529f\u80fd\u63d0\u4f9b\u7cbe\u786e\u7684\u70ed\u529b\u5b66\u6570\u636e\u3002\u6db2\u4f53\u7cfb\u7edf\u91c7\u7528\u4e86 UNIQUAC \u6a21\u578b\uff0c\u5e76\u7ed3\u5408 Peng-Robinson \u4e09\u6b21\u72b6\u6001\u65b9\u7a0b\uff0c\u800c\u6c7d-\u6db2\u7cfb\u7edf\u5219\u91c7\u7528\u4e86 Soave-Redlich-Kwong \u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("ethanol_water_evaporation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
