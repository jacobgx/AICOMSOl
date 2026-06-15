/*
 * transverse_isotropic_porous_layer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class transverse_isotropic_porous_layer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pelw", "PoroelasticWavesSinglePhysics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "3000[Hz]", "\u9891\u7387");
    model.param().set("theta0", "0[deg]", "\u5165\u5c04\u89d2");
    model.param().set("H", "6[cm]", "\u591a\u5b54\u5c42\u539a\u5ea6");
    model.param().set("W", "10[cm]", "\u57df\u5bbd\u5ea6");
    model.param().set("Hair", "20[cm]", "\u7a7a\u6c14\u5c42\u539a\u5ea6");
    model.param().set("Hpml", "5[cm]", "PML \u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H+Hair+Hpml"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hair", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("kx_e", "sin(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cx");
    model.component("comp1").variable("var1").set("ky_e", "-cos(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cy");
    model.component("comp1").variable("var1").set("k0", "intop_pnt(acpr.k)", "\u81ea\u7531\u573a\u6ce2\u6570");
    model.component("comp1").variable("var1").set("kx", "k0*kx_e", "\u80cc\u666f\u5e73\u9762\u6ce2\uff0ck_x");
    model.component("comp1").variable("var1").set("ky", "k0*ky_e", "\u80cc\u666f\u5e73\u9762\u6ce2\uff0ck_y");
    model.component("comp1").variable("var1")
         .set("Zn", "aveop_bnd(acpr.p_t/(nx*up(acpr.vx)+ny*up(acpr.vy)))/intop_pnt(acpr.rho*acpr.c)", "\u5f52\u4e00\u5316\u6bd4\u8868\u9762\u963b\u6297");
    model.component("comp1").variable("var1").set("Pin", "intop_bnd(-acpr.I_by)", "\u5165\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("Pout", "intop_bnd(acpr.I_sy)", "\u51fa\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("alpha", "1-Pout/Pin", "\u5438\u6536\u7cfb\u6570");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_pnt");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "aveop_bnd");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(4);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_bnd");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3);
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "acpr");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "1/cos(theta0)");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

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

    model.component("comp1").physics("pelw").selection().set(1);
    model.component("comp1").physics("pelw").feature("pm1").set("G_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("G", "(50+7i)[kPa]");
    model.component("comp1").physics("pelw").feature("pm1").set("nu_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("nu", 0.1);
    model.component("comp1").physics("pelw").feature("pm1").set("rhod_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("rhod", "60[kg/m^3]");
    model.component("comp1").physics("pelw").feature("pm1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("epsilon_p", 0.99);
    model.component("comp1").physics("pelw").feature("pm1").set("Rf_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("Rf", "17000[N*s/m^4]");
    model.component("comp1").physics("pelw").feature("pm1").set("tau_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("tau", 1.01);
    model.component("comp1").physics("pelw").feature("pm1").set("Lv_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("Lv", "140[um]");
    model.component("comp1").physics("pelw").feature("pm1").set("Lth_mat", "userdef");
    model.component("comp1").physics("pelw").feature("pm1").set("Lth", "150[um]");
    model.component("comp1").physics("pelw").create("apm1", "AnisotropicPoroelasticWavesMaterial", 2);
    model.component("comp1").physics("pelw").feature("apm1").selection().set(1);
    model.component("comp1").physics("pelw").feature("apm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("pelw").feature("apm1").set("Evector_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("Evector", new int[]{10, 10, 0});
    model.component("comp1").physics("pelw").feature("apm1").set("GvectorVo_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1")
         .set("GvectorVo", new String[]{"(50+7i)[kPa]", "(120+22i)[kPa]", "0"});
    model.component("comp1").physics("pelw").feature("apm1").set("nuvector_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("nuvector", new double[]{0.1, 0.1, 0});
    model.component("comp1").physics("pelw").feature("apm1").set("rhod_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("rhod", "60[kg/m^3]");
    model.component("comp1").physics("pelw").feature("apm1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("epsilon_p", 0.99);
    model.component("comp1").physics("pelw").feature("apm1").set("Rf_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1")
         .set("Rf", new String[]{"5000[N*s/m^4]", "0", "0", "0", "17000[N*s/m^4]", "0", "0", "0", "1"});
    model.component("comp1").physics("pelw").feature("apm1").set("tau_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1")
         .set("tau", new double[]{1.01, 0, 0, 0, 1.01, 0, 0, 0, 1.01});
    model.component("comp1").physics("pelw").feature("apm1").set("Lv_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1")
         .set("Lv", new String[]{"126[um]", "0", "0", "0", "140[um]", "0", "0", "0", "1"});
    model.component("comp1").physics("pelw").feature("apm1").set("Lth_mat", "userdef");
    model.component("comp1").physics("pelw").feature("apm1").set("Lth", "150[um]");
    model.component("comp1").physics("pelw").create("pfix1", "Fixed", 1);
    model.component("comp1").physics("pelw").feature("pfix1").selection().set(2);
    model.component("comp1").physics("pelw").create("il2", "ImperviousLayer", 1);
    model.component("comp1").physics("pelw").feature("il2").selection().set(2);
    model.component("comp1").physics("pelw").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("pelw").feature("pc1").selection().set(1, 8);
    model.component("comp1").physics("pelw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("pelw").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").selection().set(2, 3);
    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(2);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new String[]{"kx_e", "ky_e", "0"});
    model.component("comp1").physics("acpr").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp1").physics("acpr").feature("bpf1").set("rho_mat", "from_mat");
    model.component("comp1").physics("acpr").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc1").selection().set(3, 9);
    model.component("comp1").physics("acpr").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc2").selection().set(5, 10);
    model.component("comp1").physics("acpr").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc2").set("kFloquet", new String[]{"kx", "ky", "0"});

    model.component("comp1").multiphysics().create("apb1", "AcousticPorousBoundary", 1);
    model.component("comp1").multiphysics("apb1").selection().set(4);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "H/12");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "H/12");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u6a2a\u5411\u5404\u5411\u540c\u6027");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "500 700 1000 3000");
    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "range(0,5,85)", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/apb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5404\u5411\u540c\u6027");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "500 700 1000 3000");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"pelw/apm1"});
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "range(0,5,85)", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("fullsize", new int[]{4, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"kx", "ky"});
    model.result().dataset("arr1").selection().geom("geom1", 2);
    model.result().dataset("arr1").selection().geom("geom1", 2);
    model.result().dataset("arr1").selection().set(1, 2);
    model.result().dataset().duplicate("arr2", "arr1");
    model.result().dataset("arr2").set("data", "dset2");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb (pelw)");
    model.result("pg1").set("data", "arr1");
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"u", "v"});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b (acpr)");
    model.result("pg2").set("data", "arr1");
    model.result("pg2").setIndex("looplevel", 10, 0);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "apb1.p_t");
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg3").set("data", "arr1");
    model.result("pg3").setIndex("looplevel", 10, 0);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u963b\u6297\uff1a500 Hz");
    model.result("pg4").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u5165\u5c04\u89d2 (deg)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8868\u9762\u963b\u6297 (1)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(-Zn)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "real(Zn)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(-Zn)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "imag(Zn)", 1);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("autosolution", false);
    model.result("pg4").feature("glob1").set("legendprefix", "\u6a2a\u5411\u5404\u5411\u540c\u6027\uff1a");
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("data", "dset2");
    model.result("pg4").feature("glob2").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").feature("glob2").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg4").feature("glob2").setIndex("expr", "real(-Zn)", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "real(Zn)", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "imag(-Zn)", 1);
    model.result("pg4").feature("glob2").setIndex("descr", "imag(Zn)", 1);
    model.result("pg4").feature("glob2").set("linestyle", "dashed");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").feature("glob2").set("linewidth", 2);
    model.result("pg4").feature("glob2").set("autosolution", false);
    model.result("pg4").feature("glob2").set("legendprefix", "\u5404\u5411\u540c\u6027\uff1a");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u9762\u963b\u6297\uff1a700 Hz");
    model.result("pg5").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u8868\u9762\u963b\u6297\uff1a1000 Hz");
    model.result("pg6").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u8868\u9762\u963b\u6297\uff1a3000 Hz");
    model.result("pg7").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5438\u58f0\u7cfb\u6570");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u5165\u5c04\u89d2 (deg)");
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", -0.5);
    model.result("pg8").set("xmax", 85.5);
    model.result("pg8").set("ymin", -0.01);
    model.result("pg8").set("ymax", 1.01);
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "alpha", 0);
    model.result("pg8").feature("glob1").set("legendprefix", "\u6a2a\u5411\u5404\u5411\u540c\u6027\uff1a");
    model.result("pg8").create("glob2", "Global");
    model.result("pg8").feature("glob2").set("markerpos", "datapoints");
    model.result("pg8").feature("glob2").set("linewidth", "preference");
    model.result("pg8").feature("glob2").set("data", "dset2");
    model.result("pg8").feature("glob2").setIndex("expr", "alpha", 0);
    model.result("pg8").feature("glob2").set("linestyle", "dashed");
    model.result("pg8").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg8").feature("glob2").set("legendprefix", "\u5404\u5411\u540c\u6027\uff1a");
    model.result("pg8").run();
    model.result("pg2").run();

    model.title("\u6a2a\u5411\u5404\u5411\u540c\u6027\u591a\u5b54\u5c42");

    model
         .description("\u672c\u6559\u7a0b\u7814\u7a76\u7531\u73bb\u7483\u68c9\u5236\u6210\u7684\u591a\u5b54\u5c42\u7684\u58f0\u5b66\u5c5e\u6027\u3002\u591a\u5b54\u6750\u6599\u5177\u6709\u6a2a\u5411\u5404\u5411\u540c\u6027\u5c5e\u6027\uff0c\u5e76\u91c7\u7528\u5b8c\u6574\u7684\u5404\u5411\u5f02\u6027\u591a\u5b54\u5f39\u6027\u6750\u6599\u6a21\u578b\u8fdb\u884c\u5efa\u6a21\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("transverse_isotropic_porous_layer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
