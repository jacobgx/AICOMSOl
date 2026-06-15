/*
 * sector_generator_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class sector_generator_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature("ccc").set("CoilName", "1");
    model.study("std1").feature("ccc").set("outputmap", new String[]{});
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").setSolveFor("/physics/rmm", true);

    model.param().set("d_wire", "3[mm]");
    model.param().descr("d_wire", "\u7ed5\u7ec4\u4e2d\u5bfc\u7ebf\u7684\u76f4\u5f84");
    model.param().set("N", "100");
    model.param().descr("N", "\u7ed5\u7ec4\u4e2d\u7684\u531d\u6570");
    model.param().set("rpm", "60[rpm]");
    model.param().descr("rpm", "\u8f6c\u5b50\u7684\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").insertFile("sector_generator_3d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5b9a\u5b50\u7ebf\u5708");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").set(17, 19, 20, 22, 23, 24);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6c38\u78c1\u4f53");
    model.component("comp1").selection("sel2").set(9, 10);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u65cb\u8f6c\u57df");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u7a33\u6001\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5468\u671f\u6027\u6761\u4ef6\uff1a\u8f6c\u5b50");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(1, 2, 6, 7, 14, 18, 23, 27);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5")
         .label("\u5468\u671f\u6027\u6761\u4ef6\uff1a\u5b9a\u5b50\uff0c\u6807\u91cf\u52bf");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(49, 52, 56, 59);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6")
         .label("\u5468\u671f\u6027\u6761\u4ef6\uff1a\u5b9a\u5b50\uff0c\u77e2\u52bf");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(75, 78, 96, 98, 118, 125);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u76ee\u6807");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(41, 42, 43, 44);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u6e90");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(48, 51, 55, 58);

    model.component("comp1").pair("ap1").swap();

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
    model.component("comp1").material("mat2").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N50 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.41[T]");
    model.component("comp1").material("mat2").selection().set(1, 2, 5, 6, 15, 16);
    model.component("comp1").material("mat3").selection().named("sel2");

    model.component("comp1").physics("rmm").create("mfc1", "MagneticFluxConservation", 3);
    model.component("comp1").physics("rmm").feature("mfc1")
         .label("\u78c1\u901a\u91cf\u5b88\u6052\uff1a\u6c14\u9699");
    model.component("comp1").physics("rmm").feature("mfc1").selection().set(3, 4, 7, 8, 11, 12, 13, 14);
    model.component("comp1").physics("rmm").create("mfc2", "MagneticFluxConservation", 3);
    model.component("comp1").physics("rmm").feature("mfc2")
         .label("\u78c1\u901a\u91cf\u5b88\u6052\uff1a\u8f6c\u5b50\u94c1\u82af");
    model.component("comp1").physics("rmm").feature("mfc2").selection().set(1, 2, 5, 6);
    model.component("comp1").physics("rmm").feature("mfc2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("rmm").feature("mfc2").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("rmm").feature("mfc2").feature("loss1").set("LossModel", "Bertotti");
    model.component("comp1").physics("rmm").create("al2", "AmperesLaw", 3);
    model.component("comp1").physics("rmm").feature("al2")
         .label("\u5b89\u57f9\u5b9a\u5f8b\uff1a\u5b9a\u5b50\u94c1\u82af");
    model.component("comp1").physics("rmm").feature("al2").selection().set(15, 16);
    model.component("comp1").physics("rmm").feature("al2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("rmm").feature("al2").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("rmm").feature("al2").feature("loss1").set("LossModel", "Bertotti");
    model.component("comp1").physics("rmm").create("cmag1", "ConductingMagnet", 3);
    model.component("comp1").physics("rmm").feature("cmag1").selection().named("sel2");
    model.component("comp1").physics("rmm").feature("cmag1")
         .set("ConstrainForInducedCurrents", "NoInducedCurrentsConstrain");
    model.component("comp1").physics("rmm").feature("cmag1").feature("north1").selection().set(45, 46);
    model.component("comp1").physics("rmm").feature("cmag1").feature("south1").selection().set(30, 34);
    model.component("comp1").physics("rmm").feature("cmag1").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("rmm").create("coil1", "Coil", 3);
    model.component("comp1").physics("rmm").feature("coil1").selection().named("sel1");
    model.component("comp1").physics("rmm").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("rmm").feature("coil1").set("ICoil", "0[A]");
    model.component("comp1").physics("rmm").feature("coil1").set("N", "N");
    model.component("comp1").physics("rmm").feature("coil1").set("AreaFrom", "Diameter");
    model.component("comp1").physics("rmm").feature("coil1").set("coilWindDiameter", "d_wire");
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").set("fl", 16);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").feature("ct1").selection().set(97);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").feature("cg1").selection().set(76);
    model.component("comp1").physics("rmm").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("rmm").create("ssc1", "SectorSymmetry", 2);
    model.component("comp1").physics("rmm").feature("ssc1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("rmm").feature("ssc1").set("nsector", 8);
    model.component("comp1").physics("rmm").feature("ssc1").set("PeriodicType", "AntiPeriodicity");
    model.component("comp1").physics("rmm").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("rmm").feature("pc1").selection().named("sel4");
    model.component("comp1").physics("rmm").feature("pc1").set("PeriodicType", "AntiPeriodicity");
    model.component("comp1").physics("rmm").create("pc2", "PeriodicCondition", 2);
    model.component("comp1").physics("rmm").feature("pc2").selection().named("sel5");
    model.component("comp1").physics("rmm").feature("pc2").set("PeriodicType", "AntiPeriodicity");
    model.component("comp1").physics("rmm").create("pc3", "PeriodicCondition", 2);
    model.component("comp1").physics("rmm").feature("pc3").selection().named("sel6");
    model.component("comp1").physics("rmm").feature("pc3").set("PeriodicType", "AntiPeriodicity");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().named("sel3");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "constantRevolutionsPerTime");
    model.component("comp1").common("rot1").set("revolutionsPerTime", "rpm");

    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1[S/m]"});

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4, 14, 21, 22, 23);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 6, 8, 10, 13, 16, 24);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source")
         .set(2, 4, 6, 8, 10, 13, 14, 16, 21, 22, 23, 24);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination")
         .set(1, 3, 5, 7, 9, 11, 12, 15, 17, 18, 19, 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat", "Stationary");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.005,0.25)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"rmm/gfa1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobal", 0.005);

    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().set(1, 2, 5, 6, 9, 10, 15, 16);
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", 8);
    model.result().dataset("sec1").set("rotinv", true);
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u5b8c\u6574\u51e0\u4f55\uff0c\u94c1\u82af");
    model.result().dataset("mir1").set("data", "sec1");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\uff08\u5f84\u5411\u5206\u91cf\uff09");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "sys2.e_r1");
    model.result("pg1").feature("vol1").set("descr", "\u57fa\u77e2 (sys2) r\uff0cx \u5206\u91cf");
    model.result("pg1").feature("vol1").set("expr", "sys2.e_r1*rmm.Bx+sys2.e_r2*rmm.By");
    model.result("pg1").feature("vol1").set("descractive", true);
    model.result("pg1").feature("vol1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\uff0cr \u5206\u91cf");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("colorscalemode", "linearsymmetric");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset1");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").label("\u7ebf\u5708\u611f\u5e94\u7535\u538b");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"rmm.VCoil_1"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u538b"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("emloss", "TimeToFrequencyLosses");
    model.study("std2").feature("emloss").set("fftinputstudy", "current");
    model.study("std2").feature("emloss").set("lossstarttime", "0");
    model.study("std2").feature("emloss").set("notsolnum", "auto");
    model.study("std2").feature("emloss").set("outputmap", new String[]{});
    model.study("std2").feature("emloss").setSolveFor("/physics/rmm", true);
    model.study("std2").feature("emloss").set("fftinputstudy", "std1");
    model.study("std2").feature("emloss").set("emT0", 0.25);
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u5468\u671f\u5e73\u5747\u635f\u8017 (rmm)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "rmm.Qh");
    model.result("pg3").feature("vol1").set("colortable", "GrayBody");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").feature("vol1").feature().create("filt1", "Filter");
    model.result("pg3").feature("vol1").feature("filt1").set("expr", "rmm.isLossCalculationDomain");
    model.result("pg3").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").set("data", "dset4");
    model.result().numerical("int1").selection().set(1, 2, 5, 6, 9, 10, 15, 16);
    model.result().numerical("int1").setIndex("expr", "rmm.Qh*16", 0);
    model.result().numerical("int1").setIndex("descr", "Loss power", 0);
    model.result().numerical("int1").setIndex("descr", "\u635f\u8017\u529f\u7387", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
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
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("repeat", "iterations");
    model.result().export("anim1").set("iterations", 10);
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u53d1\u7535\u673a\u4e09\u7ef4\u5efa\u6a21");

    model
         .description("\u672c App \u6f14\u793a\u5982\u4f55\u5229\u7528\u7535\u673a\u7684\u6247\u533a\u5bf9\u79f0\u6765\u6a21\u62df\u7535\u673a\uff08\u4f8b\u5982\uff0c\u53d1\u7535\u673a\u3001\u7535\u52a8\u673a\u6216\u9a71\u52a8\u5668\uff09\uff0c\u4ece\u800c\u51cf\u5c0f\u95ee\u9898\u89c4\u6a21\u3002\u6240\u7814\u7a76\u7684\u673a\u5668\u662f\u7b80\u5316\u7684\u4e09\u7ef4\u53d1\u7535\u673a\uff0c\u4ee5 generator_2d App \u4e2d\u4f7f\u7528\u7684\u51e0\u4f55\u7ed3\u6784\u4e3a\u57fa\u7840\u3002\n\n\u6b64 App \u4f7f\u7528\u201c\u65cb\u8f6c\u673a\u68b0\uff0c\u78c1\u201d\u63a5\u53e3\u3002\u5efa\u8bae\u60a8\u5148\u4e86\u89e3 rotating_machinery_3d_tutorial \u6a21\u578b\uff0c\u7136\u540e\u518d\u5b66\u4e60\u6b64 App\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("sector_generator_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
