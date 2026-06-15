/*
 * loudspeaker_driver_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_driver_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature("ccc").set("CoilName", "1");
    model.study("std1").feature("ccc").set("outputmap", new String[]{});
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").setSolveFor("/physics/mf", true);
    model.study("std1").feature("ccc").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("ccc").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("N0", "100", "\u7ebf\u5708\u531d\u6570");
    model.param().set("V0", "3.55[V]", "\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("f_loss", "40[Hz]", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u7684\u9891\u7387");
    model.param().set("omega_loss", "2*pi*f_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u7684\u89d2\u9891\u7387");
    model.param().set("fmax", "4000[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("h_slit1", "0.2[mm]", "\u78c1\u9699\u72ed\u7f1d 1");
    model.param().set("h_slit2", "0.4[mm]", "\u78c1\u9699\u72ed\u7f1d 2");
    model.param()
         .set("deltaS", "165[mm]*50", "\u81ea\u7531\u7a7a\u95f4\u57df\u4e2d\u7684\u4eba\u5de5\u96c6\u80a4\u6df1\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "loudspeaker_driver_3d.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(1, 2, 4, 5, 7, 8, 11, 15, 21, 25, 28, 31, 34, 37, 40, 43, 45, 48, 51, 56, 59, 62, 65, 69, 72, 75, 80, 85, 91, 97, 132, 138, 146, 152, 155, 160, 174, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 205, 212, 219, 221, 222, 223);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7ebf\u5708");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel2").set(16, 17, 18);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7a7a\u6c14\u57df");
    model.component("comp1").selection("sel3").set(1, 3, 5, 6, 12, 19, 21, 23, 29, 32);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u94dd\u57df");
    model.component("comp1").selection("sel4").set(25, 27, 30);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u78c1\u4f53");
    model.component("comp1").selection("sel5").set(26);

    model.component("comp1").variable().create("var1");

    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u6781\u7247");
    model.component("comp1").selection("sel6").set(4, 24);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u975e\u5bfc\u7535\u56fa\u4f53\u57df");
    model.component("comp1").selection("sel7").set(7, 8, 9, 10, 11, 13, 14, 20);

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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Aluminum");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Composite");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Cloth");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("Foam");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "0.4");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "67[kg/m^3]");
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("Coil");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat7").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").label("Glass Fiber");
    model.component("comp1").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat8").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat9").label("Generic Ferrite");
    model.component("comp1").material("mat9").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat9").propertyGroup("def").set("lossfactor", "0.01");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat9").propertyGroup("def").set("youngsmodulus", "200[GPa]");
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "5000[kg/m^3]");
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity").set("normBr", "0.4[T]");
    model.component("comp1").material("mat2").selection().named("sel6");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7800[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"180[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.29"});
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat4").selection().set(2, 22);
    model.component("comp1").material("mat5").selection().set(20);
    model.component("comp1").material("mat6").selection().set(28, 31);
    model.component("comp1").material("mat7").selection().named("sel2");
    model.component("comp1").material("mat8").selection().set(7, 8, 9, 10, 11, 13, 14, 15);
    model.component("comp1").material("mat9").selection().named("sel5");

    model.component("comp1").physics("mf").selection()
         .set(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27, 29, 32);
    model.component("comp1").physics("mf").feature("fsp1").set("sigma_stab_mat", "from_delta_s");
    model.component("comp1").physics("mf").feature("fsp1").set("delta_s", "deltaS");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().named("sel7");
    model.component("comp1").physics("mf").feature("als1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1")
         .set("sigma", new String[]{"5[S/m]", "0", "0", "0", "5[S/m]", "0", "0", "0", "5[S/m]"});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als2").selection().named("sel5");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als2").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mf").feature("als2").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2")
         .set("sigma", new String[]{"5[S/m]", "0", "0", "0", "5[S/m]", "0", "0", "0", "5[S/m]"});
    model.component("comp1").physics("mf").create("als3", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als3").selection().named("sel6");
    model.component("comp1").physics("mf").feature("als3").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").create("als4", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als4").selection().named("sel4");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel2");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "linper(V0)");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N0");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "3.5e-8[m^2]");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").set("fl", 4);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection()
         .set(190, 191, 192);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection()
         .set(56, 59, 62);
    model.component("comp1").physics("mf").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("mf").feature("symp1").selection().named("sel1");
    model.component("comp1").physics("acpr").selection().named("sel3");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(10);
    model.component("comp1").physics("acpr").feature("efc1").set("SymmetryType", "SectorSymmetryWithPlane");
    model.component("comp1").physics("acpr").feature("efc1").set("TransformationType", "RotationAndReflection");
    model.component("comp1").physics("acpr").feature("efc1").set("n", 4);
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().set(3, 10);
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().set(23);
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra1").set("h", "h_slit1");
    model.component("comp1").physics("acpr").create("nra2", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra2").selection().set(6);
    model.component("comp1").physics("acpr").feature("nra2").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra2").set("h", "h_slit2");
    model.component("comp1").physics("acpr").create("nra3", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra3").selection().set(12);
    model.component("comp1").physics("acpr").feature("nra3").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra3").set("a", "1.5[mm]");
    model.component("comp1").physics("solid").selection()
         .set(2, 4, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 20, 22, 24, 25, 26, 27, 28, 30, 31);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").selection()
         .set(2, 7, 8, 9, 10, 11, 13, 14, 15, 22);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp2", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").selection().set(20);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").set("beta_dK", "0.14/omega_loss");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp3", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").selection().set(28, 31);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").set("beta_dK", "0.46/omega_loss");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(159, 168, 169, 170);

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics().create("mmcpl1", "Magnetomechanics", 3);
    model.component("comp1").multiphysics("mmcpl1").selection().named("sel2");
    model.component("comp1").multiphysics("mmcpl1").set("OnlyLorentz", true);

    model.study("std1").label("\u7814\u7a76 1 - \u9891\u7387\u54cd\u5e94");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("ccc").set("geometricNonlinearity", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mmcpl1", false);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("plist", "{31.5, 63, 125, 250, 500, 1e3, 2e3, 4e3}");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "343[m/s]/fmax/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(181, 190, 191, 192, 194, 197);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(288, 318);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(283, 309, 310, 311, 313, 317, 320, 321, 330, 331, 371, 372, 377, 393);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection()
         .set(335, 336, 342, 343, 353, 354, 355, 356, 364, 365, 380);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(298, 312, 314);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(312);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(286, 298, 302, 314, 317);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(182, 183, 184, 185, 187, 188, 189);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(301);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(285, 295);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(6, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 20, 23);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection()
         .set(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 23);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 18);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(38, 41);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "3[mm]");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(11, 12);
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").selection()
         .set(93, 94, 111, 112, 120, 121, 122, 123, 126, 127, 128, 129, 137, 141);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").selection().set(328, 329);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").selection().set(369, 370);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").selection().set(375, 376);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis4").selection().set(358, 359);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis4").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis5").selection().set(190);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis5").set("numelem", 18);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(9, 78, 140, 151);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "5[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().set(2, 22, 28);
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("map4", "Map");
    model.component("comp1").mesh("mesh1").feature("map4").selection().set(154, 157, 161, 164, 166);
    model.component("comp1").mesh("mesh1").feature("map4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map4").feature("dis1").selection().set(439);
    model.component("comp1").mesh("mesh1").feature("map4").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map4").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map4").feature("dis2").selection().set(436, 441);
    model.component("comp1").mesh("mesh1").feature("map4").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().set(143, 148, 207, 222, 223);
    model.component("comp1").mesh("mesh1").feature("ftri3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").selection().set(143, 148, 207);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmax", "6[mm]");
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().set(30, 31);
    model.component("comp1").mesh("mesh1").create("swe5", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe5").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe5").selection().set(27, 29, 32);
    model.component("comp1").mesh("mesh1").feature("swe5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe5").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe6", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe6").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe6").selection().set(25);
    model.component("comp1").mesh("mesh1").create("ftri4", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri4").selection().set(174, 198, 200);
    model.component("comp1").mesh("mesh1").feature("ftri4").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri4").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri4").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri4").feature("size1").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(174, 198);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(150, 258, 259, 260, 276, 278, 279, 280, 281, 322, 323, 324, 325, 326, 328, 357, 362);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 1.1);
    model.component("comp1").mesh("mesh1").create("swe7", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe7").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe7").selection().set(4, 24, 26);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 3, 5, 19, 21);
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl2").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/mf", true);
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/mmcpl1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u7ed3\u6784\u7279\u5f81\u6a21\u6001");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").feature("eig").setSolveFor("/physics/mf", false);
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/mmcpl1", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", 4);
    model.result().dataset("sec1").set("include", "manual");
    model.result().dataset("sec1").set("startsector", 3);
    model.result().dataset("sec1").set("sectorsinclude", 3);
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().dataset().create("sec2", "Sector3D");
    model.result().dataset("sec2").set("data", "dset4");
    model.result().dataset("sec2").set("sectors", 4);
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("startsector", 3);
    model.result().dataset("sec2").set("sectorsinclude", 3);
    model.result().dataset("sec2").set("trans", "rotrefl");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u51e0\u4f55");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "1");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(25, 27, 30);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol1").feature("mtrl1").set("family", "aluminum");
    model.result("pg1").create("vol2", "Volume");
    model.result("pg1").feature("vol2").set("expr", "1");
    model.result("pg1").feature("vol2").set("coloring", "uniform");
    model.result("pg1").feature("vol2").set("color", "custom");
    model.result("pg1").feature("vol2")
         .set("customcolor", new double[]{0.16470588743686676, 0.1921568661928177, 0.2980392277240753});
    model.result("pg1").feature("vol2").create("sel1", "Selection");
    model.result("pg1").feature("vol2").feature("sel1").selection().set(2, 22, 28, 31);
    model.result("pg1").run();
    model.result("pg1").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol2").feature("mtrl1").set("family", "textile");
    model.result("pg1").feature("vol2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").create("vol3", "Volume");
    model.result("pg1").feature("vol3").set("expr", "1");
    model.result("pg1").feature("vol3").set("coloring", "uniform");
    model.result("pg1").feature("vol3").set("color", "custom");
    model.result("pg1").feature("vol3")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg1").feature("vol3").create("sel1", "Selection");
    model.result("pg1").feature("vol3").feature("sel1").selection().set(20);
    model.result("pg1").run();
    model.result("pg1").feature("vol3").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol3").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol3").feature("mtrl1").set("family", "textile");
    model.result("pg1").feature("vol3").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").create("vol4", "Volume");
    model.result("pg1").feature("vol4").set("expr", "1");
    model.result("pg1").feature("vol4").create("sel1", "Selection");
    model.result("pg1").feature("vol4").feature("sel1").selection().set(7, 8, 9, 10, 11, 13, 14, 15);
    model.result("pg1").run();
    model.result("pg1").feature("vol4").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol4").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol4").feature("mtrl1").set("family", "chrome");
    model.result("pg1").create("vol5", "Volume");
    model.result("pg1").feature("vol5").set("expr", "1");
    model.result("pg1").feature("vol5").create("sel1", "Selection");
    model.result("pg1").feature("vol5").feature("sel1").selection().named("sel2");
    model.result("pg1").run();
    model.result("pg1").feature("vol5").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol5").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol5").feature("mtrl1").set("family", "copper");
    model.result("pg1").create("vol6", "Volume");
    model.result("pg1").feature("vol6").set("expr", "1");
    model.result("pg1").feature("vol6").set("coloring", "uniform");
    model.result("pg1").feature("vol6").set("color", "custom");
    model.result("pg1").feature("vol6")
         .set("customcolor", new double[]{0.6549019813537598, 0.6901960968971252, 0.7568627595901489});
    model.result("pg1").feature("vol6").create("sel1", "Selection");
    model.result("pg1").feature("vol6").feature("sel1").selection().set(4, 24);
    model.result("pg1").run();
    model.result("pg1").feature("vol6").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol6").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol6").feature("mtrl1").set("family", "iron");
    model.result("pg1").feature("vol6").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").create("vol7", "Volume");
    model.result("pg1").feature("vol7").set("expr", "1");
    model.result("pg1").feature("vol7").set("coloring", "uniform");
    model.result("pg1").feature("vol7").set("color", "custom");
    model.result("pg1").feature("vol7")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg1").feature("vol7").create("sel1", "Selection");
    model.result("pg1").feature("vol7").feature("sel1").selection().set(26);
    model.result("pg1").run();
    model.result("pg1").feature("vol7").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol7").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol7").feature("mtrl1").set("family", "soil");
    model.result("pg1").feature("vol7").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u9759\u78c1\u573a");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("selnumber", 50);
    model.result("pg2").feature("str1").selection().set(100);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("radiusexpr", "0.2");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", "1");
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("sel4");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("vol1").feature("mtrl1").set("family", "aluminum");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "0.3");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection()
         .set(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 27, 28, 31, 32, 33, 34, 35, 36, 38, 39, 63, 64, 65, 66, 67, 69, 70, 92, 93, 95, 96, 102, 103, 106, 107, 108, 119, 120, 121, 122, 123, 132, 133, 142, 147, 148, 149, 150, 151, 153, 154, 159, 160, 161, 258, 259, 260, 276, 277, 278, 279, 282, 284, 285, 286, 287, 289, 301, 302, 303, 305, 317, 319, 323, 325, 326, 332, 333, 334, 352, 357, 360, 361, 362, 363, 366);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mf.normJ");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(11, 12, 14, 20, 85, 88, 90, 91, 92, 94, 110, 112, 132, 174, 190, 191, 192, 198, 199, 205);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\u548c\u58f0\u538b\u7ea7");
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "acpr.p_t");
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "acpr.Lp_t");
    model.result("pg4").feature("surf2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("trn1").set("move", new int[]{300, 0, 0});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb");
    model.result("pg5").set("data", "sec1");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "solid.disp");
    model.result("pg5").feature("surf1").set("unit", "\u00b5m");
    model.result("pg5").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u6d1b\u4f26\u5179\u529b\uff08z \u5206\u91cf\uff09");
    model.result("pg6").setIndex("looplevel", 5, 0);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "mmcpl1.FLtzz");
    model.result("pg6").feature("vol1").set("differential", true);
    model.result("pg6").run();
    model.result("pg6").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u7ebf\u5708\u7535\u6d41\u5bc6\u5ea6 Jx");
    model.result("pg7").setIndex("looplevel", 5, 0);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "mf.Jx");
    model.result("pg7").feature("vol1").set("differential", true);
    model.result("pg7").feature("vol1").set("colortable", "Thermal");
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().named("sel2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u7ebf\u5708\u7535\u6d41\u5bc6\u5ea6 Jy");
    model.result("pg8").setIndex("looplevel", 5, 0);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", "mf.Jy");
    model.result("pg8").feature("vol1").set("differential", true);
    model.result("pg8").feature("vol1").set("colortable", "Thermal");
    model.result("pg8").feature("vol1").create("sel1", "Selection");
    model.result("pg8").feature("vol1").feature("sel1").selection().named("sel2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u8f74\u5411\u54cd\u5e94");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlog", true);
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "dB(Z)");
    model.result("pg9").create("oct1", "OctaveBand");
    model.result("pg9").feature("oct1").set("quantity", "bandpower");
    model.result("pg9").feature("oct1").set("markerpos", "datapoints");
    model.result("pg9").feature("oct1").set("linewidth", "preference");
    model.result("pg9").feature("oct1").selection().geom("geom1");
    model.result("pg9").feature("oct1").set("expr", "pext(0,0,1[m])");
    model.result("pg9").feature("oct1").set("quantity", "continuous");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u7ebf\u5708\u7535\u963b\u6297");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "Z(\\Omega)");
    model.result("pg10").set("xlog", true);
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "abs(mf.ZCoil_1)", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "abs(Z)", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "real(mf.ZCoil_1)", 1);
    model.result("pg10").feature("glob1").setIndex("descr", "real(Z)", 1);
    model.result("pg10").feature("glob1").setIndex("expr", "imag(mf.ZCoil_1)", 2);
    model.result("pg10").feature("glob1").setIndex("descr", "imag(Z)", 2);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().create("pg11", "PolarGroup");
    model.result("pg11").run();
    model.result("pg11").label("\u7a7a\u95f4\u54cd\u5e94");
    model.result("pg11").set("zeroangle", "up");
    model.result("pg11").create("rp1", "RadiationPattern");
    model.result("pg11").feature("rp1").set("markerpos", "datapoints");
    model.result("pg11").feature("rp1").set("linewidth", "preference");
    model.result("pg11").feature("rp1").set("anglerestr", "manual");
    model.result("pg11").feature("rp1").set("phimin", -90);
    model.result("pg11").feature("rp1").set("phirange", 180);
    model.result("pg11").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg11").feature("rp1").set("radius", "1[m]");
    model.result("pg11").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result("pg11").feature("rp1").set("legend", true);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").setIndex("looplevel", 1, 0);
    model.result("pg12").label("\u632f\u578b (solid)");
    model.result("pg12").set("showlegends", false);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg12").feature("surf1").set("threshold", "manual");
    model.result("pg12").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg12").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg12").feature("surf1").set("colortabletrans", "none");
    model.result("pg12").feature("surf1").set("colorscalemode", "linear");
    model.result("pg12").feature("surf1").create("def", "Deform");
    model.result("pg12").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg12").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg12").label("\u632f\u578b (solid)");
    model.result("pg12").run();
    model.result("pg12").set("data", "sec2");
    model.result("pg12").set("looplevel", new int[]{6});
    model.result("pg12").run();
    model.result("pg12").set("looplevel", new int[]{1});
    model.result("pg12").run();
    model.result("pg12").set("looplevel", new int[]{2});
    model.result("pg12").run();
    model.result("pg12").set("looplevel", new int[]{3});
    model.result("pg12").run();
    model.result("pg12").set("looplevel", new int[]{4});
    model.result("pg12").run();
    model.result("pg12").set("looplevel", new int[]{5});
    model.result("pg12").run();
    model.result("pg1").run();

    model.title("\u626c\u58f0\u5668\u9a71\u52a8\u5668\u4e09\u7ef4\u6a21\u578b - \u9891\u57df\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u9ad8\u6548\u6c42\u89e3\u626c\u58f0\u5668\u7684\u5168\u4e09\u7ef4\u632f\u52a8\u7535\u58f0\u591a\u7269\u7406\u573a\u6a21\u578b\u3002\u53c2\u7167\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u73b0\u6709\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b loudspeaker_driver\uff0c\u8fd9\u4e2a\u4e09\u7ef4\u7248\u672c\u4e2d\u7684\u7269\u7406\u573a\u8bbe\u7f6e\u4e0e\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7248\u672c\u57fa\u672c\u76f8\u540c\u3002\u5728\u4e09\u7ef4\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u9009\u62e9\u8fed\u4ee3\u6c42\u89e3\u5668\u5efa\u8bae\u8fdb\u884c\u9891\u57df\u5206\u6790\uff0c\u4ee5\u786e\u4fdd\u6709\u6548\u6c42\u89e3\u58f0\u5b66\u3001\u7ed3\u6784\u548c\u7535\u78c1\u8026\u5408\u95ee\u9898\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("loudspeaker_driver_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
