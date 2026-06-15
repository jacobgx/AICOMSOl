/*
 * pressure_reciprocity_calibration_coupler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class pressure_reciprocity_calibration_coupler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);

    model.param().label("\u53c2\u6570 - \u7269\u7406\u573a");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p0", "1[atm]", "\u73af\u5883\u538b\u529b");
    model.param().set("T0", "25[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("relH", "0.5", "\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("f0", "10[kHz]", "\u6700\u5927\u9891\u7387");
    model.param().set("dvisc0", "0.22[mm]*sqrt(100[Hz]/100[Hz])", "100 Hz \u7684\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("dvisc", "0.22[mm]*sqrt(100[Hz]/f0)", "f0 \u7684\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("dn", "1[um]", "\u6d3b\u585e\u6700\u5927\u4f4d\u79fb");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u51e0\u4f55");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("a", "4.5[mm]", "\u8026\u5408\u5668\u534a\u5f84");
    model.param("par2").set("L", "5[mm]", "\u8026\u5408\u5668\u957f\u5ea6");
    model.param("par2").set("S", "a^2*pi", "\u6a2a\u622a\u9762\u79ef");
    model.param("par2").set("A", "2*S+L*2*pi*a", "\u5185\u90e8\u8026\u5408\u5668\u8868\u9762\u79ef");
    model.param("par2").set("V", "S*L", "\u8026\u5408\u5668\u4f53\u79ef");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"a", "L"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14\uff08\u5e72\u71e5\uff09");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"nitrogen", "7727-37-9", "N2", "COMSOL"}, 
         {"oxygen", "7782-44-7", "O2", "COMSOL"}, 
         {"water", "7732-18-5", "H2O", "COMSOL"}, 
         {"argon", "7440-37-1", "Ar", "COMSOL"}, 
         {"carbon dioxide", "124-38-9", "CO2", "COMSOL"}, 
         {"neon", "7440-01-9", "Ne", "COMSOL"}, 
         {"helium", "7440-59-7", "He", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").set("predefinedSystem", "Moistair");
    model.thermodynamics().feature("pp1").label("\u6e7f\u7a7a\u6c14 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "Predefined_systemMoistair");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("tdep1", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp1").feature("tdep1").set("funcname", "LnVaporPressure_water11");
    model.thermodynamics().feature("pp1").feature("tdep1").set("property", "LnVaporPressure");
    model.thermodynamics().feature("pp1").feature("tdep1").set("propertydescr", "Ln \u84b8\u6c7d\u538b");
    model.thermodynamics().feature("pp1").feature("tdep1").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("tdep1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("tdep1").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("tdep1").comments("Refitted to IAPWS");
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[273.15 ,647.10]"}});
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp1").feature("tdep1")
         .set("derivatives", new String[]{"LnVaporPressure_water11_Dtemperature"});
    model.thermodynamics().feature("pp1").feature("tdep1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("compounds", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_nitrogen", "1", "\u6469\u5c14\u5206\u6570 nitrogen"}, 
         {"molefraction_oxygen", "1", "\u6469\u5c14\u5206\u6570 oxygen"}, 
         {"molefraction_water", "1", "\u6469\u5c14\u5206\u6570 water"}, 
         {"molefraction_argon", "1", "\u6469\u5c14\u5206\u6570 argon"}, 
         {"molefraction_carbon_dioxide", "1", "\u6469\u5c14\u5206\u6570 carbon dioxide"}, 
         {"molefraction_neon", "1", "\u6469\u5c14\u5206\u6570 neon"}, 
         {"molefraction_helium", "1", "\u6469\u5c14\u5206\u6570 helium"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_nitrogen", "0.14", "0.14"}, 
         {"molefraction_oxygen", "0.14", "0.14"}, 
         {"molefraction_water", "0.14", "0.14"}, 
         {"molefraction_argon", "0.14", "0.14"}, 
         {"molefraction_carbon_dioxide", "0.14", "0.14"}, 
         {"molefraction_neon", "0.14", "0.14"}, 
         {"molefraction_helium", "0.14", "0.14"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dtemperature", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dpressure", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_nitrogen", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_oxygen", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_water", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_argon", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_carbon_dioxide", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_neon", "Density_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas11_Dmolefraction_helium"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("funcname", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("compounds", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_nitrogen", "1", "\u6469\u5c14\u5206\u6570 nitrogen"}, 
         {"molefraction_oxygen", "1", "\u6469\u5c14\u5206\u6570 oxygen"}, 
         {"molefraction_water", "1", "\u6469\u5c14\u5206\u6570 water"}, 
         {"molefraction_argon", "1", "\u6469\u5c14\u5206\u6570 argon"}, 
         {"molefraction_carbon_dioxide", "1", "\u6469\u5c14\u5206\u6570 carbon dioxide"}, 
         {"molefraction_neon", "1", "\u6469\u5c14\u5206\u6570 neon"}, 
         {"molefraction_helium", "1", "\u6469\u5c14\u5206\u6570 helium"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_nitrogen", "0.14", "0.14"}, 
         {"molefraction_oxygen", "0.14", "0.14"}, 
         {"molefraction_water", "0.14", "0.14"}, 
         {"molefraction_argon", "0.14", "0.14"}, 
         {"molefraction_carbon_dioxide", "0.14", "0.14"}, 
         {"molefraction_neon", "0.14", "0.14"}, 
         {"molefraction_helium", "0.14", "0.14"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dtemperature", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dpressure", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_nitrogen", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_oxygen", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_water", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_argon", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_carbon_dioxide", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_neon", "HeatCapacityCp_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas12_Dmolefraction_helium"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("compounds", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_nitrogen", "1", "\u6469\u5c14\u5206\u6570 nitrogen"}, 
         {"molefraction_oxygen", "1", "\u6469\u5c14\u5206\u6570 oxygen"}, 
         {"molefraction_water", "1", "\u6469\u5c14\u5206\u6570 water"}, 
         {"molefraction_argon", "1", "\u6469\u5c14\u5206\u6570 argon"}, 
         {"molefraction_carbon_dioxide", "1", "\u6469\u5c14\u5206\u6570 carbon dioxide"}, 
         {"molefraction_neon", "1", "\u6469\u5c14\u5206\u6570 neon"}, 
         {"molefraction_helium", "1", "\u6469\u5c14\u5206\u6570 helium"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_nitrogen", "0.14", "0.14"}, 
         {"molefraction_oxygen", "0.14", "0.14"}, 
         {"molefraction_water", "0.14", "0.14"}, 
         {"molefraction_argon", "0.14", "0.14"}, 
         {"molefraction_carbon_dioxide", "0.14", "0.14"}, 
         {"molefraction_neon", "0.14", "0.14"}, 
         {"molefraction_helium", "0.14", "0.14"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dtemperature", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dpressure", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_nitrogen", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_oxygen", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_water", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_argon", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_carbon_dioxide", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_neon", "HeatCapacityRatioCpCv_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas13_Dmolefraction_helium"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("funcname", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("compounds", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_nitrogen", "1", "\u6469\u5c14\u5206\u6570 nitrogen"}, 
         {"molefraction_oxygen", "1", "\u6469\u5c14\u5206\u6570 oxygen"}, 
         {"molefraction_water", "1", "\u6469\u5c14\u5206\u6570 water"}, 
         {"molefraction_argon", "1", "\u6469\u5c14\u5206\u6570 argon"}, 
         {"molefraction_carbon_dioxide", "1", "\u6469\u5c14\u5206\u6570 carbon dioxide"}, 
         {"molefraction_neon", "1", "\u6469\u5c14\u5206\u6570 neon"}, 
         {"molefraction_helium", "1", "\u6469\u5c14\u5206\u6570 helium"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_nitrogen", "0.14", "0.14"}, 
         {"molefraction_oxygen", "0.14", "0.14"}, 
         {"molefraction_water", "0.14", "0.14"}, 
         {"molefraction_argon", "0.14", "0.14"}, 
         {"molefraction_carbon_dioxide", "0.14", "0.14"}, 
         {"molefraction_neon", "0.14", "0.14"}, 
         {"molefraction_helium", "0.14", "0.14"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dtemperature", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dpressure", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_nitrogen", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_oxygen", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_water", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_argon", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_carbon_dioxide", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_neon", "ThermalConductivity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas14_Dmolefraction_helium"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("funcname", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mass");

    return model;
  }

  public static Model run2(Model model) {
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("compounds", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"molefraction_nitrogen", "1", "\u6469\u5c14\u5206\u6570 nitrogen"}, 
         {"molefraction_oxygen", "1", "\u6469\u5c14\u5206\u6570 oxygen"}, 
         {"molefraction_water", "1", "\u6469\u5c14\u5206\u6570 water"}, 
         {"molefraction_argon", "1", "\u6469\u5c14\u5206\u6570 argon"}, 
         {"molefraction_carbon_dioxide", "1", "\u6469\u5c14\u5206\u6570 carbon dioxide"}, 
         {"molefraction_neon", "1", "\u6469\u5c14\u5206\u6570 neon"}, 
         {"molefraction_helium", "1", "\u6469\u5c14\u5206\u6570 helium"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"molefraction_nitrogen", "0.14", "0.14"}, 
         {"molefraction_oxygen", "0.14", "0.14"}, 
         {"molefraction_water", "0.14", "0.14"}, 
         {"molefraction_argon", "0.14", "0.14"}, 
         {"molefraction_carbon_dioxide", "0.14", "0.14"}, 
         {"molefraction_neon", "0.14", "0.14"}, 
         {"molefraction_helium", "0.14", "0.14"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dtemperature", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dpressure", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_nitrogen", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_oxygen", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_water", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_argon", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_carbon_dioxide", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_neon", "Viscosity_nitrogen_oxygen_water_argon_carbon_dioxide_neon_helium_Gas15_Dmolefraction_helium"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("tdep1").tag("matpp1lnvaporpressure_water");
    model.thermodynamics().feature("pp1").feature("singlephase1").tag("mat_singlephase1");
    model.thermodynamics().feature("pp1").feature("mat_singlephase1").set("funcname", "Densitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase2").tag("mat_singlephase2");
    model.thermodynamics().feature("pp1").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp1");
    model.thermodynamics().feature("pp1").feature("singlephase3").tag("mat_singlephase3");
    model.thermodynamics().feature("pp1").feature("mat_singlephase3").set("funcname", "HeatCapacityRatioCpCvpp1");
    model.thermodynamics().feature("pp1").feature("singlephase4").tag("mat_singlephase4");
    model.thermodynamics().feature("pp1").feature("mat_singlephase4").set("funcname", "ThermalConductivitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase5").tag("mat_singlephase5");
    model.thermodynamics().feature("pp1").feature("mat_singlephase5").set("funcname", "Viscositypp1");
    model.thermodynamics()
         .createMaterial("comp1", "pp1", "Gas", new String[]{"nitrogen", "oxygen", "water", "argon", "carbon dioxide", "neon", "helium"}, new String[]{"0.14285714285714285", "0.14285714285714285", "0.14285714285714285", "0.14285714285714285", "0.14285714285714285", "0.14285714285714285", "0.14285714285714285"}, new String[]{}, new String[][]{{"density", "Densitypp1"}, {"heatcapacitycp", "HeatCapacityCppp1"}, {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp1"}, {"thermalconductivity", "ThermalConductivitypp1"}, {"viscosity", "Viscositypp1"}}, "Thermodynamics", new String[][]{{"0", "273", "373", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"68", "273", "373", "20", "101325", "201325", "15"}, 
         {"48", "273", "373", "20", "101325", "201325", "15"}, 
         {"52", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mole"});

    model.component("comp1").material("pp1mat1").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - \u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("omega", "2*pi*freq", "\u89d2\u9891\u7387");
    model.component("comp1").variable("var1").set("mu", "intop_pnt(ta.mu)", "\u52a8\u529b\u9ecf\u5ea6");
    model.component("comp1").variable("var1").set("gamma", "intop_pnt(ta.gamma)", "\u6bd4\u70ed\u7387");
    model.component("comp1").variable("var1").set("Cp", "intop_pnt(ta.Cp)", "\u6052\u538b\u70ed\u5bb9");
    model.component("comp1").variable("var1").set("rho0", "intop_pnt(ta.rho0)", "\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("kcond", "intop_pnt(ta.kcond)", "\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1").set("c", "intop_pnt(ta.c)", "\u58f0\u901f");
    model.component("comp1").variable("var1")
         .set("betaT", "intop_pnt(ta.betaT)", "\u7b49\u6e29\u538b\u7f29\u7cfb\u6570");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2")
         .label("\u53d8\u91cf - \u7b49\u6e29\u6781\u9650\uff08\u6781\u4f4e\u9891\u7387\uff09");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("dV", "intop_s(dn)", "\u4f4d\u79fb\u4f53\u79ef\uff08\u6e90\uff09");
    model.component("comp1").variable("var2").set("dpT", "dV/betaT/V", "\u7b49\u6e29\u6781\u9650\u7684\u538b\u529b");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf - \u4f20\u8f93\u7ebf\uff08\u9ad8\u9891\uff09");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3").set("k0", "omega/c", "\u7edd\u70ed\u6ce2\u6570");
    model.component("comp1").variable("var3").set("Z0", "rho0*c", "\u7edd\u70ed\u7279\u6027\u963b\u6297");
    model.component("comp1").variable("var3").set("kv", "sqrt(-i*omega*rho0/mu)", "\u9ecf\u6027\u6ce2\u6570");
    model.component("comp1").variable("var3").set("kth", "sqrt(-i*omega*rho0*Cp/kcond)", "\u70ed\u6ce2\u6570");
    model.component("comp1").variable("var3")
         .set("Yv", "-besselj(2,kv*a)/besselj(0,kv*a)", "\u6807\u91cf\u70ed\u573a\u7684\u5e73\u5747\u503c");
    model.component("comp1").variable("var3")
         .set("Yth", "-besselj(2,kth*a)/besselj(0,kth*a)", "\u6807\u91cf\u9ecf\u6027\u573a\u7684\u5e73\u5747\u503c");
    model.component("comp1").variable("var3")
         .set("Zc", "Z0/sqrt(Yv*(gamma-(gamma-1)*Yth))", "\u6a21\u5f0f\u7279\u6027\u963b\u6297");
    model.component("comp1").variable("var3")
         .set("kc_sq", "k0^2*(gamma-(gamma-1)*Yth)/Yv", "\u6a21\u5f0f\u6ce2\u6570\u5e73\u65b9");
    model.component("comp1").variable("var3").set("kc", "sqrt(kc_sq)", "\u672c\u4f53\u6ce2\u6570");
    model.component("comp1").variable("var3").set("Y_tl", "(i*S*sin(kc*L)/Zc)", "\u4f20\u8f93\u7ebf\u5bfc\u7eb3");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u53d8\u91cf - Vincent \u7b49\u4eba\uff08\u4f4e\u9891\uff09");

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4").set("Rt", "L/(2*a)", "\u6bd4\u7387");
    model.component("comp1").variable("var4").set("alpha_th", "kcond/(rho0*Cp)", "\u70ed\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var4").set("Yr", "0[m^3/s/Pa]", "\u63a5\u6536\u5668\u58f0\u5bfc\u7eb3");
    model.component("comp1").variable("var4")
         .set("Yt", "0[m^3/s/Pa]", "\u53d1\u5c04\u5668\u58f0\u5bfc\u7eb3\uff08\u6e90\uff09");
    model.component("comp1").variable("var4")
         .set("Ep", "sum(sum((8/pi^2)/((m+1/2)^2*lam_n(n)^2)*1/(1+(lam_n(n)^2*Rt^2+(m+1/2)^2*pi^2)*Xp^2/(1+2*Rt)^2),n,1,10),m,0,9)", "Vincent \u7b49\u4eba\uff0c\u65b9\u7a0b 24");
    model.component("comp1").variable("var4")
         .set("Xp", "A/V*(1-i)/sqrt(2)*sqrt(alpha_th/omega)", "Vincent \u7b49\u4eba\uff0c\u65b9\u7a0b 26");
    model.component("comp1").variable("var4")
         .set("p_galf", "dV/(betaT*V)*(1/(1+Yr/(i*omega*betaT*V)-(gamma-1)/gamma*Ep))", "Vincent \u7b49\u4eba\uff0c\u65b9\u7a0b 27");
    model.component("comp1").variable("var4")
         .set("Ya", "i*omega*V/(gamma*p0)*(gamma-(gamma-1)*Ep) + Yt + Yr", "Vincent \u7b49\u4eba\uff0c\u65b9\u7a0b 30");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_s");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_r");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(3);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_pnt");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop3").selection().set(4);
    model.component("comp1").cpl("intop3").set("axisym", false);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1")
         .set("filename", "pressure_reciprocity_calibration_coupler_bessel_zeros.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "lam_n");
    model.component("comp1").func("int1").set("interp", "neighbor");
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", 1, 0);

    model.component("comp1").physics("ta").feature("tam1").set("minput_relativehumidity", "relH");
    model.component("comp1").physics("ta").create("velt1", "VelocityThermoacoustic", 1);
    model.component("comp1").physics("ta").feature("velt1").selection().set(2);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 0);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 2);
    model.component("comp1").physics("ta").feature("velt1").setIndex("u0", "ta.iomega*dn", 2);
    model.component("comp1").physics("ta").feature("velt1").selection("excludedPoints").set(3);
    model.component("comp1").physics("ta").create("iso1", "Isothermal", 1);
    model.component("comp1").physics("ta").feature("iso1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "a/10");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "dvisc0");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "dvisc0");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.2*dvisc");
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "0.2*dvisc");
    model.component("comp1").mesh("mesh1").create("bl3", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl3").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl3").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl3").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl3").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl3").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl3").feature("blp").set("blhmin", "2e-6");
    model.component("comp1").mesh("mesh1").create("bl4", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl4").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl4").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl4").feature("blp").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("bl4").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl4").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl4").feature("blp").set("blhmin", "2e-6");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{0.05, 0.063, 0.08, 0.1, 0.125, 0.16, 0.2, 0.25, 0.315, 0.4, 0.5, 0.63, 0.8, 1, 1.25, 1.6, 2, 2.5, 3.15, 4, 5, 6.3, 8, 10, 12.5, 16, 20, 25, 31.5, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1e3, 1.25e3, 1.6e3, 2e3, 2.5e3, 3.15e3, 4e3, 5e3, 6.3e3, 8e3, 1e4}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "ta.v_inst");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(1);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316 (ta)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "ta.T_t");
    model.result("pg3").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5747\u65b9\u6839\u58f0\u901f (ta)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ta.v_rms");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 14, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 24, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 34, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 14, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 24, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 34, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8026\u5408\u5668\u4e2d\u7684\u538b\u529b\uff1areal(p)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "f (Hz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").feature("ptgr1").set("expr", "real(ta.p_t)");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(dpT)", 0);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u7b49\u6e29\u6781\u9650\uff08\u6781\u4f4e\u9891\u7387\uff09", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "real(i*omega*dV/Y_tl)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u4f20\u8f93\u7ebf\uff08\u9ad8\u9891\uff09", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "real(p_galf)", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "Vincent \u7b49\u4eba\uff08\u4f4e\u9891\uff09", 2);
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("ylog", true);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8026\u5408\u5668\u4e2d\u7684\u538b\u529b\uff1aimag(p)");
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "imag(ta.p_t)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "imag(dpT)", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "imag(i*omega*dV/Y_tl)", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "imag(p_galf)", 2);
    model.result("pg5").run();
    model.result("pg5").set("ylog", false);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u8f6c\u79fb\u963b\u6297\uff1areal(Z) \u548c imag(Z)");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f (Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Z<sub>a,12</sub> (kg/(m<sup>4</sup>s))");
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "real((intop_s(ta.p_t)/S)/(ta.iomega*dn*S))", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "real(Z)\uff0cCOMSOL \u6a21\u578b", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "-imag((intop_s(ta.p_t)/S)/(ta.iomega*dn*S))", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "-imag(Z)\uff0cCOMSOL \u6a21\u578b", 1);
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").setIndex("expr", "real(1/Y_tl)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "real(Z)\uff0c\u4f20\u8f93\u7ebf", 0);
    model.result("pg6").feature("glob2").setIndex("expr", "-imag(1/Y_tl)", 1);
    model.result("pg6").feature("glob2").setIndex("descr", "-imag(Z)\uff0c\u4f20\u8f93\u7ebf", 1);
    model.result("pg6").feature("glob2").set("linestyle", "dashed");
    model.result("pg6").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg6").run();
    model.result("pg6").create("glob3", "Global");
    model.result("pg6").feature("glob3").set("markerpos", "datapoints");
    model.result("pg6").feature("glob3").set("linewidth", "preference");
    model.result("pg6").feature("glob3").setIndex("expr", "real(1/Ya)", 0);
    model.result("pg6").feature("glob3").setIndex("descr", "real(Z)\uff0cVincent \u7b49\u4eba", 0);
    model.result("pg6").feature("glob3").setIndex("expr", "-imag(1/Ya)", 1);
    model.result("pg6").feature("glob3").setIndex("descr", "-imag(Z)\uff0cVincent \u7b49\u4eba", 1);
    model.result("pg6").feature("glob3").set("linestyle", "none");
    model.result("pg6").feature("glob3").set("linecolor", "cyclereset");
    model.result("pg6").feature("glob3").set("linemarker", "point");
    model.result("pg6").feature("glob3").set("markerpos", "interp");
    model.result("pg6").feature("glob3").set("markers", 25);
    model.result("pg6").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("par1", "Tg");
    model.result().dataset("grid1").set("parmin1", 273.15);
    model.result().dataset("grid1").set("parmax1", 323.15);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5bc6\u5ea6");
    model.result("pg7").set("data", "grid1");
    model.result("pg7").setIndex("looplevelinput", "first", 0);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u6e29\u5ea6 (<sup>o</sup>C)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u5bc6\u5ea6 (kg/m<sup>3</sup>)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1")
         .set("expr", "subst(pp1mat1.def.rho,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0)");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "relH = 0.0", 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2")
         .set("expr", "subst(pp1mat1.def.rho,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.2)");
    model.result("pg7").feature("lngr2").setIndex("legends", "relH = 0.2", 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3")
         .set("expr", "subst(pp1mat1.def.rho,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.4)");
    model.result("pg7").feature("lngr3").setIndex("legends", "relH = 0.4", 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr4", "lngr3");
    model.result("pg7").run();
    model.result("pg7").feature("lngr4")
         .set("expr", "subst(pp1mat1.def.rho,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.8)");
    model.result("pg7").feature("lngr4").setIndex("legends", "relH = 0.8", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u52a8\u529b\u9ecf\u5ea6");
    model.result("pg8").set("ylabel", "\u52a8\u529b\u9ecf\u5ea6 (Pa\\cdot s)");
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1")
         .set("expr", "subst(pp1mat1.def.mu,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0)");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2")
         .set("expr", "subst(pp1mat1.def.mu,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.2)");
    model.result("pg8").run();
    model.result("pg8").feature("lngr3")
         .set("expr", "subst(pp1mat1.def.mu,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.4)");
    model.result("pg8").run();
    model.result("pg8").feature("lngr4")
         .set("expr", "subst(pp1mat1.def.mu,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.8)");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u70ed\u5bfc\u7387");
    model.result("pg9").set("ylabel", "\u70ed\u5bfc\u7387 (W/(m\\cdot K))");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1")
         .set("expr", "subst(pp1mat1.def.k_iso,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0)");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2")
         .set("expr", "subst(pp1mat1.def.k_iso,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.2)");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3")
         .set("expr", "subst(pp1mat1.def.k_iso,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.4)");
    model.result("pg9").run();
    model.result("pg9").feature("lngr4")
         .set("expr", "subst(pp1mat1.def.k_iso,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.8)");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u58f0\u901f");
    model.result("pg10").set("ylabel", "\u58f0\u901f (m/s)");
    model.result("pg10").run();
    model.result("pg10").feature("lngr1")
         .set("expr", "subst(pp1mat1.def.c,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0)");
    model.result("pg10").run();
    model.result("pg10").feature("lngr2")
         .set("expr", "subst(pp1mat1.def.c,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.2)");
    model.result("pg10").run();
    model.result("pg10").feature("lngr3")
         .set("expr", "subst(pp1mat1.def.c,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.4)");
    model.result("pg10").run();
    model.result("pg10").feature("lngr4")
         .set("expr", "subst(pp1mat1.def.c,minput.T,Tg[K/m],minput.pA,p0,minput.phi,0.8)");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg7").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg6");
    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup("grp1").add("plotgroup", "pg8");
    model.nodeGroup("grp1").add("plotgroup", "pg9");
    model.nodeGroup("grp1").add("plotgroup", "pg10");
    model.nodeGroup("grp1").label("\u6750\u6599\u5c5e\u6027");

    model.result("pg2").run();

    model
         .title("\u5177\u6709\u8be6\u7ec6\u6e7f\u7a7a\u6c14\u6750\u6599\u5c5e\u6027\u7684\u538b\u529b\u4e92\u6613\u6821\u51c6\u8026\u5408\u5668");

    model
         .description("\u5728\u6821\u51c6\u9ad8\u4fdd\u771f\u6d4b\u91cf\u9ea6\u514b\u98ce\u65f6\uff0c\u4eba\u4eec\u5e38\u4f7f\u7528\u538b\u529b\u4e92\u6613\u6821\u51c6\u65b9\u6cd5\u3002\u5728\u6821\u51c6\u8fc7\u7a0b\u4e2d\uff0c\u4e24\u4e2a\u9ea6\u514b\u98ce\u5206\u522b\u8fde\u63a5\u5728\u4e00\u4e2a\u5c01\u95ed\u5706\u67f1\u5f62\u8154\u4f53\u7684\u4e24\u7aef\u3002\u4e86\u89e3\u8fd9\u7c7b\u8154\u4f53\u4e2d\u7684\u58f0\u573a\u5728\u6b64\u8fc7\u7a0b\u4e2d\u5c24\u4e3a\u91cd\u8981\uff0c\u5305\u62ec\u6240\u6709\u70ed\u9ecf\u6027\u58f0\u5b66\u6548\u5e94\uff0c\u4f8b\u5982\uff0c\u8f83\u9ad8\u9891\u7387\u4e0b\u7684\u58f0\u8fb9\u754c\u5c42\u548c\u8f83\u4f4e\u9891\u7387\u4e0b\u5411\u7b49\u6e29\u7279\u6027\u7684\u8fc7\u6e21\u3002\n\n\u672c\u4f8b\u5efa\u7acb\u4e86\u4e00\u4e2a\u7b80\u5355\u7684\u6821\u51c6\u8026\u5408\u5668\u6a21\u578b\uff0c\u5e76\u8ba8\u8bba\u4e86\u6267\u884c\u9ad8\u7cbe\u5ea6\u7edd\u5bf9\u503c\u4eff\u771f\u65f6\u7684\u91cd\u8981\u6ce8\u610f\u4e8b\u9879\u3002\u6a21\u578b\u7ed3\u679c\u5305\u62ec\u7528\u4e8e\u4e92\u6613\u6821\u51c6\u7684\u58f0\u8f6c\u79fb\u963b\u6297\u548c\u8026\u5408\u5668\u4e2d\u7684\u538b\u529b\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5206\u6790\u9884\u6d4b\u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u5305\u542b\u4f7f\u7528 COMSOL Multiphysics \u4e2d\u7684\u201c\u70ed\u529b\u5b66\u201d\u529f\u80fd\u8fdb\u884c\u7cbe\u786e\u7684\u6750\u6599\u5c5e\u6027\u8ba1\u7b97\u3002\u8fd9\u6837\u5c31\u53ef\u4ee5\u6839\u636e\u73af\u5883\u538b\u529b\u3001\u6e29\u5ea6\u548c\u76f8\u5bf9\u6e7f\u5ea6\u6765\u8bbe\u7f6e\u6e7f\u7a7a\u6c14\u6750\u6599\u3002");

    return model;
  }

  public static Model run3(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pressure_reciprocity_calibration_coupler.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
