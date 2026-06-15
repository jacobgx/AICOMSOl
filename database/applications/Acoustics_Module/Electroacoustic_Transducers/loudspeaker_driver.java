/*
 * loudspeaker_driver.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_driver {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/asb1", true);

    model.component("comp1").geom("geom1").insertFile("loudspeaker_driver_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("N0", "100");
    model.param().descr("N0", "\u97f3\u5708\u531d\u6570");
    model.param().set("V0", "3.55[V]");
    model.param().descr("V0", "\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("f_loss", "40[Hz]");
    model.param().descr("f_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u65f6\u7684\u9891\u7387");
    model.param().set("omega_loss", "2*pi*f_loss");
    model.param().descr("omega_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u65f6\u7684\u89d2\u9891\u7387");
    model.param().set("fmax", "8[kHz]");
    model.param().descr("fmax", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("c0", "343[m/s]");
    model.param().descr("c0", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("lam0", "c0/fmax");
    model.param().descr("lam0", "\u6700\u5c0f\u6ce2\u957f");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8f6f\u94c1");
    model.component("comp1").selection("sel1").set(6, 23);
    model.component("comp1").selection("sel1").set("color", "3");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u590d\u5408\u6750\u6599");
    model.component("comp1").selection("sel2").set(3, 21);
    model.component("comp1").selection("sel2").set("color", "9");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5e03");
    model.component("comp1").selection("sel3").set(20);
    model.component("comp1").selection("sel3").set("color", "7");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6ce1\u6cab");
    model.component("comp1").selection("sel4").set(25);
    model.component("comp1").selection("sel4").set("color", "10");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u97f3\u5708");
    model.component("comp1").selection("sel5").set(17, 18, 19);
    model.component("comp1").selection("sel5").set("color", "8");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u73bb\u7483\u7ea4\u7ef4");
    model.component("comp1").selection("sel6").set(9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").selection("sel6").set("color", "10");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u666e\u901a\u94c1\u6c27\u4f53");
    model.component("comp1").selection("sel7").set(24);
    model.component("comp1").selection("sel7").set("color", "17");
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("PML");
    model.component("comp1").selection("sel8").set(1, 5);
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7a7a\u6c14");
    model.component("comp1").selection("dif1").set("add", new String[]{"box1"});
    model.component("comp1").selection("dif1")
         .set("subtract", new String[]{"sel1", "sel2", "sel3", "sel4", "sel5", "sel6", "sel7"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7ed3\u6784\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3", "sel4", "sel5", "sel6"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u590d\u5408\u6750\u6599\u548c\u73bb\u7483\u7ea4\u7ef4");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "sel6"});
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u78c1\u4f53\u57df");
    model.component("comp1").selection("box2").set("xmin", "0[mm]");
    model.component("comp1").selection("box2").set("xmax", "50[mm]");
    model.component("comp1").selection("box2").set("ymin", "-90[mm]");
    model.component("comp1").selection("box2").set("ymax", "-42[mm]");
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("PML \u4e4b\u5916\u7684\u6240\u6709\u57df");
    model.component("comp1").selection("dif2").set("add", new String[]{"box1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"sel8"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel8");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(93);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("coil_power", "mf.PCoil_1");
    model.component("comp1").variable("var1").descr("coil_power", "\u97f3\u5708\u529f\u7387");
    model.component("comp1").variable("var1").set("aco_eff", "-intop1(up(acpr.Ir)*nr+up(acpr.Iz)*nz)/coil_power");
    model.component("comp1").variable("var1").descr("aco_eff", "\u58f0\u5b66\u6548\u7387");

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
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat2").label("Soft Iron (With Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Composite");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Cloth");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Foam");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.4");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "67[kg/m^3]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("Coil");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("Glass Fiber");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat7").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat7").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat8").label("Generic Ferrite");
    model.component("comp1").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat8").propertyGroup("def").set("lossfactor", "0.01");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat8").propertyGroup("def").set("youngsmodulus", "200[GPa]");
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "5000[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity").set("normBr", "0.4[T]");
    model.component("comp1").material("mat3").selection().named("sel2");
    model.component("comp1").material("mat4").selection().named("sel3");
    model.component("comp1").material("mat5").selection().named("sel4");
    model.component("comp1").material("mat6").selection().named("sel5");
    model.component("comp1").material("mat7").selection().named("sel6");
    model.component("comp1").material("mat8").selection().named("sel7");

    model.component("comp1").physics("mf").selection().named("box2");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").label("\u666e\u901a\u94c1\u6c27\u4f53");
    model.component("comp1").physics("mf").feature("als1").selection().named("sel7");
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als1").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").label("\u8f6f\u94c1");
    model.component("comp1").physics("mf").feature("als2").selection().named("sel1");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").create("als3", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als3").selection().named("uni1");
    model.component("comp1").physics("mf").feature("als3")
         .label("\u56fa\u4f53\u4e2d\u7684\u5b89\u57f9\u5b9a\u5f8b - \u975e\u5bfc\u7535\u56fa\u4f53");
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel5");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N0");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "3.5e-8[m^2]");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "linper(V0)");
    model.component("comp1").physics("acpr").selection().named("dif1");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(93);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 2);
    model.component("comp1").physics("acpr").feature("nra1").selection().set(8);
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra1").set("h", "0.4[mm]");
    model.component("comp1").physics("acpr").feature().duplicate("nra2", "nra1");
    model.component("comp1").physics("acpr").feature("nra2").selection().set(22);
    model.component("comp1").physics("acpr").feature("nra2").set("h", "0.2[mm]");
    model.component("comp1").physics("solid").selection().named("uni1");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").selection().set();
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").selection().named("uni2");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp2", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").selection().set();
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").selection().named("sel3");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").set("beta_dK", "0.14/omega_loss");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp3", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").selection().set();
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").selection().named("sel4");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").set("beta_dK", "0.46/omega_loss");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(81, 85);

    model.component("comp1").multiphysics().create("mmcpl1", "Magnetomechanics", 2);
    model.component("comp1").multiphysics("mmcpl1").set("OnlyLorentz", true);
    model.component("comp1").multiphysics("mmcpl1").selection().named("sel5");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(1, 3, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 25);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.5[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.15);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(9, 13, 14, 15, 16, 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().set(3, 21, 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmax", "4[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").selection()
         .set(8, 10, 11, 12, 17, 18, 19, 22);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("hmax", "0.5[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(22, 38, 41, 45);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(87, 88);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(4, 6, 23);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(12, 53, 95, 96, 97, 98);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp1", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().set(93);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u78c1\u573a");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin")
         .set("plist", "1 2 3 4 5 6 7 8 9 {10, 10.6, 11.2, 11.8, 12.5, 13.2, 14, 15, 16, 17, 18, 19, 20, 21.2, 22.4, 23.6, 25, 26.5, 28, 30, 31.5, 33.5, 35.5, 37.5, 40, 42.5, 45, 47.5, 50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3, 1.06e3, 1.12e3, 1.18e3, 1.25e3, 1.32e3, 1.4e3, 1.5e3, 1.6e3, 1.7e3, 1.8e3, 1.9e3, 2e3, 2.12e3, 2.24e3, 2.36e3, 2.5e3, 2.65e3, 2.8e3, 3e3, 3.15e3, 3.35e3, 3.55e3, 3.75e3, 4e3, 4.25e3, 4.5e3, 4.75e3, 5e3, 5.3e3, 5.6e3, 6e3, 6.3e3, 6.7e3, 7.1e3, 7.5e3, 8e3}");
    model.study("std1").feature("frlin").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("frlin").setSolveFor("/physics/solid", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u9759\u78c1\u573a");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "mf.normH");
    model.result("pg1").feature("surf1").set("descr", "\u78c1\u573a\u6a21");
    model.result("pg1").feature("surf1").set("colortable", "Thermal");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6709\u6548\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mf.normB/(mu0_const*mf.normH)");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u6709\u6548\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.result("pg2").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").set("data", "dset2");
    model.result().numerical("av1").selection().named("sel5");
    model.result().numerical("av1").setIndex("expr", "-mf.Br*N0*2*pi*r", 0);
    model.result().numerical("av1").setIndex("unit", "N/A", 0);
    model.result().numerical("av1").setIndex("descr", "BL", 0);
    model.result().numerical("av1").set("intvolume", false);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u611f\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mf.Jiphi");
    model.result("pg3").feature("surf1").set("descr", "\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\uff0cphi \u5206\u91cf");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5c4f\u853d\u97f3\u5708\u7535\u611f");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "mf.LCoil_1", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "mH", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u97f3\u5708\u7535\u611f", 0);
    model.result("pg4").set("xlog", true);
    model.result("pg4").run();
    model.result().export().create("plot1", "pg4", "glob1", "Plot");

    model.study().create("std2");
    model.study("std2").feature().copy("stat", "std1/stat");
    model.study("std2").feature().copy("frlin", "std1/frlin");
    model.study("std2").label("\u7814\u7a76 2 - \u5b8c\u6574\u6a21\u578b");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("frlin").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("dif2");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().dataset("rev1").set("defaultPlotIDs", new String[]{"pg7|acpr", "pg8|acpr"});
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").setIndex("looplevel", 126, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b, 3D (acpr)");
    model.result("pg5").label("\u58f0\u538b, 3D (acpr)");
    model.result("pg5").run();
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "acpr.p_t");
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("color", "gray");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 126, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result("pg6").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result("pg6").run();

    model.view("view2").set("showgrid", false);

    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7075\u654f\u5ea6");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "SPL (dB)");
    model.result("pg7").set("legendpos", "lowermiddle");
    model.result("pg7").create("oct1", "OctaveBand");
    model.result("pg7").feature("oct1").set("quantity", "bandpower");
    model.result("pg7").feature("oct1").set("markerpos", "datapoints");
    model.result("pg7").feature("oct1").set("linewidth", "preference");
    model.result("pg7").feature("oct1").selection().geom("geom1");
    model.result("pg7").feature("oct1").set("expr", "pext(0,1[m])");
    model.result("pg7").run();
    model.result("pg7").feature("oct1").set("quantity", "bandaveragepsd");
    model.result("pg7").feature("oct1").set("bandtype", "octave3");
    model.result("pg7").feature("oct1").set("type", "outline");
    model.result("pg7").feature().duplicate("oct2", "oct1");
    model.result("pg7").run();
    model.result("pg7").feature("oct2").set("quantity", "continuous");
    model.result("pg7").run();
    model.result("pg7").feature("oct2").set("linewidth", 2);
    model.result("pg7").feature("oct2").set("legend", true);
    model.result("pg7").feature("oct2").set("legendmethod", "manual");
    model.result("pg7").feature("oct2").setIndex("legends", "\u5b8c\u6574\u7814\u7a76", 0);
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 15);
    model.result("pg7").set("ymin", 64);
    model.result("pg7").set("ymax", 92);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u603b\u7535\u963b\u6297");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "Z (\\Omega)");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "abs(mf.ZCoil_1)", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "abs(Z)", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "real(mf.ZCoil_1)", 1);
    model.result("pg8").feature("glob1").setIndex("unit", "", 1);
    model.result("pg8").feature("glob1").setIndex("descr", "real(Z)", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "imag(mf.ZCoil_1)", 2);
    model.result("pg8").feature("glob1").setIndex("unit", "", 2);
    model.result("pg8").feature("glob1").setIndex("descr", "imag(Z)", 2);
    model.result("pg8").run();
    model.result("pg8").set("xlog", true);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u4f4d\u79fb");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "solid.disp");
    model.result("pg9").feature("surf1").create("def1", "Deform");
    model.result("pg9").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u65b9\u5411\u6027\u56fe");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").create("dir1", "Directivity");
    model.result("pg10").feature("dir1").set("linewidth", "preference");
    model.result("pg10").feature("dir1").set("anglerestr", "manual");
    model.result("pg10").feature("dir1").set("phidisc", 360);
    model.result("pg10").feature("dir1").set("phimin", -90);
    model.result("pg10").feature("dir1").set("phirange", 180);
    model.result("pg10").feature("dir1").set("radius", "1[m]");
    model.result("pg10").feature("dir1").set("levelmethod", "levels");
    model.result("pg10").feature("dir1").set("levels", "-15 -12 -9 -6 -3 -2 -1 1 2 3");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u97f3\u5708\u529f\u7387\u548c\u6548\u7387");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").set("twoyaxes", true);
    model.result("pg11").set("legendpos", "middleleft");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "coil_power", 0);
    model.result("pg11").run();
    model.result("pg11").create("glob2", "Global");
    model.result("pg11").feature("glob2").set("markerpos", "datapoints");
    model.result("pg11").feature("glob2").set("linewidth", "preference");
    model.result("pg11").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg11").feature("glob2").setIndex("expr", "aco_eff", 0);
    model.result("pg11").feature("glob2").setIndex("unit", "%", 0);
    model.result("pg11").set("xlog", true);
    model.result("pg11").run();

    model.study().create("std3");
    model.study("std3").feature().copy("stat", "std2/stat");
    model.study("std3").feature().copy("frlin", "std2/frlin");
    model.study("std3").feature("frlin").set("useadvanceddisable", true);
    model.study("std3").feature("frlin").set("disabledphysics", new String[]{"acpr/nra1", "acpr/nra2"});
    model.study("std3")
         .label("\u7814\u7a76 3 - \u5b8c\u6574\u6a21\u578b\uff0c\u4e0d\u542b\u72ed\u7a84\u533a\u57df\u58f0\u5b66");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg7").run();
    model.result("pg7").feature("oct2").set("data", "dset5");
    model.result("pg7").feature("oct2")
         .setIndex("legends", "\u5b8c\u6574\u7814\u7a76 - \u4e0d\u542b\u72ed\u7a84\u533a\u57df\u58f0\u5b66", 0);
    model.result("pg7").feature("oct2").set("linestyle", "dotted");
    model.result("pg7").feature().duplicate("oct3", "oct2");
    model.result("pg7").run();
    model.result("pg7").feature("oct3").set("data", "parent");
    model.result("pg7").feature("oct3").setIndex("legends", "\u5b8c\u6574\u7814\u7a76", 0);
    model.result("pg7").feature("oct3").set("linestyle", "solid");
    model.result("pg7").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset5");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").run();
    model.result("pg12").label("\u58f0\u538b - \u4e0d\u542b\u72ed\u7a84\u533a\u57df\u58f0\u5b66");
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").set("data", "mir1");
    model.result("pg12").setIndex("looplevel", 81, 0);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "acpr.p_t");
    model.result("pg12").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg12").feature("surf1").set("colortable", "Wave");
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("surf2", "surf1");
    model.result("pg12").run();
    model.result("pg12").feature("surf2").set("data", "dset5");
    model.result("pg12").feature("surf2").setIndex("looplevel", 82, 0);
    model.result("pg12").feature("surf2").set("titletype", "none");
    model.result("pg12").feature("surf2").set("inheritplot", "surf1");
    model.result("pg12").run();
    model.result("pg12").create("ann1", "Annotation");
    model.result("pg12").feature("ann1").set("text", "630 Hz \u65f6\u7684\u538b\u529b");
    model.result("pg12").feature("ann1").set("posxexpr", "10[mm]");
    model.result("pg12").feature("ann1").set("posyexpr", "20[mm]");
    model.result("pg12").feature("ann1").set("showpoint", false);
    model.result("pg12").feature().duplicate("ann2", "ann1");
    model.result("pg12").run();
    model.result("pg12").feature("ann2").set("posxexpr", "-140[mm]");
    model.result("pg12").feature("ann2").set("text", "600 Hz \u65f6\u7684\u538b\u529b");
    model.result("pg12").run();
    model.result("pg12").create("line1", "Line");
    model.result("pg12").feature("line1").set("expr", "0");
    model.result("pg12").feature("line1").set("data", "mir1");
    model.result("pg12").feature("line1").set("titletype", "none");
    model.result("pg12").feature("line1").set("coloring", "uniform");
    model.result("pg12").feature("line1").set("color", "black");
    model.result("pg12").run();

    model.study().create("std4");
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("chkeigregion", true);
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("linpsolnum", "auto");
    model.study("std4").feature("eig").set("solnum", "auto");
    model.study("std4").feature("eig").set("notsolnum", "auto");
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/mf", false);
    model.study("std4").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std4").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std4").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study("std4").feature("eig").setSolveFor("/multiphysics/mmcpl1", false);
    model.study("std4").label("\u7814\u7a76 4 - \u7279\u5f81\u9891\u7387");
    model.study("std4").feature("eig").set("neigsactive", true);
    model.study("std4").feature("eig").set("neigs", 10);
    model.study("std4").feature("eig").set("eigwhich", "lr");
    model.study("std4").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").set("data", "dset7");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").label("\u632f\u578b (solid)");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg13").feature("surf1").set("threshold", "manual");
    model.result("pg13").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg13").feature("surf1").set("colortable", "Rainbow");
    model.result("pg13").feature("surf1").set("colortabletrans", "none");
    model.result("pg13").feature("surf1").set("colorscalemode", "linear");
    model.result("pg13").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg13").feature("surf1").create("def", "Deform");
    model.result("pg13").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg13").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset7solidrev", "Revolve2D");
    model.result().dataset("dset7solidrev").set("data", "dset7");
    model.result().dataset("dset7solidrev").set("revangle", 225);
    model.result().dataset("dset7solidrev").set("startangle", -90);
    model.result().dataset("dset7solidrev").set("hasspacevars", true);
    model.result().dataset("dset7solidrev").set("modenumber", "solid.mk");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset7solidrev");
    model.result("pg14").setIndex("looplevel", 1, 0);
    model.result("pg14").label("\u632f\u578b, 3D (solid)");
    model.result("pg14").set("showlegends", false);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg14").feature("surf1").set("threshold", "manual");
    model.result("pg14").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg14").feature("surf1").set("colortable", "Rainbow");
    model.result("pg14").feature("surf1").set("colortabletrans", "none");
    model.result("pg14").feature("surf1").set("colorscalemode", "linear");
    model.result("pg14").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg14").feature("surf1").create("def", "Deform");
    model.result().dataset("dset7solidrev").set("hasspacevars", true);
    model.result("pg14").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg14").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg14").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg14").feature("surf1").feature("def").set("descractive", true);
    model.result().evaluationGroup().create("std4EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std4EvgFrq").set("data", "dset7");
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4 - \u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std4EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std4EvgFrq").run();
    model.result("pg13").run();
    model.result().dataset("dset7").selection().geom("geom1", 2);
    model.result().dataset("dset7").selection().named("uni1");
    model.result("pg14").run();

    model.view("view4").set("showgrid", false);

    model.result("pg14").stepNext(0);
    model.result("pg14").run();
    model.result("pg14").stepNext(0);
    model.result("pg14").run();
    model.result("pg14").stepNext(0);
    model.result("pg14").run();
    model.result("pg14").stepNext(0);
    model.result("pg14").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 38, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 88, 0);
    model.result("pg3").run();
    model.result("pg6").run();

    model.title("\u626c\u58f0\u5668\u9a71\u52a8\u5668 - \u9891\u57df\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u7535\u52a8\u5f0f\u9525\u76c6\u578b\u626c\u58f0\u5668\u9a71\u52a8\u5668\uff08\u901a\u5e38\u4e3a\u4e2d\u4f4e\u9891\uff09\u3002\u8be5\u5206\u6790\u5728\u9891\u57df\u4e2d\u8fdb\u884c\uff0c\u56e0\u6b64\u80fd\u591f\u8868\u5f81\u9a71\u52a8\u5668\u7684\u7ebf\u6027\u7279\u6027\u3002\u6a21\u578b\u5206\u6790\u5305\u62ec\u989d\u5b9a\u9a71\u52a8\u7535\u538b\u4e0b\u7684\u603b\u7535\u963b\u6297\u548c\u8f74\u4e0a\u58f0\u538b\u7ea7\u968f\u9891\u7387\u7684\u53d8\u5316\u60c5\u51b5\uff0c\u5e76\u901a\u8fc7\u65b9\u5411\u6027\u56fe\u63cf\u8ff0\u9a71\u52a8\u5668\u7684\u7a7a\u95f4\u7279\u6027\u3002\n\n\u672c\u6559\u5b66\u6a21\u578b\u7ed3\u5408\u4f7f\u7528\u201c\u78c1\u573a\u201d\u63a5\u53e3\u548c\u201c\u58f0-\u7ed3\u6784\u76f8\u4e92\u4f5c\u7528\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u8fdb\u884c\u8bbe\u7f6e\u3002\u7b2c\u4e00\u4e2a\u5206\u6790\u4ec5\u6c42\u89e3\u9a71\u52a8\u5668\u9759\u6b62\u65f6\u7684\u7535\u78c1\u95ee\u9898\u90e8\u5206\u3002\u60a8\u53ef\u4ee5\u4ece\u4e2d\u63d0\u53d6\u9a71\u52a8\u529b\u56e0\u5b50\u548c\u963b\u585e\u97f3\u5708\u963b\u6297\uff0c\u5e76\u5c06\u5176\u5bfc\u51fa\u4ee5\u4f9b\u540e\u7eed\u5206\u6790\u4f7f\u7528\u3002\u968f\u540e\u5bf9\u6574\u4e2a\u6a21\u578b\u8fdb\u884c\u5206\u6790\uff0c\u5305\u62ec\u76f8\u5173\u7684\u591a\u7269\u7406\u573a\u76f8\u4e92\u4f5c\u7528\uff0c\u4f8b\u5982\u58f0-\u7ed3\u6784\u76f8\u4e92\u4f5c\u7528\u4ee5\u53ca\u4f5c\u7528\u5728\u97f3\u5708\u4e0a\u7684\u7535\u78c1\u529b\u3002\u8be5\u7814\u7a76\u8fd8\u5305\u542b\u97f3\u5708\u4e0e\u6781\u7247\u4e4b\u95f4\u7684\u6c14\u9699\u4e2d\u7684\u70ed\u963b\u5c3c\u548c\u9ecf\u6ede\u963b\u5c3c\u3002\u6b64\u5916\uff0c\u8fd8\u8fdb\u884c\u4e86\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u4ee5\u8bc6\u522b\u9525\u5f62\u6a21\u5f0f\u5e76\u5206\u89e3\u9891\u7387\u3002");

    return model;
  }

  public static Model run3(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("loudspeaker_driver.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
