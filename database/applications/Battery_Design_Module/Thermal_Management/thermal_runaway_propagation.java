/*
 * thermal_runaway_propagation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class thermal_runaway_propagation {

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
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/bp", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("thermal_runaway_propagation_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("adjsel1");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("Q_cell", "2.5[A*h]", "\u7535\u6c60\u5bb9\u91cf");
    model.param("par2")
         .set("n_batt", "nx_batt*ny_batt", "\u7535\u6c60\u7ec4\u4e2d\u7684\u7535\u6c60\u5355\u5143\u6570");
    model.param("par2")
         .set("I_1C_pack", "Q_cell*ny_batt/1[h]", "\u5171 1C \u7684\u7535\u6c60\u7ec4\u7535\u6d41\uff08\u5e94\u7528\u5728\u7ec8\u7aef\u65f6\uff09");
    model.param("par2").set("SOC_0", "0.25", "\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param("par2").set("C_rate", "2", "\u500d\u7387");
    model.param("par2").set("T_ext", "30[degC]", "\u5916\u90e8\u6e29\u5ea6");
    model.param("par2").set("h_conv", "10[W/m^2/K]", "\u5bf9\u6d41\u51b7\u5374\u4f20\u70ed\u7cfb\u6570");
    model.param("par2").set("kT_batt_il", "30[W/m/K]", "\u7535\u6c60\u5c42\u95f4\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kT_batt_tl", "1[W/m/K]", "\u7535\u6c60\u7a7f\u5c42\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("rho_batt", "2000[kg/m^3]", "\u7535\u6c60\u5e73\u5747\u5bc6\u5ea6");
    model.param("par2").set("Cp_batt", "1400[J/(kg*K)]", "\u7535\u6c60\u5e73\u5747\u70ed\u5bb9");
    model.param("par2").set("J0", "0.85", "\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("tau", "1000[s]", "\u6269\u6563\u65f6\u95f4\u5e38\u6570");
    model.param("par2").set("eta_1C", "4.5[mV]", "1C \u6b27\u59c6\u8fc7\u7535\u4f4d");
    model.param("par2").set("T_trigger", "80[degC]", "\u70ed\u4e8b\u4ef6\u7684\u89e6\u53d1\u6e29\u5ea6");
    model.param("par2")
         .set("t_start", "1[min]", "\u7b2c\u4e00\u6b21\u7535\u6c60\u70ed\u5931\u63a7\u7684\u5f00\u59cb\u65f6\u95f4");
    model.param("par2")
         .set("t_peak", "1[s]", "\u7535\u6c60\u70ed\u5931\u63a7\u8fc7\u7a0b\u4e2d\u7684\u5cf0\u503c\u65f6\u95f4");
    model.param("par2").set("t_tr", "6[s]", "\u7535\u6c60\u70ed\u5931\u63a7\u65f6\u95f4");
    model.param("par2")
         .set("W_tr", "32[kJ]", "\u7535\u6c60\u70ed\u5931\u63a7\u8fc7\u7a0b\u4e2d\u7684\u603b\u70ed\u91ca\u653e\u91cf");
    model.param("par2")
         .set("Qh_peak", "2*W_tr/t_tr", "\u7535\u6c60\u70ed\u5931\u63a7\u8fc7\u7a0b\u4e2d\u7684\u5cf0\u503c\u70ed\u6e90");
    model.param("par2")
         .set("E_max_cell", "4.2[V]", "\u5145\u7535\u671f\u95f4\u7684\u6700\u5927\u7535\u6c60\u7535\u538b");
    model.param("par2")
         .set("E_max_pack", "nx_batt*E_max_cell", "\u5145\u7535\u671f\u95f4\u7684\u6700\u5927\u7535\u6c60\u7ec4\u7535\u538b");

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
    model.component("comp1").material("mat2").label("Acrylic plastic");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 1000);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Steel AISI 4340");
    model.component("comp1").material("mat3").set("family", "steel");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").selection().named("geom1_comsel1");
    model.component("comp1").material("mat2").selection().named("geom1_pi3_csel1_dom");
    model.component("comp1").material("mat3").selection().named("geom1_pi2_boxsel1");

    model.component("comp1").physics("bp").selection().named("geom1_unisel1");
    model.component("comp1").physics("bp").feature("batt").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("bp").feature("batt").set("Q_cell0", "Q_cell");
    model.component("comp1").physics("bp").feature("batt").set("SOC_pack0", "SOC_0");
    model.component("comp1").physics("bp").feature("batt").feature("cep1").set("SOC_Eocv", new int[]{});
    model.component("comp1").physics("bp").feature("batt").feature("cep1").set("Eocv", new int[]{});
    model.component("comp1").physics("bp").feature("batt").feature("cep1")
         .set("SOC_Eocv", new String[]{"0.000", "0.010", "0.020", "0.030", "0.040", "0.050", "0.060", "0.070", "0.080", "0.090", 
         "0.100", "0.110", "0.120", "0.130", "0.140", "0.150", "0.160", "0.170", "0.180", "0.190", 
         "0.200", "0.210", "0.220", "0.230", "0.240", "0.250", "0.260", "0.270", "0.280", "0.290", 
         "0.300", "0.310", "0.320", "0.330", "0.340", "0.350", "0.360", "0.370", "0.380", "0.390", 
         "0.400", "0.410", "0.420", "0.430", "0.440", "0.450", "0.460", "0.470", "0.480", "0.490", 
         "0.500", "0.510", "0.520", "0.530", "0.540", "0.550", "0.560", "0.570", "0.580", "0.590", 
         "0.600", "0.610", "0.620", "0.630", "0.640", "0.650", "0.660", "0.670", "0.680", "0.690", 
         "0.700", "0.710", "0.720", "0.730", "0.740", "0.750", "0.760", "0.770", "0.780", "0.790", 
         "0.800", "0.810", "0.820", "0.830", "0.840", "0.850", "0.860", "0.870", "0.880", "0.890", 
         "0.900", "0.910", "0.920", "0.930", "0.940", "0.950", "0.960", "0.970", "0.980", "0.990", 
         "1.000"});
    model.component("comp1").physics("bp").feature("batt").feature("cep1")
         .set("Eocv", new String[]{"1.310", "2.261", "2.833", "3.184", "3.393", "3.511", "3.587", "3.626", "3.656", "3.685", 
         "3.699", "3.711", "3.723", "3.735", "3.747", "3.759", "3.767", "3.775", "3.783", "3.791", 
         "3.800", "3.808", "3.816", "3.824", "3.832", "3.840", "3.845", "3.850", "3.854", "3.859", 
         "3.864", "3.868", "3.873", "3.878", "3.882", "3.887", "3.891", "3.896", "3.900", "3.905", 
         "3.910", "3.916", "3.921", "3.928", "3.934", "3.941", "3.947", "3.953", "3.959", "3.965", 
         "3.970", "3.975", "3.979", "3.983", "3.986", "3.989", "3.992", "3.995", "3.997", "4.000", 
         "4.002", "4.004", "4.007", "4.009", "4.011", "4.014", "4.017", "4.020", "4.023", "4.026", 
         "4.029", "4.032", "4.035", "4.035", "4.036", "4.037", "4.037", "4.038", "4.039", "4.039", 
         "4.040", "4.041", "4.041", "4.042", "4.042", "4.043", "4.043", "4.045", "4.049", "4.052", 
         "4.055", "4.058", "4.061", "4.064", "4.069", "4.079", "4.092", "4.111", "4.134", "4.167", 
         "4.204"});
    model.component("comp1").physics("bp").feature("batt").feature("vl1").set("eta_ir1C", "eta_1C");
    model.component("comp1").physics("bp").feature("batt").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("bp").feature("batt").feature("vl1")
         .set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("bp").feature("batt").feature("vl1").set("tau", "tau");
    model.component("comp1").physics("bp").feature("batt").create("te1", "ThermalEvent", 3);
    model.component("comp1").physics("bp").feature("batt").feature("te1").selection().set(24, 25, 26);
    model.component("comp1").physics("bp").feature("batt").feature("te1").set("EventConditionType", "ExplicitTime");
    model.component("comp1").physics("bp").feature("batt").feature("te1").set("t_exp", "t_start");
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("t_te_tab", "t_peak", 1, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("Qh_te_tab", "Qh_peak", 1, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("t_te_tab", 0, 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("Qh_te_tab", 0, 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("Qh_te_tab", 0, 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("t_te_tab", 0, 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("Qh_te_tab", 0, 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1").setIndex("t_te_tab", "t_tr", 2, 0);
    model.component("comp1").physics("bp").feature("batt").feature("te1")
         .set("AddOhmicOverPotentialAfterEvent", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u6545\u969c\u7535\u6c60");
    model.component("comp1").selection("sel1").set(24, 25, 26);

    model.component("comp1").physics("bp").feature("batt").feature("te1").selection().named("sel1");

    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u975e\u6545\u969c\u7535\u6c60");
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1"});

    model.component("comp1").physics("bp").feature("batt").feature().duplicate("te2", "te1");
    model.component("comp1").physics("bp").feature("batt").feature("te2").selection().named("dif1");
    model.component("comp1").physics("bp").feature("batt").feature("te2").set("EventConditionType", "maxT");
    model.component("comp1").physics("bp").feature("batt").feature("te2").set("maxT_te", "T_trigger");
    model.component("comp1").physics("bp").feature("ccnd").selection().named("geom1_pi2_boxsel1");
    model.component("comp1").physics("bp").feature("ccnd").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("bp").feature("ccnd").feature("egnd1").selection().named("geom1_pi2_boxsel4");
    model.component("comp1").physics("bp").feature("ccnd").create("cdc1", "ChargeDischargeCycling", 2);
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").selection().named("geom1_pi2_boxsel5");
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").set("Ich", "C_rate*I_1C_pack");
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").set("Vmax", "E_max_pack");
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").set("item.OCCH", true);
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").set("trech", "1[h]");
    model.component("comp1").physics("bp").feature("ccnd").feature("cdc1").set("StartWith", "Charge_first");
    model.component("comp1").physics("bp").feature("nc").selection().named("geom1_unisel2");
    model.component("comp1").physics("bp").feature("pc").selection().named("geom1_unisel3");
    model.component("comp1").physics("ht").create("bl1", "BatteryLayers", 3);
    model.component("comp1").physics("ht").feature("bl1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("bl1").set("LayerConfiguration", "SpirallyWound");
    model.component("comp1").physics("ht").feature("bl1").set("ThroughLayerConductivity", "kT_batt_tl");
    model.component("comp1").physics("ht").feature("bl1").set("InLayerConductivity", "kT_batt_il");
    model.component("comp1").physics("ht").feature("bl1").set("Density", "rho_batt");
    model.component("comp1").physics("ht").feature("bl1").set("HeatCapacity", "Cp_batt");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_ext");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_conv");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_ext");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("geom1_boxsel2");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("geom1_boxsel3");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_boxsel2");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "gap/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrow", 1.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "bp.ccnd.cdc1.phis0");
    model.component("comp1").probe("var1").set("descr", "\u7535\u6c60\u7535\u4f4d");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u7535\u6c60\u7ec4\u7535\u538b");

    model.result().table().create("tbl1", "Table");

    model.component("comp1").probe("var1").set("table", "tbl1");
    model.component("comp1").probe("var1").set("window", "window1");
    model.component("comp1").probe("var1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").selection().named("geom1_csel1_dom");
    model.component("comp1").probe("dom1").set("expr", "T");
    model.component("comp1").probe("dom1").set("descr", "\u6e29\u5ea6");
    model.component("comp1").probe("dom1").set("unit", "degC");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe("dom1").set("descr", "\u7535\u6c60\u6700\u9ad8\u6e29\u5ea6");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("dom1").set("table", "tbl2");
    model.component("comp1").probe("dom1").set("window", "window1");
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").set("type", "average");
    model.component("comp1").probe("dom2").set("descr", "\u7535\u6c60\u5e73\u5747\u6e29\u5ea6");

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,5,20)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdf", "0.1[s]");
    model.sol("sol1").feature("t1").set("storeudot", false);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"bp.E_cell_max", "bp.E_cell_avg", "bp.E_cell_min"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u7535\u538b", "\u7535\u6c60\u7535\u538b", "\u7535\u6c60\u7535\u538b"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").label("\u7535\u6c60\u7535\u538b (bp)");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "E<sub>max</sub>", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "E<sub>avg</sub>", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "E<sub>min</sub>", 2);
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob2").set("expr", new String[]{"bp.I_app_max", "bp.I_app_avg", "bp.I_app_min"});
    model.result("pg2").feature("glob2")
         .set("descr", new String[]{"\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41", "\u5916\u52a0\u7535\u6c60\u7535\u6d41"});
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "I<sub>max</sub>", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "I<sub>avg</sub>", 1);
    model.result("pg2").feature("glob2").setIndex("legends", "I<sub>min</sub>", 2);
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").set("titletype", "none");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"bp.SOC_max", "bp.SOC_avg", "bp.SOC_min"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u8377\u7535\u72b6\u6001", "\u8377\u7535\u72b6\u6001", "\u8377\u7535\u72b6\u6001"});
    model.result("pg3").label("\u8377\u7535\u72b6\u6001 (bp)");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "SOC<sub>max</sub>", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "SOC<sub>avg</sub>", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "SOC<sub>min</sub>", 2);
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
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 43, 0);
    model.result("pg4").label("\u7535\u52bf (bp)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"phis"});
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature().remove("vol1");
    model.result("pg5").run();
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().named("geom1_adjsel1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("unit", "degC");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("paramindicator", "Time=eval(round(t)) s");

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{6});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{8});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{12});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{16});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{20});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{24});
    model.result("pg5").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7ec4\u7535\u538b\u548c\u6700\u9ad8\u6e29\u5ea6\u63a2\u9488");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "\u7535\u6c60\u6e29\u5ea6 (degC)");
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "E<sub>pack</sub>", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "T<sub>max</sub>", 0);
    model.result("pg1").feature("tblp2").setIndex("legends", "T<sub>avg</sub>", 1);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0,0.25,20)");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "Q_tr");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", "t_peak", 1, 0);
    model.func("int1").setIndex("table", "Qh_peak/1000", 1, 1);
    model.func("int1").setIndex("table", "t_tr", 2, 0);
    model.func("int1").setIndex("table", 0, 2, 1);
    model.func("int1").setIndex("fununit", "kW", 0);
    model.func("int1").setIndex("argunit", "s", 0);
    model.func().remove("int1");

    model.title("\u7535\u6c60\u7ec4\u4e2d\u7684\u70ed\u5931\u63a7\u4f20\u64ad");

    model
         .description("\u6ee5\u7528\u9020\u6210\u7684\u5185\u90e8\u6216\u5916\u90e8\u77ed\u8def\u6216\u8fc7\u70ed\u7b49\uff0c\u53ef\u80fd\u4f7f\u5355\u4e2a\u7535\u6c60\u5355\u5143\u8fdb\u5165\u70ed\u5931\u63a7\u72b6\u6001\uff0c\u5728\u6b64\u671f\u95f4\uff0c\u7535\u6c60\u5355\u5143\u4f1a\u4ea7\u751f\u5927\u91cf\u7684\u70ed\u91cf\u3002\u5982\u679c\u5728\u70ed\u5931\u63a7\u4e8b\u4ef6\u4e2d\uff0c\u76f8\u90bb\u7535\u6c60\u4e4b\u95f4\u4f20\u9012\u4e86\u8db3\u591f\u7684\u70ed\u91cf\uff0c\u5219\u5b83\u4eec\u4e5f\u4f1a\u8fdb\u5165\u70ed\u5931\u63a7\u72b6\u6001\u3002\n\n\u70ed\u5931\u63a7\u5728\u6574\u4e2a\u7535\u6c60\u7ec4\u4e2d\u4f20\u64ad\uff0c\u6784\u6210\u4e86\u4e25\u91cd\u7684\u5b89\u5168\u9690\u60a3\u3002\u5728\u8bbe\u8ba1\u7535\u6c60\u7ec4\u65f6\uff0c\u9700\u8981\u91c7\u53d6\u63aa\u65bd\u6765\u51cf\u8f7b\u5931\u63a7\u4f20\u64ad\u3002\n\n\u672c\u6559\u7a0b\u4f7f\u7528\u57fa\u4e8e\u4e8b\u4ef6\u7684\u70ed\u6e90\u6a21\u62df\u7531 20\u00a0\u4e2a\u5706\u67f1\u5f62\u7535\u6c60\u7ec4\u6210\u7684\u7535\u6c60\u7ec4\u4e2d\u7684\u4f20\u70ed\u548c\u7531\u6b64\u4ea7\u751f\u7684\u70ed\u5931\u63a7\u4f20\u64ad\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_runaway_propagation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
