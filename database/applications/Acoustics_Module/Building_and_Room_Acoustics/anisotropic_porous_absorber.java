/*
 * anisotropic_porous_absorber.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class anisotropic_porous_absorber {

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
    model.param().set("f0", "3000[Hz]", "\u9891\u7387");
    model.param().set("theta0", "0[deg]", "\u5165\u5c04\u89d2");
    model.param().set("H", "4[cm]", "\u591a\u5b54\u5c42\u539a\u5ea6");
    model.param().set("W", "10[cm]", "\u57df\u5bbd\u5ea6");
    model.param().set("Hair", "20[cm]", "\u7a7a\u6c14\u5c42\u539a\u5ea6");
    model.param().set("Hpml", "5[cm]", "PML \u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H+Hair+Hpml"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hair", 1);
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
         .set("Zn", "aveop_bnd(acpr.p_t/(nx*up(acpr.vx)+ny*up(acpr.vy)))", "\u6bd4\u8868\u9762\u963b\u6297");
    model.component("comp1").variable("var1").set("Pin", "intop_bnd(-acpr.I_by)", "\u5165\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("Pout", "intop_bnd(acpr.I_sy)", "\u51fa\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("alpha", "1-Pout/Pin", "\u5438\u6536\u7cfb\u6570");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("opname", "intop_pnt");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(4);
    model.component("comp1").cpl("aveop1").set("opname", "aveop_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4);
    model.component("comp1").cpl("intop2").set("opname", "intop_bnd");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3);
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"0.98"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel")
         .set("Rf", new String[]{"26000[N*s/m^4]", "48000[N*s/m^4]", "26000[N*s/m^4]"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel")
         .set("Lth", new String[]{"150[um]"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel")
         .set("Lv", new String[]{"68[um]", "57[um]", "68[um]"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel")
         .set("tau", new String[]{"1.03", "1.08", "1.03"});

    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(2);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new String[]{"kx_e", "ky_e", "0"});
    model.component("comp1").physics("acpr").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp1").physics("acpr").feature("bpf1").set("rho_mat", "from_mat");
    model.component("comp1").physics("acpr").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc1").selection().set(5, 10);
    model.component("comp1").physics("acpr").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc2").selection().set(3, 9);
    model.component("comp1").physics("acpr").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc2").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("pc3", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc3").selection().set(1, 8);
    model.component("comp1").physics("acpr").feature("pc3").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc3").set("kFloquet", new String[]{"kx", "ky", "0"});
    model.component("comp1").physics("acpr").create("apm1", "AnisotropicPoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("apm1").selection().set(1);
    model.component("comp1").physics("acpr").feature("apm1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("apm1").set("SolidMaterial", "mat2");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "H/24");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "H/24");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "range(10,10,5000)");
    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "0,40,80", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component().copy("comp2", "comp1");

    model.component("comp2").variable("var2").set("k0", "comp2.intop_pnt(acpr2.k)");
    model.component("comp2").variable("var2")
         .set("Zn", "comp2.aveop_bnd(acpr2.p_t/(nx*up(acpr2.vx)+ny*up(acpr2.vy)))");
    model.component("comp2").variable("var2").set("Pin", "comp2.intop_bnd(-acpr2.I_by)");
    model.component("comp2").variable("var2").set("Pout", "comp2.intop_bnd(acpr2.I_sy)");

    model.component("comp2").material("mat4").selection().set(1);

    model.component("comp2").physics("acpr2").feature().remove("apm1");
    model.component("comp2").physics("acpr2").feature().remove("pc3");
    model.component("comp2").physics("acpr2").selection().set(2, 3);
    model.component("comp2").physics().create("pelw", "PoroelasticWavesSinglePhysics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/pelw", true);

    model.component("comp2").physics("pelw").selection().set(1);
    model.component("comp2").physics("pelw").feature("pm1").set("IsotropicOption_BA", "EG");
    model.component("comp2").physics("pelw").feature("pm1").set("FluidMaterial", "mat3");
    model.component("comp2").physics("pelw").create("apm1", "AnisotropicPoroelasticWavesMaterial", 2);
    model.component("comp2").physics("pelw").feature("apm1").selection().set(1);
    model.component("comp2").physics("pelw").feature("apm1").set("SolidModel", "Orthotropic");
    model.component("comp2").physics("pelw").feature("apm1").set("FluidMaterial", "mat3");
    model.component("comp2").physics("pelw").create("pfix1", "Fixed", 1);
    model.component("comp2").physics("pelw").feature("pfix1").selection().set(2);
    model.component("comp2").physics("pelw").create("il2", "ImperviousLayer", 1);
    model.component("comp2").physics("pelw").feature("il2").selection().set(2);
    model.component("comp2").physics("pelw").create("pc1", "PeriodicCondition", 1);
    model.component("comp2").physics("pelw").feature("pc1").selection().set(1, 8);
    model.component("comp2").physics("pelw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp2").physics("pelw").feature("pc1").set("kFloquet", new String[]{"kx", "ky", "0"});

    model.component("comp2").material("mat4").propertyGroup().create("Orthotropic", "Orthotropic", "Orthotropic");
    model.component("comp2").material("mat4").propertyGroup("Orthotropic")
         .set("Evector", new String[]{"42e5", "12e5", "42e5"});
    model.component("comp2").material("mat4").propertyGroup("Orthotropic")
         .set("nuvector", new String[]{"0", "0", "0"});
    model.component("comp2").material("mat4").propertyGroup()
         .create("OrthotropicVoGrp", "OrthotropicVoGrp", "Orthotropic_Voigt_notation");
    model.component("comp2").material("mat4").propertyGroup("OrthotropicVoGrp")
         .set("GvectorVo", new String[]{"21e5", "60e4", "21e5"});
    model.component("comp2").material("mat4").propertyGroup("def").set("density", new String[]{"140"});
    model.component("comp2").material("mat4").propertyGroup("def").set("lossfactor", new String[]{"0.1"});

    model.component("comp2").multiphysics().create("apb1", "AcousticPorousBoundary", 1);
    model.component("comp2").multiphysics("apb1").selection().set(4);

    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std2").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/apb1", true);
    model.study("std2").feature("freq").set("plist", "range(10,10,5000)");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "0,40,80", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().remove("dset2");
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("fullsize", new int[]{4, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"kx", "ky"});
    model.result().dataset("arr1").selection().geom("geom1", 2);
    model.result().dataset("arr1").selection().geom("geom1", 2);
    model.result().dataset("arr1").selection().set(1, 2);
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(1);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").set("data", "arr1");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "alpha", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("data", "dset1");
    model.result("pg3").feature("glob2").setIndex("expr", "alpha", 0);
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob2").set("autodescr", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 50, 1);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").feature("surf1").set("expr", "pelw.iomega*v");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").feature("surf2").set("expr", "pelw.v_tY");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").feature("surf3").set("data", "dset4");
    model.result("pg4").feature("surf3").setIndex("looplevel", 50, 1);
    model.result("pg4").feature("surf3").set("expr", "acpr.vy");
    model.result("pg4").feature("surf3").set("inheritplot", "surf1");
    model.result("pg4").run();
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("arraydim", "1");
    model.result("pg4").feature("ann1").set("showpoint", false);
    model.result("pg4").feature("ann1").set("manualindexing", true);
    model.result("pg4").feature().duplicate("ann2", "ann1");
    model.result("pg4").feature("ann2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").set("arrayindex", 1);
    model.result("pg4").feature().duplicate("ann3", "ann2");
    model.result("pg4").feature("ann3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("ann3").set("arrayindex", 2);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 500, 1);
    model.result("pg5").feature("surf3").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").setIndex("looplevel", 500, 1);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg6").feature("glob1").setIndex("expr", "at2(0.06,0.02,arg(pelw.iomega*v)-arg(pelw.vY))", 0);
    model.result("pg6").feature("glob1").set("unwrapphase", true);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg6").feature("glob1").set("autosolution", false);
    model.result("pg6").feature("glob1").set("linecolor", "custom");
    model.result("pg6").feature("glob1")
         .set("customlinecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "Phase between fluid and solid velocity", 0);
    model.result("pg6").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg6").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("gmrk1").set("displaymode", "intersection");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("glob1").feature("gmrk1").set("intersectionline", "horizontal");
    model.result("pg6").feature("glob1").feature("gmrk1").set("intersectiony", 0);
    model.result("pg6").feature("glob1").feature("gmrk1").set("showlines", true);
    model.result("pg6").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").setIndex("expr", "alpha", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "Absorption coefficient (Poroelastic Waves)", 0);
    model.result("pg6").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg6").feature("glob2").set("autosolution", false);
    model.result("pg6").feature("glob2").set("linecolor", "custom");
    model.result("pg6").feature("glob2")
         .set("customlinecolor", new double[]{0.6980392336845398, 0.13333334028720856, 0.13333334028720856});
    model.result("pg6").feature("glob2").set("linewidth", 2);
    model.result("pg6").feature().duplicate("glob3", "glob2");
    model.result("pg6").run();
    model.result("pg6").feature("glob3").set("data", "dset1");
    model.result("pg6").feature("glob3").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("glob3").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg6").feature("glob3").setIndex("descr", "Absorption coefficient (Poroacoustics)", 0);
    model.result("pg6").feature("glob3").set("linewidth", 1);
    model.result("pg6").feature("glob3").set("linestyle", "dashed");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(1);
    model.result("pg7").feature("ptgr1").set("expr", "pelw.apm1.cs_poroXX");
    model.result("pg7").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "x direction", 0);
    model.result("pg7").create("ptgr2", "PointGraph");
    model.result("pg7").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr2").set("linewidth", "preference");
    model.result("pg7").feature("ptgr2").selection().set(1);
    model.result("pg7").feature("ptgr2").set("expr", "pelw.apm1.cs_poroYY");
    model.result("pg7").feature("ptgr2").set("xdatasolnumtype", "level2");
    model.result("pg7").feature("ptgr2").set("legend", true);
    model.result("pg7").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr2").setIndex("legends", "y direction", 0);
    model.result("pg7").run();
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("expr", "pelw.apm1.cp_fastXX");
    model.result("pg8").feature("ptgr1").setIndex("legends", "fast x direction", 0);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("expr", "pelw.apm1.cp_fastYY");
    model.result("pg8").feature("ptgr2").setIndex("legends", "fast y direction", 0);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg8").feature().duplicate("ptgr4", "ptgr2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("ptgr3").set("expr", "pelw.apm1.cp_slowXX");
    model.result("pg8").feature("ptgr3").setIndex("legends", "slow x direction", 0);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr4").set("expr", "pelw.apm1.cp_slowYY");
    model.result("pg8").feature("ptgr4").setIndex("legends", "slow y direction", 0);
    model.result("pg8").run();
    model.result("pg1").run();

    model.title("\u5404\u5411\u5f02\u6027\u591a\u5b54\u5438\u58f0\u4f53");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u5404\u5411\u5f02\u6027\u591a\u5b54\u5438\u58f0\u6750\u6599\u7684\u4e8c\u7ef4\u6a21\u578b\uff0c\u5176\u4e2d\u5728\u4e09\u4e2a\u4e0d\u540c\u7684\u5165\u5c04\u89d2\u5ea6\u4e0b\u5c06\u5438\u58f0\u7cfb\u6570 \u03b1 \u4f5c\u4e3a\u9891\u7387\u7684\u51fd\u6570\u8fdb\u884c\u786e\u5b9a\uff0c\u540c\u65f6\u4f7f\u7528\u4e86\u5468\u671f\u6027 Floquet \u8fb9\u754c\u6761\u4ef6\u3002\n\n\u672c\u6a21\u578b\u4f7f\u7528\u4e24\u79cd\u4e0d\u540c\u7684\u65b9\u6cd5\u6765\u6a21\u62df\u591a\u5b54\u6750\u6599\uff1a\u591a\u5b54\u4ecb\u8d28\u58f0\u5b66\u548c\u591a\u5b54\u5f39\u6027\u6ce2\u3002\u901a\u8fc7\u6bd4\u8f83\u8fd9\u4e24\u79cd\u65b9\u6cd5\uff0c\u53d1\u73b0\u5fc5\u987b\u4f7f\u7528\u201c\u591a\u5b54\u5f39\u6027\u6ce2\u201d\u63a5\u53e3\u6765\u6355\u6349\u591a\u5b54\u5f39\u6027\u6846\u67b6\u7684\u5171\u632f\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("anisotropic_porous_absorber.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
