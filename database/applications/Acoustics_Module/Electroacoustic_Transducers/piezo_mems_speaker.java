/*
 * piezo_mems_speaker.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class piezo_mems_speaker {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pze1", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/es", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/ta", true);
    model.study("std1").feature("frlin").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fmax", "20[kHz]", "\u6700\u5927\u9891\u7387");
    model.param().set("c0", "343 [m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("lambda_min", "c0/fmax", "\u6700\u5c0f\u6ce2\u957f");
    model.param().set("dvisc", "0.22[mm]*sqrt(100[Hz]/fmax)", "\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("V_DC", "1[V]", "\u504f\u538b");
    model.param().set("V_AC", "0.2[V]", "\u4ea4\u6d41\u7535\u538b");
    model.param().set("th_pt", "400[nm]", "Pt \u5c42\u539a\u5ea6");
    model.param().set("th_au", "250[nm]", "Au \u5c42\u539a\u5ea6");
    model.param().set("rho_pt", "21450[kg/m^3]", "Pt \u7684\u5bc6\u5ea6");
    model.param().set("rho_au", "19300[kg/m^3]", "Au \u7684\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").insertFile("piezo_mems_speaker_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").cpl().create("linext1", "LinearExtrusion");
    model.component("comp1").cpl("linext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("linext1").selection().set(36, 91, 141);
    model.component("comp1").cpl("linext1").selection("srcvertex1").set(11);
    model.component("comp1").cpl("linext1").selection("srcvertex2").set(65);
    model.component("comp1").cpl("linext1").selection("dstvertex1").set(11);
    model.component("comp1").cpl("linext1").selection("dstvertex2").set(65);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("geom1_difsel3");
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "acpr");

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
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat2").label("Lead Zirconate Titanate (PZT-4)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.1);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.23e-011[1/Pa]", "-4.05e-012[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.05e-012[1/Pa]", "1.23e-011[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.31e-012[1/Pa]", "-5.31e-012[1/Pa]", "1.55e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.27e-011[1/Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "2.89e-010[C/N]", "0[C/N]", 
         "4.96e-010[C/N]", "0[C/N]", "4.96e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"1475", "0", "0", "0", "1475", "0", "0", "0", "1300"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.38999e+011[Pa]", "7.78366e+010[Pa]", "7.42836e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "7.78366e+010[Pa]", "1.38999e+011[Pa]", "7.42836e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "7.42836e+010[Pa]", "7.42836e+010[Pa]", "1.15412e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "3.0581e+010[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "15.0804[C/m^2]", "0[C/m^2]", 
         "12.7179[C/m^2]", "0[C/m^2]", "12.7179[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").label("Silicon");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.7);
    model.component("comp1").material("mat3").set("roughness", 0.5);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material("mat3").selection().named("geom1_difsel1");
    model.component("comp1").material("mat2").selection().named("geom1_difsel2");
    model.component("comp1").material("mat1").selection().named("geom1_unisel4");

    model.component("comp1").physics("solid").selection().named("geom1_unisel2");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("geom1_difsel2");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(45, 46, 111, 113, 142, 143, 144, 146);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("geom1_difsel5");
    model.component("comp1").physics("solid").create("adm1", "AddedMass2", 2);
    model.component("comp1").physics("solid").feature("adm1").label("\u9644\u52a0\u8d28\u91cf - Pt \u7535\u6781");
    model.component("comp1").physics("solid").feature("adm1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("adm1")
         .set("mPerArea", new String[]{"rho_pt*th_pt", "0", "0", "0", "rho_pt*th_pt", "0", "0", "0", "rho_pt*th_pt"});
    model.component("comp1").physics("solid").create("adm2", "AddedMass2", 2);
    model.component("comp1").physics("solid").feature("adm2").label("\u9644\u52a0\u8d28\u91cf - Au \u7535\u6781");
    model.component("comp1").physics("solid").feature("adm2").selection().named("geom1_sel3");
    model.component("comp1").physics("solid").feature("adm2")
         .set("mPerArea", new String[]{"rho_au*th_au", "0", "0", "0", "rho_au*th_au", "0", "0", "0", "rho_au*th_au"});
    model.component("comp1").physics("es").selection().named("geom1_unisel3");
    model.component("comp1").physics("es").feature("ccnp1").selection().named("geom1_difsel2");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 3);
    model.component("comp1").physics("es").feature("ccns1").selection().named("geom1_difsel1");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("geom1_sel2");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_sel3");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "V_DC+linper(V_AC)");
    model.component("comp1").physics("acpr").selection().named("geom1_difsel4");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("geom1_difsel5");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("geom1_sel1");
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition0", 1, 0);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition1", 1, 0);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 2);
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("tvb1").selection().set(3, 44, 47, 141);
    model.component("comp1").physics("ta").selection().named("geom1_unisel1");
    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("geom1_difsel5");

    model.component("comp1").multiphysics().create("tsb1", "ThermoacousticStructureBoundary", 2);
    model.component("comp1").multiphysics("tsb1").selection().all();
    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 2);
    model.component("comp1").multiphysics("atb1").selection().all();
    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics("asb1").feature()
         .create("tvb1", "CouplingThermoviscousBoundaryLayerImpedance", "geom1", 2);
    model.component("comp1").multiphysics("asb1").feature("tvb1").selection().named("geom1_boxsel3");
    model.component("comp1").multiphysics("asb1").feature("tvb1").set("FluidMaterial", "mat1");

    model.component("comp1").mesh("mesh1").create("map1", "Map");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").feature("map1").selection().set(36, 73, 81, 90, 121);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "dvisc*4");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(35);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(102);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(81);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(72, 141);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_min/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "dvisc/2");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(16, 35, 43, 63, 97, 108, 130, 140);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "dvisc*15");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(3, 4, 8, 9, 10, 11, 12, 13, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 37);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection()
         .set(4, 10, 11, 13, 17, 20, 23, 25, 27, 30, 33, 35, 37);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2, 5, 6, 29, 31);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run("swe2");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").feature("frlin")
         .set("plist", "10 5000 6000 7000 8000 9000 9200 9400 9475 9550 9600 9650 9725 9800 10000 10200 11000 12000 20000");
    model.study("std1").feature("frlin").set("geometricNonlinearity", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("i1").active(true);
    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("frametype", "spatial");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("differential", true);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("differential", true);
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("evalmethodactive", "off");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("evalmethodactive", "off");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 19, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b (acpr)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 19, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 19, 0);
    model.result("pg6").create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg6").feature("iso1").set("number", "10");
    model.result("pg6").feature("iso1").set("colortable", "Wave");
    model.result("pg6").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u58f0\u538b (ta)");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("expr", "ta.p_t");
    model.result("pg7").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg7").feature("mslc1").set("colortable", "Wave");
    model.result("pg7").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u58f0\u901f (ta)");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").feature().create("slc1", "Slice");
    model.result("pg8").feature("slc1").label("\u5207\u9762");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg8").feature("slc1").set("evalmethodactive", "off");
    model.result("pg8").feature("slc1").set("smooth", "internal");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("evalmethodactive", "off");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("evalmethodactive", "off");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("evalmethodactive", "off");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("evalmethodactive", "off");
    model.result("pg8").feature("slc1").set("data", "parent");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6e29\u5ea6\u53d8\u5316 (ta)");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").feature().create("mslc1", "Multislice");
    model.result("pg9").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("expr", "ta.T_t");
    model.result("pg9").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg9").feature("mslc1").set("colortable", "ThermalWave");
    model.result("pg9").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").feature("mslc1").set("smooth", "internal");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("evalmethodactive", "off");
    model.result("pg9").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg8").run();
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().named("geom1_unisel3");
    model.result("pg2").set("applyselectiontodatasetedges", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "5");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg2").feature("strmsl1").set("xnumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg2").feature("strmsl1").set("ynumber", "5");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg2").feature("strmsl1").set("znumber", "0");
    model.result("pg2").run();
    model.result("pg4").set("applyselectiontodatasetedges", false);
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b (ta+acpr)");
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection()
         .set(2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37);
    model.result("pg4").selection().named("geom1_comsel1");
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").run();
    model.result("pg4").feature().remove("surf1");
    model.result("pg4").run();
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "atb1.p_t");
    model.result("pg4").feature("vol1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg4").feature("vol1").set("descractive", true);
    model.result("pg4").feature("vol1").set("descr", "\u58f0\u538b");
    model.result("pg4").feature("vol1").set("colortable", "Wave");
    model.result("pg4").feature("vol1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("vol1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("filt1").set("expr", "(x<y)");
    model.result("pg4").run();
    model.result("pg6").set("applyselectiontodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (ta+acpr)");
    model.result("pg6").selection().geom("geom1", 3);
    model.result("pg6").selection().named("geom1_comsel1");
    model.result("pg6").set("applyselectiontodatasetedges", true);
    model.result("pg6").run();
    model.result("pg6").feature("iso1").set("expr", "atb1.p_t");
    model.result("pg6").feature("iso1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg6").feature("iso1").set("descractive", true);
    model.result("pg6").feature("iso1").set("descr", "\u58f0\u538b");
    model.result("pg6").run();
    model.result("pg8").set("applyselectiontodatasetedges", false);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").selection().geom("geom1", 3);
    model.result("pg8").selection().named("geom1_unisel1");
    model.result("pg8").set("applyselectiontodatasetedges", true);
    model.result("pg8").set("edges", false);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("planetype", "general");
    model.result("pg8").feature("slc1").set("genmethod", "pointnormal");
    model.result("pg8").feature("slc1").set("genpnpoint", new int[]{20, 20, 0});
    model.result("pg8").feature("slc1").set("genpnvec", new int[]{1, 1, 0});
    model.result("pg8").feature("slc1").set("genparaactive", true);
    model.result("pg8").feature("slc1").set("gennumber", 10);
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("gennumber", 0);
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("gennumber", 10);
    model.result("pg8").run();
    model.result("pg9").set("applyselectiontodatasetedges", false);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 11, 0);
    model.result("pg9").selection().geom("geom1", 3);
    model.result("pg9").selection().geom("geom1", 3);
    model.result("pg9").selection().set(2, 3, 4, 5, 9, 11, 19, 21, 22, 23, 29, 31, 32, 33, 36, 37);
    model.result("pg9").selection().named("geom1_unisel1");
    model.result("pg9").set("applyselectiontodatasetedges", true);
    model.result("pg9").set("edges", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature().remove("mslc1");
    model.result("pg9").run();
    model.result("pg9").create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("expr", "ta.T_t");
    model.result("pg9").feature("slc1").set("planetype", "general");
    model.result("pg9").feature("slc1").set("genmethod", "pointnormal");
    model.result("pg9").feature("slc1").set("genpnpoint", new int[]{20, 20, 0});
    model.result("pg9").feature("slc1").set("genpnvec", new int[]{1, 1, 0});
    model.result("pg9").feature("slc1").set("genparaactive", true);
    model.result("pg9").feature("slc1").set("gennumber", 10);
    model.result("pg9").feature("slc1").set("colortable", "ThermalWave");
    model.result("pg9").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").set("gennumber", 0);
    model.result("pg9").run();
    model.result("pg9").feature("slc1").set("gennumber", 10);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u5bf9\u6570\u70ed\u9ecf\u6027\u635f\u8017 (ta)");
    model.result("pg10").run();
    model.result("pg10").feature("slc1").set("expr", "log10(ta.diss_tot)");
    model.result("pg10").feature("slc1").set("descractive", true);
    model.result("pg10").feature("slc1").set("descr", "log10(\u70ed\u9ecf\u6027\u635f\u8017)");
    model.result("pg10").feature("slc1").set("rangecoloractive", true);
    model.result("pg10").feature("slc1").set("rangecolormin", 6.1);
    model.result("pg10").feature("slc1").set("rangecolormax", 8.1);
    model.result("pg10").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("slc1").set("colorscalemode", "linear");
    model.result("pg10").feature("slc1").set("gennumber", 0);
    model.result("pg10").run();
    model.result("pg10").feature("slc1").set("gennumber", 10);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7ec8\u7aef\u963b\u6297");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("twoyaxes", true);
    model.result("pg11").set("axislimits", true);
    model.result("pg11").set("xmin", 18);
    model.result("pg11").set("xmax", 20000);
    model.result("pg11").set("ymin", 0.1);
    model.result("pg11").set("ymax", 150);
    model.result("pg11").set("yminsec", -91);
    model.result("pg11").set("ymaxsec", -20);
    model.result("pg11").set("xlog", true);
    model.result("pg11").set("ylog", true);
    model.result("pg11").set("legendpos", "lowerleft");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "abs(1/es.Y11)/4", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "k\u03a9", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "\u5927\u5c0f", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("glob2", "Global");
    model.result("pg11").feature("glob2").set("markerpos", "datapoints");
    model.result("pg11").feature("glob2").set("linewidth", "preference");
    model.result("pg11").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg11").feature("glob2").setIndex("expr", "arg((1/es.Y11))", 0);
    model.result("pg11").feature("glob2").setIndex("unit", "deg", 0);
    model.result("pg11").feature("glob2").setIndex("descr", "\u76f8\u4f4d", 0);
    model.result("pg11").run();
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().named("geom1_unisel3");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "dset3");
    model.result().dataset("sec1").set("sectors", 4);
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("applyselectiontodatasetedges", false);
    model.result("pg12").run();
    model.result("pg12").label("\u6a21\u578b\u7f29\u7565\u56fe");
    model.result("pg12").set("data", "sec1");
    model.result("pg12").setIndex("looplevel", 11, 0);
    model.result("pg12").set("edges", false);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").run();

    model.view("view5").set("showgrid", false);

    model.result("pg12").create("vol1", "Volume");
    model.result("pg12").feature("vol1").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg12").feature("vol1").feature("def1").set("scale", 100);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg12").feature("slc1").set("planetype", "general");
    model.result("pg12").feature("slc1").set("genmethod", "pointnormal");
    model.result("pg12").feature("slc1").set("genpnvec", new int[]{1, 1, 0});
    model.result("pg12").feature("slc1").set("genparaactive", true);
    model.result("pg12").feature("slc1").set("gennumber", 20);
    model.result("pg12").feature("slc1").set("colorlegend", false);
    model.result("pg12").feature("slc1").set("colortable", "Traffic");
    model.result("pg12").feature("slc1").set("inheritplot", "vol1");
    model.result("pg12").feature("slc1").set("inheritcolor", false);
    model.result("pg12").feature("slc1").set("inheritrange", false);
    model.result("pg12").run();
    model.result("pg12").feature("slc1").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").feature("slc1").feature("def1").set("expr", new String[]{"0", "0", "linext1(w)"});
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("slc2", "slc1");
    model.result("pg12").run();
    model.result("pg12").feature("slc2").set("genpnvec", new int[]{-1, 1, 0});
    model.result("pg12").feature("slc2").set("titletype", "none");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("line1", "Line");
    model.result("pg12").feature("line1").set("expr", "0");
    model.result("pg12").feature("line1").set("titletype", "none");
    model.result("pg12").feature("line1").set("coloring", "uniform");
    model.result("pg12").feature("line1").set("color", "black");
    model.result("pg12").feature("line1").set("inheritplot", "vol1");
    model.result("pg12").feature("line1").set("inheritcolor", false);
    model.result("pg12").feature("line1").set("inheritrange", false);
    model.result("pg12").feature("line1").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup3D");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg13").run();
    model.result("pg13").label("\u9759\u6001\u53d8\u5f62\uff08\u9884\u5e94\u529b\uff09");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("showlegendsunit", true);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "abs(w)");
    model.result("pg13").feature("surf1").create("def1", "Deform");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("10 cm \u5904\u7684\u8f74\u4e0a\u54cd\u5e94");
    model.result("pg14").set("titletype", "label");
    model.result("pg14").create("oct1", "OctaveBand");
    model.result("pg14").feature("oct1").set("quantity", "bandpower");
    model.result("pg14").feature("oct1").set("markerpos", "datapoints");
    model.result("pg14").feature("oct1").set("linewidth", "preference");
    model.result("pg14").feature("oct1").selection().geom("geom1");
    model.result("pg14").feature("oct1").set("expr", "pext(0,0,10[cm])");
    model.result("pg14").feature("oct1").set("quantity", "continuous");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").set("data", "dset1");
    model.result("pg15").setIndex("looplevel", 19, 0);
    model.result("pg15").label("\u4f4d\u79fb (solid)");
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").set("showlegends", true);
    model.result("pg15").create("vol1", "Volume");
    model.result("pg15").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg15").feature("vol1").set("threshold", "manual");
    model.result("pg15").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg15").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg15").feature("vol1").set("colortabletrans", "none");
    model.result("pg15").feature("vol1").set("colorscalemode", "linear");
    model.result("pg15").feature("vol1").set("resolution", "custom");
    model.result("pg15").feature("vol1").set("refine", 2);
    model.result("pg15").feature("vol1").create("def", "Deform");
    model.result("pg15").feature("vol1").feature("def").set("differential", true);
    model.result("pg15").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg15").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg15").label("\u4f4d\u79fb (solid)");
    model.result("pg15").run();
    model.result("pg15").setIndex("looplevel", 11, 0);
    model.result("pg15").set("edges", false);
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").run();
    model.result("pg12").run();

    model.title("\u538b\u7535 MEMS \u626c\u58f0\u5668");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u538b\u7535\u5fae\u673a\u7535\u7cfb\u7edf (MEMS) \u626c\u58f0\u5668\uff0c\u8be5\u626c\u58f0\u5668\u7531\u56db\u4e2a\u4e09\u89d2\u5f62\u819c\u7ec4\u6210\uff0c\u5176\u4e2d\u4f7f\u7528\u4e00\u5c42\u9506\u949b\u9178\u94c5 (PZT) \u6750\u6599\uff0c\u5e76\u4e14\u7845\u5c42\u9876\u90e8\u6709\u4e24\u4e2a\u7535\u6781\u4f5c\u4e3a\u6267\u884c\u5668\u3002\u4e09\u89d2\u5f62\u819c\u7531\u72ed\u7a84\u7684\u6c14\u9699\u9694\u5f00\uff0c\u4ece\u800c\u4f7f\u819c\u5177\u6709\u8f83\u5927\u7684\u6320\u5ea6\u3002\u95f4\u9699\u4e2d\u7684\u70ed\u9ecf\u6027\u635f\u5931\u4f1a\u9650\u5236\u6c14\u6d41\uff0c\u4f7f\u5f97\u56db\u4e2a\u6267\u884c\u5668\u5728\u58f0\u5b66\u4e0a\u8868\u73b0\u4e3a\u5355\u4e2a\u819c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("piezo_mems_speaker.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
