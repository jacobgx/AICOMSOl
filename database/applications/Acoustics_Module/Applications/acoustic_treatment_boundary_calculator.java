/*
 * acoustic_treatment_boundary_calculator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class acoustic_treatment_boundary_calculator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "400[mm]", "Domain width");
    model.param().set("H", "400[mm]", "Air domain height");
    model.param().set("Hpml", "100[mm]", "PML domain height");
    model.param().set("Hs", "15[mm]", "Sample thickness");
    model.param().set("rho_m", "700[kg/m^3]", "Density of the material");
    model.param().set("E_m", "5e-3[GPa]", "Young's modulus of the material");
    model.param().set("nu_m", "0.25", "Poisson's ratio of the material");
    model.param().set("eta_s_m", "0.05", "Isotropic loss factor");
    model.param().set("epsilon1", "0.95", "Porosity of the porous sample");
    model.param().set("Rf1", "70[kPa*s/m^2]", "Flow resistivity of the porous sample");
    model.param().set("Lv1", "200[um]", "Viscous characteristic length of the porous sample");
    model.param().set("Lth1", "400[um]", "Thermal characteristic length of the porous sample");
    model.param().set("tau1", "1", "Tortuosity of the porous sample");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("Hcav", "100[mm]", "Cavity depth");
    model.param("par2").set("epsilon2", "0.9", "Porosity of the porous cavity");
    model.param("par2").set("Rf2", "30[kPa*s/m^2]", "Flow resistivity of the porous material in the cavity");
    model.param("par2").set("Lv2", "300[um]", "Viscous characteristic length of the porous cavity");
    model.param("par2").set("Lth2", "600[um]", "Thermal characteristic length of the porous cavity");
    model.param("par2").set("tau2", "1", "Tortuosity of the porous cavity");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("theta0", "pi/5[rad]", "Incidence angle from the y-axis");
    model.param("par3").set("theta_min", "0[deg]", "Lowest incidence angle");
    model.param("par3").set("theta_max", "85[deg]", "Highest incidence angle");
    model.param("par3").set("theta_step", "5[deg]", "Angle resolution");
    model.param("par3").set("fmin", "40[Hz]", "Lowest frequency");
    model.param("par3").set("fmax", "12000[Hz]", "Highest frequency");
    model.param("par3").set("lambda_min", "343[m/s]/fmax", "Minimal wavelength");
    model.param("par3").set("fstep", "50[Hz]", "Frequency step");
    model.param("par3").set("octRa", "12", "Octave ratio");
    model.param("par3").set("f0", "1000[Hz]", "Reference used to calculate band center frequencies");
    model.param("par3").set("nmin", "floor(octRa*log2(fmin/f0))", "Lowest band number value");
    model.param("par3").set("nmax", "ceil(octRa*log2(fmax/f0))", "Highest band number value");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "Hcav+Hs+H+Hpml"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-Hcav-Hs"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hcav", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hs", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H", 2);
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material("mat1").selection().set(1, 3, 4);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(4);
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "1/cos(theta0)");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(6);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(6);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("k0", "intop1(acpr.k)", "Wave number");
    model.component("comp1").variable("var1").set("kx_e", "sin(theta0)", "Plane wave direction, x-component");
    model.component("comp1").variable("var1").set("ky_e", "-cos(theta0)", "Plane wave direction, y-component");
    model.component("comp1").variable("var1").set("kx", "k0*kx_e", "Incident wave number, x-component");
    model.component("comp1").variable("var1").set("ky", "k0*ky_e", "Incident wave number, y-component");
    model.component("comp1").variable("var1").set("Pin", "intop2(-acpr.I_by)", "Projected incident power");
    model.component("comp1").variable("var1").set("Pout", "intop2(acpr.I_sy)", "Projected reflected power");
    model.component("comp1").variable("var1").set("alpha", "1-Pout/Pin", "Absorption coefficient");
    model.component("comp1").variable("var1")
         .set("Zn", "aveop1(acpr.p_t/(-acpr.vy))/intop1(acpr.Z)", "Surface impedance (normalized)");

    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(3);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new String[]{"kx_e", "ky_e", "0"});
    model.component("comp1").physics("acpr").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp1").physics("acpr").feature("bpf1").set("rho_mat", "from_mat");
    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("pom1").selection().set(2);
    model.component("comp1").physics("acpr").feature("pom1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("pom1").set("Rf_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("Rf", "Rf1");
    model.component("comp1").physics("acpr").feature("pom1").set("Constants", "Miki");
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("epsilon_p", "epsilon1");
    model.component("comp1").physics("acpr").feature("pom1").set("Lv_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("Lv", "Lv1");
    model.component("comp1").physics("acpr").feature("pom1").set("Lth_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("Lth", "Lth1");
    model.component("comp1").physics("acpr").feature("pom1").set("tau_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("tau", "tau1");
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "EmpiricalPorous");
    model.component("comp1").physics("acpr").create("pom2", "PoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("pom2").selection().set(1);
    model.component("comp1").physics("acpr").feature("pom2").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("pom2").set("Rf_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom2").set("Rf", "Rf2");
    model.component("comp1").physics("acpr").feature("pom2").set("Constants", "Miki");
    model.component("comp1").physics("acpr").feature("pom2").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom2").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom2").set("epsilon_p", "epsilon2");
    model.component("comp1").physics("acpr").feature("pom2").set("Lv_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom2").set("Lv", "Lv2");
    model.component("comp1").physics("acpr").feature("pom2").set("Lth_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom2").set("Lth", "Lth2");
    model.component("comp1").physics("acpr").feature("pom2").set("tau_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom2").set("tau", "tau2");
    model.component("comp1").physics("acpr").feature("pom2").set("FluidModel", "EmpiricalPorous");
    model.component("comp1").physics("acpr").feature("pom2").active(false);
    model.component("comp1").physics("acpr").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc1").selection().set(1, 3, 5, 7, 10, 11, 12, 13);
    model.component("comp1").physics("acpr").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("E", "E_m");
    model.component("comp1").physics("solid").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("nu", "nu_m");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho_m");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s", "eta_s_m");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(4);
    model.component("comp1").physics("solid").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("solid").feature("pc1").selection().set(3, 11);
    model.component("comp1").physics("solid").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("solid").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").selection().set(1, 3, 4);

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").selection().set(6);
    model.component("comp1").multiphysics().create("asb2", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb2").selection().set(4);
    model.component("comp1").multiphysics("asb1").active(false);
    model.component("comp1").multiphysics("asb2").active(false);

    model.component("comp1").physics("solid").active(false);
    model.component("comp1").physics("acpr").selection().set(1, 2, 3, 4);

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "E_m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "E_m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "theta0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(theta_min,theta_step,theta_max)", 0);
    model.study("std1").feature("freq").set("plist", "f0*2^(range(nmin,1,nmax)/octRa)");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("se1");
    model.component("comp1").mesh("mesh1").feature().remove("id1");
    model.component("comp1").mesh("mesh1").feature().remove("dis1");
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").feature().remove("map1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_min/5");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 5);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("edg2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("hmax", "lambda_min/5/2");
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(1, 3, 5);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(10, 11, 12);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4, 6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().setOnlyPlotWhenRequested(true);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "theta0", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Incidence angle", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "freq", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "alpha", 2);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "real(Zn)", 2);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "Surface resistance", 2);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "imag(Zn)", 3);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "Surface reactance", 3);
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("tbls1", "TableSurface");
    model.result("pg1").feature("tbls1").set("source", "evaluationgroup");
    model.result("pg1").feature("tbls1").set("colx", 2);
    model.result("pg1").feature("tbls1").set("coly", 1);
    model.result("pg1").feature("tbls1").set("preprocy", "linear");
    model.result("pg1").feature("tbls1").set("scalingy", "180/pi");
    model.result("pg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "resultTable");
    model.func("int1").setIndex("argunit", "rad", 0);
    model.func("int1").setEntry("columnType", "col2", "arg");
    model.func("int1").setIndex("argunit", "Hz", 1);
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("funcnames", "col3", "alphaM");
    model.func("int1").setIndex("fununit", "1", 0);
    model.func().duplicate("int2", "int1");
    model.func("int2").set("resultTable", "evalGroup:eg2");
    model.func("int2").setEntry("funcnames", "col3", "ReZnM");
    model.func("int2").setEntry("columnType", "col4", "value");
    model.func("int2").setEntry("funcnames", "col4", "ImZnM");
    model.func("int2").setIndex("fununit", "1", 1);

    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("alpha_n", "alphaM(0,freq)", "Normal incidence absorption coefficient");
    model.component("comp1").variable("var2")
         .set("alpha_ran", "integrate(alphaM(q,freq)*sin(2*q),q,0,pi/2)", "Random incidence absorption coefficient");
    model.component("comp1").variable("var2")
         .set("Zn_n", "ReZnM(0,freq)+1i*ImZnM(0,freq)", "Normal incidence surface impedance");
    model.component("comp1").variable("var2")
         .set("Zn_ran", "ReZnM(53[deg],freq)+1i*ImZnM(53[deg],freq)", "Random incidence surface impedance");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/asb2", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "f0*2^(range(nmin,1,nmax)/octRa)");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol21").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").set("legendposoutside", "top");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", 1, 0);
    model.result("pg2").feature("glob1").set("linecolor", "gray");
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").create("oct1", "OctaveBand");
    model.result("pg2").feature("oct1").set("quantity", "bandpower");
    model.result("pg2").feature("oct1").set("markerpos", "datapoints");
    model.result("pg2").feature("oct1").set("linewidth", "preference");
    model.result("pg2").feature("oct1").selection().geom("geom1");
    model.result("pg2").feature("oct1").set("expr", "alpha_n");
    model.result("pg2").feature("oct1").set("exprtype", "general");
    model.result("pg2").feature("oct1").set("quantity", "bandaveragepsd");
    model.result("pg2").feature("oct1").set("type", "outline");
    model.result("pg2").feature("oct1").set("legend", true);
    model.result("pg2").feature("oct1").set("legendmethod", "manual");
    model.result("pg2").feature("oct1").setIndex("legends", "Octave bands", 0);
    model.result("pg2").feature().duplicate("oct2", "oct1");
    model.result("pg2").feature("oct2").set("bandtype", "octave3");
    model.result("pg2").feature("oct2").setIndex("legends", "1/3 octave bands", 0);
    model.result("pg2").feature().duplicate("oct3", "oct2");
    model.result("pg2").feature("oct3").set("quantity", "continuous");
    model.result("pg2").feature("oct3").setIndex("legends", "Continuous spectrum", 0);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").feature("oct1").set("expr", "alpha_ran");
    model.result("pg3").feature("oct2").set("expr", "alpha_ran");
    model.result("pg3").feature("oct3").set("expr", "alpha_ran");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(Zn_n)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Resistance", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").feature("glob2").setIndex("expr", "imag(Zn_n)", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "Reactance", 0);
    model.result("pg4").feature("glob2").set("linestyle", "dashed");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").feature("glob1").setIndex("expr", "real(Zn_ran)", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "imag(Zn_ran)", 0);
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset3");
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "freq", 0);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "real(Zn_n)", 1);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("descr", "Normal Incidence Surface Resistance", 1);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "imag(Zn_n)", 2);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "Normal Incidence Surface Reactance", 2);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "real(Zn_ran)", 1);
    model.result().evaluationGroup("eg4").feature("gev1")
         .setIndex("descr", "Random Incidence Surface Resistance", 1);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "imag(Zn_ran)", 2);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("descr", "Random Incidence Surface Reactance", 2);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().duplicate("eg5", "eg3");
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "real(1/Zn_n)", 1);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("descr", "Real Part of Admittance", 1);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "imag(1/Zn_n)", 2);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("descr", "Imaginary Part of Admittance", 2);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg4");
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "real(1/Zn_ran)", 1);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("descr", "Real Part of Admittance", 1);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "imag(1/Zn_ran)", 2);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("descr", "Imaginary Part of Admittance", 2);
    model.result().evaluationGroup("eg6").run();

    model.func().create("pff1", "PartialFraction");
    model.func("pff1").set("source", "resultTable");
    model.func("pff1").set("resultTable", "evalGroup:eg5");
    model.func("pff1").set("fununit", "1");
    model.func("pff1").run();
    model.func("pff1").createPlot("pg6");

    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").feature("tblp1").set("legendmethod", "manual");
    model.result("pg6").feature("tblp1").setIndex("legends", "Calculated Real Part", 0);
    model.result("pg6").feature("tblp2").set("legend", true);
    model.result("pg6").feature("tblp2").set("legendmethod", "manual");
    model.result("pg6").feature("tblp2").setIndex("legends", "Calculated Imaginary Part", 0);
    model.result("pg6").feature("plot1").setIndex("legends", "Fitted Real Part", 0);
    model.result("pg6").feature("plot2").setIndex("legends", "Fitted Imaginary Part", 0);
    model.result("pg6").run();

    model.func().duplicate("pff2", "pff1");
    model.func("pff2").set("resultTable", "evalGroup:eg6");
    model.func("pff2").run();
    model.func("pff2").createPlot("pg7");

    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("xlog", true);
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "Calculated Real Part", 0);
    model.result("pg7").feature("tblp2").set("legend", true);
    model.result("pg7").feature("tblp2").set("legendmethod", "manual");
    model.result("pg7").feature("tblp2").setIndex("legends", "Calculated Imaginary Part", 0);
    model.result("pg7").feature("plot1").setIndex("legends", "Fitted Real Part", 0);
    model.result("pg7").feature("plot2").setIndex("legends", "Fitted Imaginary Part", 0);
    model.result("pg7").run();

    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup().remove("grp2");

    model.title(null);

    model.description("");

    model.label("acoustic_treatment_boundary_calculator_embedded.mph");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1").set("includesummary", false);
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("param1").set("noderef", "par2");
    model.result().report("rpt1").feature("sec2").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1").set("noderef", "par3");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("std1", "Study");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("pg1").set("noderef", "pg1");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("pg1").set("caption", "none");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("pg1").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("mtbl2", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("mtbl2").set("noderef", "octtbl2");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("pg1").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("mtbl1").set("noderef", "octtbl3");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("mtbl2").set("noderef", "octtbl4");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec4", "sec1");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("pg1").set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec5", "sec4");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("pg1").set("noderef", "pg5");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("field1", "StringDataField");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("field1").setIndex("include", false, 0);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("field1").setIndex("include", false, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("field1").setIndex("include", false, 7);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("field1").setIndex("include", false, 8);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().move("field1", 0);

    model.title("\u58f0\u5b66\u5904\u7406\u8fb9\u754c\u8ba1\u7b97\u5668");

    model
         .description("\u672c App \u7528\u4e8e\u8ba1\u7b97\u5438\u58f0\u5668\u5728\u6cd5\u5411\u548c\u968f\u673a\u5165\u5c04\u60c5\u51b5\u4e0b\u7684\u5438\u58f0\u7cfb\u6570\u548c\u8868\u9762\u963b\u6297\u3002\n\n\u5438\u58f0\u5668\u7684\u914d\u7f6e\u975e\u5e38\u7075\u6d3b\u3002\u60a8\u53ef\u4ee5\u9009\u62e9\u591a\u5b54\u6750\u6599\u6216\u56fa\u4f53\u6750\u6599\u4f5c\u4e3a\u7814\u7a76\u6837\u672c\uff0c\u5e76\u914d\u5907\u7a7a\u6c14\u8154\u3001\u591a\u5b54\u8154\u6216\u521a\u6027\u6761\u4ef6\u4f5c\u4e3a\u80cc\u886c\u3002\u6b64\u5916\uff0c\u8fd8\u53ef\u4ee5\u6839\u636e\u9700\u8981\u8c03\u6574\u4e0d\u540c\u5143\u4ef6\u7684\u539a\u5ea6\u548c\u6df1\u5ea6\u3002\n\n\u8be5 App \u8fd8\u5177\u5907\u5c06\u9891\u57df\u7ed3\u679c\u8f6c\u6362\u5230\u65f6\u57df\u7684\u201c\u90e8\u5206\u5206\u5f0f\u62df\u5408\u201d\u529f\u80fd\u3002\u901a\u8fc7\u6b64\u529f\u80fd\uff0c\u60a8\u53ef\u4ee5\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u8868\u9762\u963b\u6297\u7528\u4f5c\u65f6\u57df\u4eff\u771f\u7684\u8fb9\u754c\u6761\u4ef6\u3002\u540c\u65f6\uff0c\u8fd8\u53ef\u4ee5\u4fdd\u5b58\u901a\u8fc7\u62df\u5408\u5f97\u5230\u7684\u6b8b\u5dee\u548c\u6781\u70b9\uff0c\u5e76\u5c06\u5176\u5bfc\u5165\u5176\u4ed6\u6a21\u578b\uff0c\u7528\u4e8e\u63cf\u8ff0\u8fb9\u754c\u963b\u6297\u3002");

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

    model.result("pg1").feature("tbls1").set("tablechanged", false);
    model.result("pg1").feature("tbls1").set("showparam", false);

    model.label("acoustic_treatment_boundary_calculator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
