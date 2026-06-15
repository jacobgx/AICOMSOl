/*
 * porous_absorber.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class porous_absorber {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f_max", "10[kHz]", "\u6a21\u578b\u4e2d\u7684\u6700\u5927\u9891\u7387");
    model.param().set("lambda_min", "343[m/s]/f_max", "\u6700\u5c0f\u6ce2\u957f");
    model.param().set("theta0", "0[deg]", "\u5165\u5c04\u6ce2\u89d2\u5ea6");
    model.param().set("W", "20[cm]", "\u57df\u5bbd\u5ea6");
    model.param().set("H", "40[cm]", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("Hp", "10[cm]", "\u591a\u5b54\u5c42\u9ad8\u5ea6");
    model.param().set("a", "2.5[cm]", "\u5939\u6742\u7269\u534a\u5f84");
    model.param().set("Hpml", "20[cm]", "PML \u57df\u9ad8\u5ea6");
    model.param().set("mu0", "1.8e-5[Pa*s]", "\u7a7a\u6c14\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("epsilonP0", "0.995", "\u4e09\u805a\u6c30\u80fa\u6d77\u7ef5\u5b54\u9699\u7387");
    model.param().set("Rf0", "10.5e3[Pa*s/m^2]", "\u6d41\u963b\u7387");
    model.param().set("tau0", "1.0059", "\u66f2\u6298");
    model.param().set("Lv0", "240[um]", "\u9ecf\u6ede\u7279\u5f81\u957f\u5ea6");
    model.param().set("Lth0", "470[um]", "\u70ed\u7279\u5f81\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H+Hpml"});
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hpml", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W", "Hp"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-Hp"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "a");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"W/2", "-Hp/2"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("kx_e", "sin(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cx");
    model.component("comp1").variable("var1").set("ky_e", "-cos(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cy");
    model.component("comp1").variable("var1").set("k0", "intop2(acpr.k)", "\u81ea\u7531\u573a\u6ce2\u6570");
    model.component("comp1").variable("var1").set("kx", "k0*kx_e", "\u80cc\u666f\u5e73\u9762\u6ce2 k_x");
    model.component("comp1").variable("var1").set("ky", "k0*ky_e", "\u80cc\u666f\u5e73\u9762\u6ce2 k_y");
    model.component("comp1").variable("var1")
         .set("p_inc", "acpr.p_b", "\u5165\u5c04\u5e73\u9762\u6ce2\uff08\u80cc\u666f\u538b\u529b\u573a\uff09");
    model.component("comp1").variable("var1")
         .set("p_scat", "acpr.p_s", "\u6563\u5c04\u6ce2\uff08\u7a7a\u6c14\u57df\uff09");
    model.component("comp1").variable("var1")
         .set("Z", "aveop1(acpr.p_t/(nx*down(acpr.vx)+ny*down(acpr.vy)))/intop2(acpr.rho*acpr.c)", "\u6bd4\u8868\u9762\u6cd5\u5411\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("R", "aveop1(down(p_scat/p_inc))", "\u53cd\u5c04\u7cfb\u6570\uff08\u5e73\u5747\uff09");
    model.component("comp1").variable("var1")
         .set("alpha_R", "1-abs(R)^2", "\u5438\u58f0\u7cfb\u6570\uff08\u57fa\u4e8e R\uff09");
    model.component("comp1").variable("var1").set("Pin", "intop3(-acpr.I_by)", "\u5165\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("Pout", "intop3(acpr.I_sy)", "\u51fa\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("alpha", "1-Pout/Pin", "\u5438\u6536\u7cfb\u6570");
    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Z_ana", "aveop1(-i*intop1(acpr.Z)*intop1(acpr.k)/kx_ana*cot(kx_ana*Hp))/intop2(acpr.rho*acpr.c)", "\u6bd4\u8868\u9762\u6cd5\u5411\u963b\u6297\uff08\u89e3\u6790\uff09");
    model.component("comp1").variable("var2").set("ky_ana", "intop2(acpr.k)*sin(theta0)");
    model.component("comp1").variable("var2").set("kx_ana", "sqrt(intop1(acpr.k)^2-ky_ana^2)");
    model.component("comp1").variable("var2")
         .set("R_ana", "(Z_ana*cos(theta0)-1)/(Z_ana*cos(theta0)+1)", "\u53cd\u5c04\u7cfb\u6570\uff08\u89e3\u6790\uff09");
    model.component("comp1").variable("var2")
         .set("alpha_ana", "1-abs(R_ana)^2", "\u5438\u58f0\u7cfb\u6570\uff08\u89e3\u6790\uff09");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(3);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(4);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().set(4);

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
    model.component("comp1").material("mat2").label("\u4e09\u805a\u6c30\u80fa\u6d77\u7ef5");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"epsilonP0"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Rf", new String[]{"Rf0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Lth", new String[]{"Lth0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Lv", new String[]{"Lv0"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("tau", new String[]{"tau0"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3);
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "1/cos(theta0)");

    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(2);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new String[]{"kx_e", "ky_e", "0"});
    model.component("comp1").physics("acpr").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp1").physics("acpr").feature("bpf1").set("rho_mat", "from_mat");
    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("pom1").selection().set(1);
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc1").selection().set(1, 8);
    model.component("comp1").physics("acpr").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc2").selection().set(3, 9);
    model.component("comp1").physics("acpr").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc2").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("pc3", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc3").selection().set(5, 10);
    model.component("comp1").physics("acpr").feature("pc3").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc3").set("kFloquet", new String[]{"kx", "ky", "0"});

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").set(1);
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").set(8);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_min/5");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{10, 12.5, 16, 20, 25, 31.5, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800} {825, 850, 875, 900, 925, 950, 975, 1e3, 1.03e3, 1.06e3, 1.09e3, 1.12e3, 1.15e3, 1.18e3, 1.22e3, 1.25e3, 1.28e3, 1.32e3, 1.36e3, 1.4e3, 1.45e3, 1.5e3, 1.55e3, 1.6e3, 1.65e3, 1.7e3, 1.75e3, 1.8e3, 1.85e3, 1.9e3, 1.95e3, 2e3, 2.06e3, 2.12e3, 2.18e3, 2.24e3, 2.3e3, 2.36e3, 2.43e3, 2.5e3, 2.58e3, 2.65e3, 2.72e3, 2.8e3, 2.9e3, 3e3, 3.07e3, 3.15e3, 3.25e3, 3.35e3, 3.45e3, 3.55e3, 3.65e3, 3.75e3, 3.87e3, 4e3, 4.12e3, 4.25e3, 4.37e3, 4.5e3, 4.62e3, 4.75e3, 4.87e3, 5e3, 5.15e3, 5.3e3, 5.45e3, 5.6e3, 5.8e3, 6e3, 6.15e3, 6.3e3, 6.5e3, 6.7e3, 6.9e3, 7.1e3, 7.3e3, 7.5e3, 7.75e3, 8e3, 8.25e3, 8.5e3, 8.75e3, 9e3, 9.25e3, 9.5e3, 9.75e3, 1e4}");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f_max", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "f_max", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "theta0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0[deg] 45[deg]", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 108, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 108, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg1").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2, 4);
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("data", "dset2");
    model.result().dataset("arr1").set("fullsize", new int[]{4, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"kx", "ky"});
    model.result("pg1").run();
    model.result("pg1").label("\u603b\u58f0\u538b");
    model.result("pg1").set("data", "arr1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u6563\u5c04\u58f0\u538b");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "acpr.p_s");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 28, 0);
    model.result("pg3").run();
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u5165\u5c04\u58f0\u538b");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "acpr.p_b");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u70b9\u58f0\u538b");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(2);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("xlog", true);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6cd5\u5411\u963b\u6297");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f (Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Z/(rho*c) (1)");
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "Z", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "Z_ana", 1);
    model.result("pg6").run();
    model.result("pg6").set("xlog", true);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5438\u58f0\u7cfb\u6570");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f (Hz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\\alpha (1)");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "alpha", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "alpha_R", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "alpha_ana", 2);
    model.result("pg7").run();
    model.result("pg7").set("xlog", true);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6cd5\u5411\u5165\u5c04\u5438\u6536");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevelinput", "manual", 1);
    model.result("pg8").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "f (Hz)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\\alpha (1)");
    model.result("pg8").set("xlog", true);
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("oct1", "OctaveBand");
    model.result("pg8").feature("oct1").set("quantity", "bandpower");
    model.result("pg8").feature("oct1").set("markerpos", "datapoints");
    model.result("pg8").feature("oct1").set("linewidth", "preference");
    model.result("pg8").feature("oct1").selection().geom("geom1");
    model.result("pg8").feature("oct1").set("expr", "alpha");
    model.result("pg8").feature("oct1").set("exprtype", "general");
    model.result("pg8").feature("oct1").set("quantity", "bandaveragepsd");
    model.result("pg8").feature().duplicate("oct2", "oct1");
    model.result("pg8").run();
    model.result("pg8").feature("oct2").set("bandtype", "octave3");
    model.result("pg8").feature().duplicate("oct3", "oct2");
    model.result("pg8").run();
    model.result("pg8").feature("oct3").set("quantity", "continuous");
    model.result("pg8").feature("oct3").set("linewidth", 2);
    model.result("pg8").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").run();

    model.title("\u591a\u5b54\u5438\u58f0\u4f53");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u591a\u5b54\u5438\u58f0\u58c1\u886c\u6750\u6599\u7684\u4e8c\u7ef4\u6a21\u578b\u3002\u57fa\u4e8e\u4e24\u4e2a\u4e0d\u540c\u5165\u5c04\u89d2\u7684\u9891\u7387\u6765\u786e\u5b9a\u5438\u6536\u7cfb\u6570 \u03b1 \u548c\u6bd4\u8868\u9762\u963b\u6297 Z\u3002\u672c\u4f8b\u4f7f\u7528\u4e86\u201cFloquet \u5468\u671f\u201d\u8fb9\u754c\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("porous_absorber.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
