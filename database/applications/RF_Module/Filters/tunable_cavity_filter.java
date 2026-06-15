/*
 * tunable_cavity_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:37 by COMSOL 6.3.0.290. */
public class tunable_cavity_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

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
    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "range(3[GHz],2.5[MHz],3.06[GHz])");

    model.component("comp1").geom("geom1").insertFile("tunable_cavity_filter_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.param().set("V0", "300[V]");
    model.param().descr("V0", "\u538b\u7535\u9a71\u52a8\u5668\u504f\u538b");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9988\u7ebf");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(17, 22, 47, 50);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u63a5\u5730");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(6, 16, 28, 39, 53);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u57fa\u677f");
    model.component("comp1").selection("sel3").set(2, 4, 6, 8);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u538b\u7535\u9a71\u52a8\u5668");
    model.component("comp1").selection("sel4").set(5);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u538b\u7535\u56fa\u5b9a\u8fb9");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(50, 51, 63, 66);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5f00\u653e\u8fb9\u754c");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6")
         .set(4, 5, 7, 8, 10, 12, 13, 23, 25, 37, 38, 40, 41, 43, 44, 45, 56, 57);

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(1);
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacement");
    model.component("comp1").common("disp1").selection().set(28);
    model.component("comp1").common("disp1").set("prescribedMeshDisplacement", new String[]{"u", "v", "w"});

    model.component("comp1").physics("solid").selection().named("sel4");
    model.component("comp1").physics("es").selection().named("sel4");
    model.component("comp1").physics("emw").selection().set(1, 2, 3, 4, 6, 7, 8);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat1").selection().named("sel4");
    model.component("comp1").material("mat2").selection().set(1, 3, 7);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"3.38"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel5");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(29);
    model.component("comp1").physics("es").feature("pot1").set("V0", "-V0");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("sel2");
    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().named("sel1");
    model.component("comp1").physics("emw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec3").selection().named("sel2");
    model.component("comp1").physics("emw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("emw").feature("sctr1").selection().named("sel6");
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(14);
    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(54);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 10);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2, 3, 4, 6, 7, 8);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "thickness", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "thickness", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "V0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "-300,300", 0);
    model.study("std1").feature("freq").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std1").feature("freq").setSolveFor("/physics/es", false);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/pze1", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 25, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 25, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
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
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 25, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
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
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
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
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (emw)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 25, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg4").feature("mslc1").set("expr", "emw.normE");
    model.result("pg4").feature("mslc1").set("colortable", "RainbowLight");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg4").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg5").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg5").label("S \u53c2\u6570 (emw)");
    model.result("pg5").feature("glob1").set("titletype", "none");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg5").feature("glob1").set("xdataexpr", "freq");
    model.result("pg5").feature("glob1").set("xdataunit", "GHz");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg6", "SmithGroup");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("rgr1", "ReflectionGraph");
    model.result("pg6").feature("rgr1").set("unit", new String[]{""});
    model.result("pg6").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg6").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg6").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg6").feature("rgr1").set("titletype", "manual");
    model.result("pg6").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg6").feature("rgr1").set("linemarker", "point");
    model.result("pg6").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("rgr1").create("col1", "Color");
    model.result("pg6").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg6").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 11, 24, 28, 30, 31, 32, 33, 36, 55);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 28, 30, 31, 32, 33, 36, 37, 38, 40, 41, 43, 44, 45, 55, 56, 57);

    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "emw.normE");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 11, 24, 28, 30, 31, 32, 33, 36, 55);
    model.result("pg7").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").feature("surf1").feature("tran1").set("transparency", 0.79);
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg7").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(6, 16, 17, 22, 39, 47, 50, 53);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 23, 24, 25, 28, 30, 31, 32, 33, 36, 37, 38, 40, 41, 43, 44, 45, 55, 56, 57);

    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "emw.normE");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().set(6, 16, 17, 22, 39, 47, 50, 53);
    model.result("pg7").feature("surf2").set("colortable", "Dipole");
    model.result("pg7").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf2").feature("mtrl1").set("family", "chrome");
    model.result("pg7").feature("surf2").set("expr", "1");
    model.result("pg7").create("surf3", "Surface");
    model.result("pg7").feature("surf3").set("expr", "emw.normE");
    model.result("pg7").feature("surf3").create("sel1", "Selection");
    model.result("pg7").feature("surf3").feature("sel1").selection()
         .set(9, 14, 15, 18, 19, 20, 21, 42, 46, 48, 49, 51, 52, 54);
    model.result("pg7").feature("surf3").set("colortable", "Dipole");
    model.result("pg7").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf3").create("tran1", "Transparency");
    model.result("pg7").feature("surf3").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-149.01368514351225, -198.6849179475204, 151.1875981869905});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 43.09714317321777);

    model.result("pg7").set("view", "view3");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 25, 0);
    model.result("pg8").setIndex("looplevel", 2, 1);
    model.result("pg8").label("\u52a8\u7f51\u683c");
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("meshdomain", "volume");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg8").feature("mesh1").feature("sel1").selection().set(1, 5);
    model.result("pg8").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg8").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg8").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.disp");
    model.result("pg1").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("rangecoloractive", true);
    model.result("pg4").feature("mslc1").set("rangecolormax", 1000);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowermiddle");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg5").run();
    model.result("pg6").run();

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").label("\u96c6\u603b\u7aef\u53e3 1");
    model.component("comp1").selection("sel7").set(14);

    model.component("comp1").physics("emw").feature("lport1").selection().named("sel7");

    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").label("\u96c6\u603b\u7aef\u53e3 2");
    model.component("comp1").selection("sel8").set(54);

    model.component("comp1").physics("emw").feature("lport2").selection().named("sel8");

    model.study().create("std2");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "thickness", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "thickness", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "V0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "-300 300", 0);
    model.study("std2").create("stat", "Stationary");
    model.study("std2").create("frawe", "FrequencyAdaptive");
    model.study("std2").feature("frawe").set("plist", "range(3[GHz],0.25[MHz],3.06[GHz])");
    model.study("std2").feature("frawe").set("rtol", 0.001);
    model.study("std2").feature("frawe").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("frawe").setSolveFor("/physics/solid", false);
    model.study("std2").feature("frawe").setSolveFor("/physics/es", false);
    model.study("std2").feature("frawe").setSolveFor("/multiphysics/pze1", false);
    model.study("std2").feature("frawe").setEntry("outputmap", "solid", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "solid", "sel7;sel8");
    model.study("std2").feature("frawe").setEntry("outputmap", "es", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "es", "sel7;sel8");
    model.study("std2").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "emw", "sel7;sel8");
    model.study("std2").feature("frawe").setEntry("outputmap", "frame:spatial1", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "frame:spatial1", "sel7;sel8");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.result("pg5").run();
    model.result().duplicate("pg9", "pg5");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg9").feature("glob1").set("linemarker", "cycle");
    model.result("pg9").feature().duplicate("glob2", "glob1");
    model.result("pg9").run();
    model.result("pg9").feature("glob2").set("data", "dset6");
    model.result("pg9").feature("glob2").setIndex("descr", "S11 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg9").feature("glob2").setIndex("descr", "S21 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg9").feature("glob2").set("linemarker", "none");
    model.result("pg9").run();
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("udist", 0.005);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("strmsl1").set("udist", 0.005);
    model.result("pg3").run();

    model
         .title("\u4f7f\u7528\u538b\u7535\u9a71\u52a8\u5668\u7684\u53ef\u8c03\u6d88\u5931\u6a21\u8154\u4f53\u6ee4\u6ce2\u5668");

    model
         .description("\u6d88\u5931\u6a21\u8154\u4f53\u6ee4\u6ce2\u5668\u53ef\u4ee5\u901a\u8fc7\u5728\u8154\u4f53\u5185\u6dfb\u52a0\u7ed3\u6784\u6765\u5b9e\u73b0\u3002\u8fd9\u79cd\u7ed3\u6784\u4f1a\u6539\u53d8\u8c10\u632f\u9891\u7387\uff0c\u5b83\u4f4e\u4e8e\u672a\u586b\u5145\u65f6\u8154\u4f53\u4e3b\u5bfc\u6a21\u5f0f\u7684\u8c10\u632f\u9891\u7387\u3002\u538b\u7535\u9a71\u52a8\u5668\u7528\u6765\u63a7\u5236\u7a7a\u6c14\u95f4\u9699\u7684\u5927\u5c0f\uff0c\u7531\u6b64\u8c03\u8282\u8c10\u632f\u9891\u7387\u3002\n\n\u9664\u4e86\u201cRF \u6a21\u5757\u201d\uff0c\u672c\u4f8b\u8fd8\u9700\u8981\u201c\u58f0\u5b66\u6a21\u5757\u201d\u3001\u201cMEMS \u6a21\u5757\u201d\u6216\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u3002");

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

    model.label("tunable_cavity_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
