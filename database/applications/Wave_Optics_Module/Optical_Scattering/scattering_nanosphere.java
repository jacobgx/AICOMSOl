/*
 * scattering_nanosphere.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class scattering_nanosphere {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.param().set("r0", "100[nm]");
    model.param().descr("r0", "\u7403\u4f53\u534a\u5f84");
    model.param().set("lda", "400[nm]");
    model.param().descr("lda", "\u6ce2\u957f");
    model.param().set("t_air", "lda/2");
    model.param().descr("t_air", "\u7403\u4f53\u5468\u56f4\u7684\u7a7a\u6c14\u539a\u5ea6");
    model.param().set("t_pml", "lda/2");
    model.param().descr("t_pml", "PML \u539a\u5ea6");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r0+t_air+t_pml");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "t_pml", 0);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "t_air", 1);
    model.component("comp1").geom("geom1").run("sph1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*(r0+t_air+t_pml)", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "2*(r0+t_air+t_pml)", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "2*(r0+t_air+t_pml)", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-(r0+t_air+t_pml)", "0", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "sph1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_L");
    model.component("comp1").cpl("intop1").selection().set(3);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("l_gold", "int_L(ewfd.Qrh)");
    model.component("comp1").variable("var1").descr("l_gold", "\u70ed\u635f\u8017");
    model.component("comp1").variable("var1").set("n_gold", "int_L(ewfd.nxx-j*ewfd.kixx)/(pi*r0^3/3)");
    model.component("comp1").variable("var1").descr("n_gold", "\u91d1\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var1").set("epsilonr_gold", "n_gold^2");
    model.component("comp1").variable("var1")
         .descr("epsilonr_gold", "\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.component("comp1").variable("var1").set("deltaS_gold", "1/real(sqrt(-(ewfd.k0*n_gold)^2))");
    model.component("comp1").variable("var1").descr("deltaS_gold", "\u91d1\u7684\u96c6\u80a4\u6df1\u5ea6");

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
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat2")
         .label("Au (Gold) (Rakic et al. 1998: Brendel-Bormann model; n,k 0.248-6.20 um)");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"2.4797e-01", "1.4943e+00"}, 
         {"2.5201e-01", "1.5158e+00"}, 
         {"2.5612e-01", "1.5382e+00"}, 
         {"2.6030e-01", "1.5611e+00"}, 
         {"2.6454e-01", "1.5844e+00"}, 
         {"2.6886e-01", "1.6078e+00"}, 
         {"2.7324e-01", "1.6306e+00"}, 
         {"2.7770e-01", "1.6527e+00"}, 
         {"2.8222e-01", "1.6733e+00"}, 
         {"2.8683e-01", "1.6921e+00"}, 
         {"2.9150e-01", "1.7085e+00"}, 
         {"2.9626e-01", "1.7220e+00"}, 
         {"3.0109e-01", "1.7322e+00"}, 
         {"3.0600e-01", "1.7387e+00"}, 
         {"3.1099e-01", "1.7411e+00"}, 
         {"3.1606e-01", "1.7391e+00"}, 
         {"3.2121e-01", "1.7327e+00"}, 
         {"3.2645e-01", "1.7219e+00"}, 
         {"3.3177e-01", "1.7071e+00"}, 
         {"3.3718e-01", "1.6887e+00"}, 
         {"3.4268e-01", "1.6677e+00"}, 
         {"3.4827e-01", "1.6452e+00"}, 
         {"3.5395e-01", "1.6224e+00"}, 
         {"3.5972e-01", "1.6006e+00"}, 
         {"3.6559e-01", "1.5810e+00"}, 
         {"3.7155e-01", "1.5645e+00"}, 
         {"3.7761e-01", "1.5515e+00"}, 
         {"3.8377e-01", "1.5419e+00"}, 
         {"3.9002e-01", "1.5351e+00"}, 
         {"3.9638e-01", "1.5301e+00"}, 
         {"4.0285e-01", "1.5255e+00"}, 
         {"4.0942e-01", "1.5196e+00"}, 
         {"4.1609e-01", "1.5107e+00"}, 
         {"4.2288e-01", "1.4971e+00"}, 
         {"4.2977e-01", "1.4771e+00"}, 
         {"4.3678e-01", "1.4493e+00"}, 
         {"4.4390e-01", "1.4126e+00"}, 
         {"4.5114e-01", "1.3661e+00"}, 
         {"4.5850e-01", "1.3095e+00"}, 
         {"4.6598e-01", "1.2427e+00"}, 
         {"4.7358e-01", "1.1664e+00"}, 
         {"4.8130e-01", "1.0821e+00"}, 
         {"4.8915e-01", "9.9182e-01"}, 
         {"4.9712e-01", "8.9849e-01"}, 
         {"5.0523e-01", "8.0543e-01"}, 
         {"5.1347e-01", "7.1590e-01"}, 
         {"5.2184e-01", "6.3260e-01"}, 
         {"5.3035e-01", "5.5731e-01"}, 
         {"5.3900e-01", "4.9085e-01"}, 
         {"5.4779e-01", "4.3326e-01"}, 
         {"5.5672e-01", "3.8405e-01"}, 
         {"5.6580e-01", "3.4242e-01"}, 
         {"5.7503e-01", "3.0751e-01"}, 
         {"5.8440e-01", "2.7843e-01"}, 
         {"5.9393e-01", "2.5437e-01"}, 
         {"6.0362e-01", "2.3457e-01"}, 
         {"6.1346e-01", "2.1841e-01"}, 
         {"6.2346e-01", "2.0533e-01"}, 
         {"6.3363e-01", "1.9487e-01"}, 
         {"6.4396e-01", "1.8664e-01"}, 
         {"6.5446e-01", "1.8030e-01"}, 
         {"6.6514e-01", "1.7558e-01"}, 
         {"6.7598e-01", "1.7227e-01"}, 
         {"6.8701e-01", "1.7016e-01"}, 
         {"6.9821e-01", "1.6911e-01"}, 
         {"7.0959e-01", "1.6897e-01"}, 
         {"7.2117e-01", "1.6966e-01"}, 
         {"7.3292e-01", "1.7107e-01"}, 
         {"7.4488e-01", "1.7313e-01"}, 
         {"7.5702e-01", "1.7577e-01"}, 
         {"7.6937e-01", "1.7895e-01"}, 
         {"7.8191e-01", "1.8262e-01"}, 
         {"7.9466e-01", "1.8675e-01"}, 
         {"8.0762e-01", "1.9129e-01"}, 
         {"8.2079e-01", "1.9621e-01"}, 
         {"8.3418e-01", "2.0151e-01"}, 
         {"8.4778e-01", "2.0715e-01"}, 
         {"8.6160e-01", "2.1311e-01"}, 
         {"8.7565e-01", "2.1938e-01"}, 
         {"8.8993e-01", "2.2594e-01"}, 
         {"9.0445e-01", "2.3278e-01"}, 
         {"9.1919e-01", "2.3989e-01"}, 
         {"9.3418e-01", "2.4725e-01"}, 
         {"9.4942e-01", "2.5486e-01"}, 
         {"9.6490e-01", "2.6271e-01"}, 
         {"9.8063e-01", "2.7079e-01"}, 
         {"9.9662e-01", "2.7909e-01"}, 
         {"1.0129e+00", "2.8761e-01"}, 
         {"1.0294e+00", "2.9634e-01"}, 
         {"1.0462e+00", "3.0528e-01"}, 
         {"1.0632e+00", "3.1442e-01"}, 
         {"1.0806e+00", "3.2376e-01"}, 
         {"1.0982e+00", "3.3329e-01"}, 
         {"1.1161e+00", "3.4302e-01"}, 
         {"1.1343e+00", "3.5293e-01"}, 
         {"1.1528e+00", "3.6304e-01"}, 
         {"1.1716e+00", "3.7334e-01"}, 
         {"1.1907e+00", "3.8382e-01"}, 
         {"1.2101e+00", "3.9449e-01"}, 
         {"1.2299e+00", "4.0535e-01"}, 
         {"1.2499e+00", "4.1641e-01"}, 
         {"1.2703e+00", "4.2765e-01"}, 
         {"1.2910e+00", "4.3909e-01"}, 
         {"1.3121e+00", "4.5072e-01"}, 
         {"1.3335e+00", "4.6256e-01"}, 
         {"1.3552e+00", "4.7459e-01"}, 
         {"1.3773e+00", "4.8684e-01"}, 
         {"1.3998e+00", "4.9930e-01"}, 
         {"1.4226e+00", "5.1198e-01"}, 
         {"1.4458e+00", "5.2488e-01"}, 
         {"1.4694e+00", "5.3801e-01"}, 
         {"1.4933e+00", "5.5138e-01"}, 
         {"1.5177e+00", "5.6499e-01"}, 
         {"1.5424e+00", "5.7885e-01"}, 
         {"1.5676e+00", "5.9297e-01"}, 
         {"1.5931e+00", "6.0736e-01"}, 
         {"1.6191e+00", "6.2203e-01"}, 
         {"1.6455e+00", "6.3699e-01"}, 
         {"1.6723e+00", "6.5224e-01"}, 
         {"1.6996e+00", "6.6780e-01"}, 
         {"1.7273e+00", "6.8368e-01"}, 
         {"1.7555e+00", "6.9989e-01"}, 
         {"1.7841e+00", "7.1645e-01"}, 
         {"1.8132e+00", "7.3336e-01"}, 
         {"1.8428e+00", "7.5065e-01"}, 
         {"1.8728e+00", "7.6831e-01"}, 
         {"1.9034e+00", "7.8638e-01"}, 
         {"1.9344e+00", "8.0486e-01"}, 
         {"1.9660e+00", "8.2377e-01"}, 
         {"1.9980e+00", "8.4313e-01"}, 
         {"2.0306e+00", "8.6294e-01"}, 
         {"2.0637e+00", "8.8324e-01"}, 
         {"2.0974e+00", "9.0404e-01"}, 
         {"2.1316e+00", "9.2535e-01"}, 
         {"2.1663e+00", "9.4720e-01"}, 
         {"2.2016e+00", "9.6961e-01"}, 
         {"2.2375e+00", "9.9259e-01"}, 
         {"2.2740e+00", "1.0162e+00"}, 
         {"2.3111e+00", "1.0404e+00"}, 
         {"2.3488e+00", "1.0652e+00"}, 
         {"2.3871e+00", "1.0907e+00"}, 
         {"2.4260e+00", "1.1169e+00"}, 
         {"2.4656e+00", "1.1438e+00"}, 
         {"2.5058e+00", "1.1715e+00"}, 
         {"2.5467e+00", "1.1999e+00"}, 
         {"2.5882e+00", "1.2291e+00"}, 
         {"2.6304e+00", "1.2592e+00"}, 
         {"2.6733e+00", "1.2901e+00"}, 
         {"2.7169e+00", "1.3219e+00"}, 
         {"2.7612e+00", "1.3545e+00"}, 
         {"2.8062e+00", "1.3882e+00"}, 
         {"2.8520e+00", "1.4228e+00"}, 
         {"2.8985e+00", "1.4584e+00"}, 
         {"2.9457e+00", "1.4951e+00"}, 
         {"2.9938e+00", "1.5328e+00"}, 
         {"3.0426e+00", "1.5717e+00"}, 
         {"3.0922e+00", "1.6117e+00"}, 
         {"3.1426e+00", "1.6529e+00"}, 
         {"3.1939e+00", "1.6954e+00"}, 
         {"3.2460e+00", "1.7391e+00"}, 
         {"3.2989e+00", "1.7842e+00"}, 
         {"3.3527e+00", "1.8306e+00"}, 
         {"3.4074e+00", "1.8784e+00"}, 
         {"3.4629e+00", "1.9277e+00"}, 
         {"3.5194e+00", "1.9785e+00"}, 
         {"3.5768e+00", "2.0308e+00"}, 
         {"3.6351e+00", "2.0847e+00"}, 
         {"3.6944e+00", "2.1403e+00"}, 
         {"3.7546e+00", "2.1976e+00"}, 
         {"3.8159e+00", "2.2566e+00"}, 
         {"3.8781e+00", "2.3175e+00"}, 
         {"3.9413e+00", "2.3802e+00"}, 
         {"4.0056e+00", "2.4449e+00"}, 
         {"4.0709e+00", "2.5116e+00"}, 
         {"4.1373e+00", "2.5803e+00"}, 
         {"4.2048e+00", "2.6511e+00"}, 
         {"4.2733e+00", "2.7242e+00"}, 
         {"4.3430e+00", "2.7995e+00"}, 
         {"4.4138e+00", "2.8771e+00"}, 
         {"4.4858e+00", "2.9572e+00"}, 
         {"4.5589e+00", "3.0397e+00"}, 
         {"4.6333e+00", "3.1247e+00"}, 
         {"4.7088e+00", "3.2124e+00"}, 
         {"4.7856e+00", "3.3028e+00"}, 
         {"4.8637e+00", "3.3960e+00"}, 
         {"4.9430e+00", "3.4921e+00"}, 
         {"5.0236e+00", "3.5911e+00"}, 
         {"5.1055e+00", "3.6932e+00"}, 
         {"5.1888e+00", "3.7985e+00"}, 
         {"5.2734e+00", "3.9069e+00"}, 
         {"5.3594e+00", "4.0187e+00"}, 
         {"5.4468e+00", "4.1340e+00"}, 
         {"5.5356e+00", "4.2528e+00"}, 
         {"5.6258e+00", "4.3752e+00"}, 
         {"5.7176e+00", "4.5013e+00"}, 
         {"5.8108e+00", "4.6314e+00"}, 
         {"5.9056e+00", "4.7653e+00"}, 
         {"6.0019e+00", "4.9034e+00"}, 
         {"6.0997e+00", "5.0456e+00"}, 
         {"6.1992e+00", "5.1922e+00"}});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"2.4797e-01", "1.9575e+00"}, 
         {"2.5201e-01", "1.9594e+00"}, 
         {"2.5612e-01", "1.9605e+00"}, 
         {"2.6030e-01", "1.9604e+00"}, 
         {"2.6454e-01", "1.9587e+00"}, 
         {"2.6886e-01", "1.9551e+00"}, 
         {"2.7324e-01", "1.9494e+00"}, 
         {"2.7770e-01", "1.9414e+00"}, 
         {"2.8222e-01", "1.9312e+00"}, 
         {"2.8683e-01", "1.9188e+00"}, 
         {"2.9150e-01", "1.9042e+00"}, 
         {"2.9626e-01", "1.8879e+00"}, 
         {"3.0109e-01", "1.8700e+00"}, 
         {"3.0600e-01", "1.8512e+00"}, 
         {"3.1099e-01", "1.8319e+00"}, 
         {"3.1606e-01", "1.8128e+00"}, 
         {"3.2121e-01", "1.7946e+00"}, 
         {"3.2645e-01", "1.7784e+00"}, 
         {"3.3177e-01", "1.7648e+00"}, 
         {"3.3718e-01", "1.7548e+00"}, 
         {"3.4268e-01", "1.7490e+00"}, 
         {"3.4827e-01", "1.7479e+00"}, 
         {"3.5395e-01", "1.7516e+00"}, 
         {"3.5972e-01", "1.7597e+00"}, 
         {"3.6559e-01", "1.7714e+00"}, 
         {"3.7155e-01", "1.7856e+00"}, 
         {"3.7761e-01", "1.8007e+00"}, 
         {"3.8377e-01", "1.8152e+00"}, 
         {"3.9002e-01", "1.8276e+00"}, 
         {"3.9638e-01", "1.8366e+00"}, 
         {"4.0285e-01", "1.8413e+00"}, 
         {"4.0942e-01", "1.8411e+00"}, 
         {"4.1609e-01", "1.8362e+00"}, 
         {"4.2288e-01", "1.8268e+00"}, 
         {"4.2977e-01", "1.8140e+00"}, 
         {"4.3678e-01", "1.7988e+00"}, 
         {"4.4390e-01", "1.7829e+00"}, 
         {"4.5114e-01", "1.7681e+00"}, 
         {"4.5850e-01", "1.7567e+00"}, 
         {"4.6598e-01", "1.7509e+00"}, 
         {"4.7358e-01", "1.7532e+00"}, 
         {"4.8130e-01", "1.7661e+00"}, 
         {"4.8915e-01", "1.7916e+00"}, 
         {"4.9712e-01", "1.8312e+00"}, 
         {"5.0523e-01", "1.8852e+00"}, 
         {"5.1347e-01", "1.9530e+00"}, 
         {"5.2184e-01", "2.0328e+00"}, 
         {"5.3035e-01", "2.1222e+00"}, 
         {"5.3900e-01", "2.2188e+00"}, 
         {"5.4779e-01", "2.3201e+00"}, 
         {"5.5672e-01", "2.4245e+00"}, 
         {"5.6580e-01", "2.5305e+00"}, 
         {"5.7503e-01", "2.6370e+00"}, 
         {"5.8440e-01", "2.7434e+00"}, 
         {"5.9393e-01", "2.8493e+00"}, 
         {"6.0362e-01", "2.9545e+00"}, 
         {"6.1346e-01", "3.0587e+00"}, 
         {"6.2346e-01", "3.1621e+00"}, 
         {"6.3363e-01", "3.2645e+00"}, 
         {"6.4396e-01", "3.3662e+00"}, 
         {"6.5446e-01", "3.4671e+00"}, 
         {"6.6514e-01", "3.5675e+00"}, 
         {"6.7598e-01", "3.6674e+00"}, 
         {"6.8701e-01", "3.7669e+00"}, 
         {"6.9821e-01", "3.8661e+00"}, 
         {"7.0959e-01", "3.9653e+00"}, 
         {"7.2117e-01", "4.0644e+00"}, 
         {"7.3292e-01", "4.1635e+00"}, 
         {"7.4488e-01", "4.2629e+00"}, 
         {"7.5702e-01", "4.3625e+00"}, 
         {"7.6937e-01", "4.4624e+00"}, 
         {"7.8191e-01", "4.5627e+00"}, 
         {"7.9466e-01", "4.6635e+00"}, 
         {"8.0762e-01", "4.7649e+00"}, 
         {"8.2079e-01", "4.8669e+00"}, 
         {"8.3418e-01", "4.9695e+00"}, 
         {"8.4778e-01", "5.0729e+00"}, 
         {"8.6160e-01", "5.1770e+00"}, 
         {"8.7565e-01", "5.2820e+00"}, 
         {"8.8993e-01", "5.3878e+00"}, 
         {"9.0445e-01", "5.4946e+00"}, 
         {"9.1919e-01", "5.6024e+00"}, 
         {"9.3418e-01", "5.7111e+00"}, 
         {"9.4942e-01", "5.8210e+00"}, 
         {"9.6490e-01", "5.9319e+00"}, 
         {"9.8063e-01", "6.0440e+00"}, 
         {"9.9662e-01", "6.1573e+00"}, 
         {"1.0129e+00", "6.2718e+00"}, 
         {"1.0294e+00", "6.3875e+00"}, 
         {"1.0462e+00", "6.5046e+00"}, 
         {"1.0632e+00", "6.6231e+00"}, 
         {"1.0806e+00", "6.7429e+00"}, 
         {"1.0982e+00", "6.8642e+00"}, 
         {"1.1161e+00", "6.9869e+00"}, 
         {"1.1343e+00", "7.1112e+00"}, 
         {"1.1528e+00", "7.2370e+00"}, 
         {"1.1716e+00", "7.3644e+00"}, 
         {"1.1907e+00", "7.4935e+00"}, 
         {"1.2101e+00", "7.6242e+00"}, 
         {"1.2299e+00", "7.7566e+00"}, 
         {"1.2499e+00", "7.8908e+00"}, 
         {"1.2703e+00", "8.0268e+00"}, 
         {"1.2910e+00", "8.1646e+00"}, 
         {"1.3121e+00", "8.3044e+00"}, 
         {"1.3335e+00", "8.4460e+00"}, 
         {"1.3552e+00", "8.5896e+00"}, 
         {"1.3773e+00", "8.7353e+00"}, 
         {"1.3998e+00", "8.8829e+00"}, 
         {"1.4226e+00", "9.0327e+00"}, 
         {"1.4458e+00", "9.1847e+00"}, 
         {"1.4694e+00", "9.3388e+00"}, 
         {"1.4933e+00", "9.4951e+00"}, 
         {"1.5177e+00", "9.6538e+00"}, 
         {"1.5424e+00", "9.8147e+00"}, 
         {"1.5676e+00", "9.9780e+00"}, 
         {"1.5931e+00", "1.0144e+01"}, 
         {"1.6191e+00", "1.0312e+01"}, 
         {"1.6455e+00", "1.0483e+01"}, 
         {"1.6723e+00", "1.0656e+01"}, 
         {"1.6996e+00", "1.0832e+01"}, 
         {"1.7273e+00", "1.1010e+01"}, 
         {"1.7555e+00", "1.1192e+01"}, 
         {"1.7841e+00", "1.1376e+01"}, 
         {"1.8132e+00", "1.1563e+01"}, 
         {"1.8428e+00", "1.1752e+01"}, 
         {"1.8728e+00", "1.1945e+01"}, 
         {"1.9034e+00", "1.2140e+01"}, 
         {"1.9344e+00", "1.2339e+01"}, 
         {"1.9660e+00", "1.2541e+01"}, 
         {"1.9980e+00", "1.2745e+01"}, 
         {"2.0306e+00", "1.2953e+01"}, 
         {"2.0637e+00", "1.3164e+01"}, 
         {"2.0974e+00", "1.3379e+01"}, 
         {"2.1316e+00", "1.3597e+01"}, 
         {"2.1663e+00", "1.3818e+01"}, 
         {"2.2016e+00", "1.4042e+01"}, 
         {"2.2375e+00", "1.4271e+01"}, 
         {"2.2740e+00", "1.4502e+01"}, 
         {"2.3111e+00", "1.4738e+01"}, 
         {"2.3488e+00", "1.4977e+01"}, 
         {"2.3871e+00", "1.5219e+01"}, 
         {"2.4260e+00", "1.5466e+01"}, 
         {"2.4656e+00", "1.5717e+01"}, 
         {"2.5058e+00", "1.5971e+01"}, 
         {"2.5467e+00", "1.6229e+01"}, 
         {"2.5882e+00", "1.6492e+01"}, 
         {"2.6304e+00", "1.6758e+01"}, 
         {"2.6733e+00", "1.7029e+01"}, 
         {"2.7169e+00", "1.7304e+01"}, 
         {"2.7612e+00", "1.7584e+01"}, 
         {"2.8062e+00", "1.7867e+01"}, 
         {"2.8520e+00", "1.8156e+01"}, 
         {"2.8985e+00", "1.8448e+01"}, 
         {"2.9457e+00", "1.8746e+01"}, 
         {"2.9938e+00", "1.9048e+01"}, 
         {"3.0426e+00", "1.9354e+01"}, 
         {"3.0922e+00", "1.9666e+01"}, 
         {"3.1426e+00", "1.9982e+01"}, 
         {"3.1939e+00", "2.0304e+01"}, 
         {"3.2460e+00", "2.0630e+01"}, 
         {"3.2989e+00", "2.0962e+01"}, 
         {"3.3527e+00", "2.1299e+01"}, 
         {"3.4074e+00", "2.1640e+01"}, 
         {"3.4629e+00", "2.1988e+01"}, 
         {"3.5194e+00", "2.2340e+01"}, 
         {"3.5768e+00", "2.2699e+01"}, 
         {"3.6351e+00", "2.3062e+01"}, 
         {"3.6944e+00", "2.3432e+01"}, 
         {"3.7546e+00", "2.3807e+01"}, 
         {"3.8159e+00", "2.4188e+01"}, 
         {"3.8781e+00", "2.4575e+01"}, 
         {"3.9413e+00", "2.4967e+01"}, 
         {"4.0056e+00", "2.5366e+01"}, 
         {"4.0709e+00", "2.5771e+01"}, 
         {"4.1373e+00", "2.6182e+01"}, 
         {"4.2048e+00", "2.6600e+01"}, 
         {"4.2733e+00", "2.7023e+01"}, 
         {"4.3430e+00", "2.7454e+01"}, 
         {"4.4138e+00", "2.7890e+01"}, 
         {"4.4858e+00", "2.8334e+01"}, 
         {"4.5589e+00", "2.8784e+01"}, 
         {"4.6333e+00", "2.9241e+01"}, 
         {"4.7088e+00", "2.9705e+01"}, 
         {"4.7856e+00", "3.0176e+01"}, 
         {"4.8637e+00", "3.0653e+01"}, 
         {"4.9430e+00", "3.1139e+01"}, 
         {"5.0236e+00", "3.1631e+01"}, 
         {"5.1055e+00", "3.2130e+01"}, 
         {"5.1888e+00", "3.2637e+01"}, 
         {"5.2734e+00", "3.3152e+01"}, 
         {"5.3594e+00", "3.3674e+01"}, 
         {"5.4468e+00", "3.4204e+01"}, 
         {"5.5356e+00", "3.4741e+01"}, 
         {"5.6258e+00", "3.5287e+01"}, 
         {"5.7176e+00", "3.5840e+01"}, 
         {"5.8108e+00", "3.6401e+01"}, 
         {"5.9056e+00", "3.6971e+01"}, 
         {"6.0019e+00", "3.7548e+01"}, 
         {"6.0997e+00", "3.8134e+01"}, 
         {"6.1992e+00", "3.8728e+01"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").physics("ewfd").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("ewfd").prop("BackgroundField")
         .set("Eb", new String[]{"0", "0", "exp(-j*ewfd.k0*x)"});
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().set(3, 16);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 5);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").physics("ewfd").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("ewfd").feature("symp1").label("\u5bf9\u79f0\u9762\uff0cPEC");
    model.component("comp1").physics("ewfd").feature("symp1").selection().set(2, 5, 9, 17, 18);
    model.component("comp1").physics("ewfd").feature("symp1").set("Symmetry_type", "pec");
    model.component("comp1").physics("ewfd").create("symp2", "SymmetryPlane", 2);
    model.component("comp1").physics("ewfd").feature("symp2").label("\u5bf9\u79f0\u9762\uff0cPMC");
    model.component("comp1").physics("ewfd").feature("symp2").selection().set(1, 4, 8, 11, 14);
    model.component("comp1").physics("ewfd").create("ffd1", "FarFieldDomain", 3);
    model.component("comp1").physics("ewfd").feature("ffd1").feature("ffc1")
         .set("SymmetrySettings", "FromSymmetryPlanes");
    model.component("comp1").physics("ewfd").create("csc1", "CrossSectionCalculation", 3);
    model.component("comp1").physics("ewfd").feature("csc1").selection().set(3);
    model.component("comp1").physics("ewfd").feature("csc1").set("I0", "0.5*(1[V/m^2])^2/Z0_const");
    model.component("comp1").physics("ewfd").prop("MeshControl").set("SizeControlParameter", "Wavelength");
    model.component("comp1").physics("ewfd").prop("MeshControl")
         .set("PhysicsControlledMeshMinimumWavelength", "lda");
    model.component("comp1").physics("ewfd").prop("MeshControl").set("ResolveWaveInLossyMedia", true);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "r0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "r0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(400[nm],300[nm]/30,700[nm])", 0);
    model.study("std1").feature("wave").set("plist", "lda");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(3);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "ewfd.Qrh");
    model.result("pg1").feature("vol1").set("descr", "\u7535\u963b\u635f\u8017");
    model.result("pg1").run();
    model.result().create("pg2", "PolarGroup");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "last", 1);
    model.result("pg2").create("rp1", "RadiationPattern");
    model.result("pg2").feature("rp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rp1").set("linewidth", "preference");
    model.result("pg2").feature("rp1").set("phidisc", 100);
    model.result("pg2").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg2").feature("rp1").set("legendmethod", "manual");
    model.result("pg2").feature("rp1").setIndex("legends", "E \u9762", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("rp2", "RadiationPattern");
    model.result("pg2").feature("rp2").set("markerpos", "datapoints");
    model.result("pg2").feature("rp2").set("linewidth", "preference");
    model.result("pg2").feature("rp2").set("phidisc", 100);
    model.result("pg2").feature("rp2").set("legendmethod", "manual");
    model.result("pg2").feature("rp2").setIndex("legends", "H \u9762", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"l_gold"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u70ed\u635f\u8017"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6ce2\u957f (m)");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6298\u5c04\u7387");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{"n_gold"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u91d1\u7684\u6298\u5c04\u7387"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg4").feature("glob1").setIndex("expr", "real(n_gold)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u91d1\u7684\u6298\u5c04\u7387\uff0c\u5b9e\u90e8", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(n_gold)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u91d1\u7684\u6298\u5c04\u7387\uff0c\u865a\u90e8", 1);
    model.result("pg4").feature("glob1").set("legend", true);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u91d1\u7684\u6298\u5c04\u7387\uff0c\u5b9e\u90e8", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u91d1\u7684\u6298\u5c04\u7387\uff0c\u865a\u90e8", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"epsilonr_gold"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob1").setIndex("expr", "real(epsilonr_gold)", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5b9e\u90e8", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "imag(epsilonr_gold)", 1);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u865a\u90e8", 1);
    model.result("pg5").feature("glob1")
         .setIndex("legends", "\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5b9e\u90e8", 0);
    model.result("pg5").feature("glob1")
         .setIndex("legends", "\u91d1\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u865a\u90e8", 1);
    model.result("pg5").run();
    model.result("pg5").set("ylabel", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.result("pg5").set("legendpos", "lowerleft");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"deltaS_gold"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u91d1\u7684\u96c6\u80a4\u6df1\u5ea6"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg6").feature("glob1").setIndex("unit", "nm", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "ewfd.sigmaAbs/(pi*r0^2)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Normalized absorption cross section", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "ewfd.sigmaSca/(pi*r0^2)", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "Normalized scattering cross section", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "ewfd.sigmaExt/(pi*r0^2)", 2);
    model.result("pg7").feature("glob1").setIndex("descr", "Normalized extinction cross section", 2);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "lda");
    model.result("pg7").feature("glob1").set("xdataunit", "nm");
    model.result("pg7").feature("glob1").set("autosolution", false);
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").run();
    model.result("pg1").run();

    model.title("\u91d1\u7eb3\u7c73\u7403\u7684\u5149\u6563\u5c04");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u91d1\u7eb3\u7c73\u7403\u7684\u53cd\u5c04\u5149\u7684\u5e73\u9762\u6ce2\u6563\u5c04\uff0c\u8ba1\u7b97\u5149\u5b66\u9891\u7387\u8303\u56f4\u7684\u6563\u5c04\uff0c\u5728\u6b64\u8303\u56f4\u5185\uff0c\u53ef\u4ee5\u5c06\u91d1\u4f5c\u4e3a\u590d\u4ecb\u7535\u5e38\u6570\u4e3a\u8d1f\u503c\u7684\u6750\u6599\u8fdb\u884c\u5efa\u6a21\uff1b\u6b64\u5916\uff0c\u8fd8\u8ba1\u7b97\u8fdc\u573a\u6a21\u5f0f\u548c\u635f\u8017\u3002");

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
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();

    model.label("scattering_nanosphere.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
