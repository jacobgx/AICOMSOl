/*
 * battery_pack_6s2p.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class battery_pack_6s2p {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("bp", "BatteryPack", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/bp", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Arrh");
    model.component("comp1").func("an1").set("expr", "exp(Ea/R_const*(1/Temp-1/T0))");
    model.component("comp1").func("an1").set("args", "Ea, Temp");
    model.component("comp1").func("an1").setIndex("argunit", "J/mol", 0);
    model.component("comp1").func("an1").setIndex("argunit", "K", 1);
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("E_OCP");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "battery_pack_6s2p_E_OCP_data.txt");
    model.component("comp1").func("int1").setIndex("argunit", "1", 0);
    model.component("comp1").func("int1").setIndex("fununit", "V", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("dEdT");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "battery_pack_6s2p_dEdT_data.txt");
    model.component("comp1").func("int2").setIndex("argunit", "1", 0);
    model.component("comp1").func("int2").setIndex("fununit", "V/K", 0);

    model.component("comp1").geom("geom1").insertFile("battery_pack_6s2p_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel1");

    model.component("comp1").view("view1").set("transparency", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7535\u6c60\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("C_rate", "4", "\u500d\u7387");
    model.param("par2").set("Q_cell", "4[A*h]", "\u7535\u6c60\u5355\u5143\u5bb9\u91cf");
    model.param("par2").set("I_1C", "Q_cell/1[h]", "1C \u7535\u6d41");
    model.param("par2").set("kT_batt_il", "30[W/m/K]", "\u5c42\u95f4\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kT_batt_tl", "1[W/m/K]", "\u7a7f\u5c42\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("Ea_eta1C", "24[kJ/mol]", "\u6d3b\u5316\u80fd");
    model.param("par2").set("Ea_J0", "-59[kJ/mol]", "\u6d3b\u5316\u80fd");
    model.param("par2").set("Ea_Tau", "24[kJ/mol]", "\u6d3b\u5316\u80fd");
    model.param("par2").set("T0", "20[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param("par2").set("J0_0", "0.85", "\u53c2\u8003\u6e29\u5ea6\u7684 J0");
    model.param("par2").set("tau_0", "1000[s]", "\u53c2\u8003\u6e29\u5ea6\u7684 tau");
    model.param("par2").set("eta_1C", "4.5[mV]", "\u53c2\u8003\u6e29\u5ea6\u7684 eta_1C");
    model.param("par2").set("rho_batt", "2000[kg/m^3]", "\u7535\u6c60\u5bc6\u5ea6");
    model.param("par2").set("Cp_batt", "1400[J/(kg*K)]", "\u7535\u6c60\u70ed\u5bb9");
    model.param("par2").set("ht", "30[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570");
    model.param("par2").set("T_init", "20[degC]", "\u521d\u59cb/\u5916\u90e8\u6e29\u5ea6");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat1").selection().named("geom1_sel4");
    model.component("comp1").material("mat2").selection().named("geom1_comsel1");

    model.component("comp1").physics("bp").selection().named("geom1_unisel2");
    model.component("comp1").physics("bp").feature("batt").selection().named("geom1_unisel1");
    model.component("comp1").physics("bp").feature("batt").set("Q_cell0", "Q_cell");
    model.component("comp1").physics("bp").feature("batt").set("SOC_pack0", 1);
    model.component("comp1").physics("bp").feature("batt").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("bp").feature("batt").feature("cep1").set("Eocvdef", "int1");
    model.component("comp1").physics("bp").feature("batt").feature("cep1").set("dEocvdTdef", "int2");
    model.component("comp1").physics("bp").feature("batt").feature("vl1")
         .set("eta_ir1C", "eta_1C*Arrh(Ea_eta1C, bp.T_cell)");
    model.component("comp1").physics("bp").feature("batt").feature("vl1").set("J0", "J0_0*Arrh(Ea_J0,bp.T_cell)");
    model.component("comp1").physics("bp").feature("batt").feature("vl1")
         .set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("bp").feature("batt").feature("vl1").set("tau", "tau_0*Arrh(Ea_Tau,bp.T_cell)");
    model.component("comp1").physics("bp").feature("ccnd").selection().named("geom1_comsel1");
    model.component("comp1").physics("bp").feature("ccnd").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("bp").feature("ccnd").feature("egnd1").selection().set(1);
    model.component("comp1").physics("bp").feature("ccnd").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("bp").feature("ccnd").feature("ec1").selection().set(167);
    model.component("comp1").physics("bp").feature("ccnd").feature("ec1").set("Its", "-I_1C*C_rate");
    model.component("comp1").physics("bp").feature("nc").selection().set(34, 79, 128);
    model.component("comp1").physics("bp").feature("pc").selection().set(31, 82, 125);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_sel5");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "ht");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_init");
    model.component("comp1").physics("ht").create("bl1", "BatteryLayers", 3);
    model.component("comp1").physics("ht").feature("bl1").selection().named("geom1_unisel1");
    model.component("comp1").physics("ht").feature("bl1").set("LayerConfiguration", "SpirallyWound");
    model.component("comp1").physics("ht").feature("bl1").set("ThroughLayerConductivity", "kT_batt_tl");
    model.component("comp1").physics("ht").feature("bl1").set("InLayerConductivity", "kT_batt_il");
    model.component("comp1").physics("ht").feature("bl1").set("Density", "rho_batt");
    model.component("comp1").physics("ht").feature("bl1").set("HeatCapacity", "Cp_batt");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").selection().named("geom1_comsel1");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "Temp1");
    model.component("comp1").probe("var1").label("\u6e29\u5ea6\uff0c\u7535\u6c60 1");
    model.component("comp1").probe("var1").set("expr", "bp.T_cell_1");
    model.component("comp1").probe("var1").set("unit", "degC");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u7535\u6c60 1");

    model.result().table().create("tbl1", "Table");

    model.component("comp1").probe("var1").set("table", "tbl1");
    model.component("comp1").probe("var1").set("window", "window1");
    model.component("comp1").probe("var1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2").label("\u6e29\u5ea6\uff0c\u7535\u6c60 2");
    model.component("comp1").probe("var2").set("probename", "Temp2");
    model.component("comp1").probe("var2").set("expr", "bp.T_cell_2");
    model.component("comp1").probe("var2").set("descr", "\u7535\u6c60 2");
    model.component("comp1").probe().duplicate("var3", "var2");
    model.component("comp1").probe("var3").label("\u6e29\u5ea6\uff0c\u7535\u6c60 3");
    model.component("comp1").probe("var3").set("probename", "Temp3");
    model.component("comp1").probe("var3").set("expr", "bp.T_cell_3");
    model.component("comp1").probe("var3").set("descr", "\u7535\u6c60 3");
    model.component("comp1").probe().duplicate("var4", "var3");
    model.component("comp1").probe("var4").label("\u7535\u4f4d\uff0c\u7535\u6c60 1");
    model.component("comp1").probe("var4").set("probename", "Ecell1");
    model.component("comp1").probe("var4").set("expr", "bp.E_cell_1");
    model.component("comp1").probe("var4").set("descr", "\u7535\u6c60 1");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("var4").set("table", "tbl2");
    model.component("comp1").probe("var4").set("window", "window2");
    model.component("comp1").probe("var4").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe().duplicate("var5", "var4");
    model.component("comp1").probe("var5").label("\u7535\u4f4d\uff0c\u7535\u6c60 2");
    model.component("comp1").probe("var5").set("probename", "Ecell2");
    model.component("comp1").probe("var5").set("expr", "bp.E_cell_2");
    model.component("comp1").probe("var5").set("descr", "\u7535\u6c60 2");
    model.component("comp1").probe().duplicate("var6", "var5");
    model.component("comp1").probe("var6").label("\u7535\u4f4d\uff0c\u7535\u6c60 3");
    model.component("comp1").probe("var6").set("probename", "Ecell3");
    model.component("comp1").probe("var6").set("expr", "bp.E_cell_3");
    model.component("comp1").probe("var6").set("descr", "\u7535\u6c60 3");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "0 0.8/C_rate");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");
    model.component("comp1").probe("var5").genResult("none");
    model.component("comp1").probe("var6").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"bp.E_cell_max", "bp.E_cell_avg", "bp.E_cell_min"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u7535\u538b", "\u7535\u6c60\u7535\u538b", "\u7535\u6c60\u7535\u538b"});
    model.result("pg3").label("\u7535\u6c60\u7535\u538b (bp)");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "E<sub>max</sub>", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "E<sub>avg</sub>", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "E<sub>min</sub>", 2);
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("unit", new String[]{"", "", ""});
    model.result("pg3").feature("glob2").set("expr", new String[]{"bp.I_app_max", "bp.I_app_avg", "bp.I_app_min"});
    model.result("pg3").feature("glob2")
         .set("descr", new String[]{"\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41"});
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob2").set("legendmethod", "manual");
    model.result("pg3").feature("glob2").setIndex("legends", "I<sub>max</sub>", 0);
    model.result("pg3").feature("glob2").setIndex("legends", "I<sub>avg</sub>", 1);
    model.result("pg3").feature("glob2").setIndex("legends", "I<sub>min</sub>", 2);
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg3").set("titletype", "none");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"bp.SOC_max", "bp.SOC_avg", "bp.SOC_min"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u8377\u7535\u72b6\u6001", "\u8377\u7535\u72b6\u6001", "\u8377\u7535\u72b6\u6001"});
    model.result("pg4").label("\u8377\u7535\u72b6\u6001 (bp)");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "SOC<sub>max</sub>", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "SOC<sub>avg</sub>", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "SOC<sub>min</sub>", 2);
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("unit", new String[]{"", "", ""});
    model.result("pg4").feature("glob2").set("expr", new String[]{"bp.I_app_max", "bp.I_app_avg", "bp.I_app_min"});
    model.result("pg4").feature("glob2")
         .set("descr", new String[]{"\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41"});
    model.result("pg4").feature("glob2").set("linestyle", "dashed");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").feature("glob2").set("legendmethod", "manual");
    model.result("pg4").feature("glob2").setIndex("legends", "I<sub>max</sub>", 0);
    model.result("pg4").feature("glob2").setIndex("legends", "I<sub>avg</sub>", 1);
    model.result("pg4").feature("glob2").setIndex("legends", "I<sub>min</sub>", 2);
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").set("titletype", "none");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").label("\u7535\u52bf (bp)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phis"});
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6 (ht)");
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("expr", "T");
    model.result("pg6").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg3").run();
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().named("geom1_unisel2");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset3");
    model.result().dataset("mir1").set("quickx", "2.5*(d_batt)");
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset("mir2").set("quicky", "d_batt/2");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u6e29\u5ea6 (degC)");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u4f4d vs. \u65f6\u95f4");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("data", "mir2");
    model.result("pg7").label("\u6e29\u5ea6");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg7").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg7").feature("surf1").set("unit", "degC");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").run();
    model.result("pg1").set("window", "window1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result().remove("pg2");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result("pg3").run();
    model.result().move("pg3", 1);
    model.result().move("pg3", 2);

    model.title("\u5706\u67f1\u7535\u6c60\u7ec4\u7684\u70ed\u5206\u5e03");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7535\u6c60\u7ec4\u5728 4C \u653e\u7535\u8fc7\u7a0b\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u3002\n\n\u8be5\u7535\u6c60\u7ec4\u9996\u5148\u901a\u8fc7\u5e76\u8054\u4e24\u4e2a\u5706\u67f1\u7535\u6c60\u6784\u6210\uff0c\u7136\u540e\u5c06\u516d\u5bf9\u5e76\u8054\u7684\u7535\u6c60\u4e32\u8054\u8d77\u6765\uff0c\u5f62\u6210\u5b8c\u6574\u7684\u7535\u6c60\u7ec4\u3002\uff08\u8fd9\u4e5f\u79f0\u4e3a 6s2p \u914d\u7f6e\u3002\uff09\n\n\u5176\u4e2d\u4f7f\u7528\u4e86\u4e24\u6b21\u5bf9\u79f0\u6027\uff0c\u56e0\u6b64\u53ea\u9700\u6c42\u89e3\u4e09\u4e2a\u7535\u6c60\u7684\u6e29\u5ea6\u5206\u5e03\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u7535\u6c60\u7ec4\u201d\u63a5\u53e3\u4ea7\u751f\u9002\u5f53\u7684\u70ed\u6e90\uff0c\u7136\u540e\u5c06\u5176\u8026\u5408\u5230\u4e09\u7ef4\u51e0\u4f55\u4e2d\u7684\u4e00\u4e2a\u201c\u4f20\u70ed\u201d\u63a5\u53e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("battery_pack_6s2p.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
