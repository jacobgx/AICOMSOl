/*
 * icp_torch.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:04 by COMSOL 6.3.0.290. */
public class icp_torch {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Equilibrium_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid");
    model.component("comp1").physics("mf").feature("alf1").selection().all();
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").multiphysics().create("phs1", "EquilibriumDischargeHeatSource", 2);
    model.component("comp1").multiphysics("phs1").set("phsEMHeat_physics", "mf");
    model.component("comp1").multiphysics("phs1").set("phsHeat_physics", "ht");
    model.component("comp1").multiphysics("phs1").selection().all();
    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 2);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mf");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("fstat", "FrequencyStationary");
    model.study("std1").feature("fstat").set("freq", "1000");
    model.study("std1").feature("fstat").set("solnum", "auto");
    model.study("std1").feature("fstat").set("notsolnum", "auto");
    model.study("std1").feature("fstat").set("outputmap", new String[]{});
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("fstat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("fstat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("fstat").setSolveFor("/multiphysics/phs1", true);
    model.study("std1").feature("fstat").setSolveFor("/multiphysics/mhd1", true);
    model.study("std1").feature("fstat").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "300[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("Pext", "11[kW]", "\u7ebf\u5708\u6fc0\u52b1\u529f\u7387");
    model.param().set("f0", "3[MHz]", "\u7ebf\u5708\u6fc0\u52b1\u9891\u7387");
    model.param().set("r_3", "125[mm]", "\u8f74\u5411\u957f\u5ea6\uff1a\u8ba1\u7b97\u57df");
    model.param().set("L_3", "200[mm]", "\u9ad8\u5ea6\uff1a\u8ba1\u7b97\u57df\u548c\u9798\u7ba1");
    model.param().set("d_1", "2[mm]", "\u539a\u5ea6\uff1a\u8f7d\u6d41\u5b50\u7ba1");
    model.param().set("L_0", "50[mm]", "\u9ad8\u5ea6\uff1a\u8f7d\u6d41\u5b50\u7ba1\u548c\u4e2d\u5fc3\u7ba1");
    model.param().set("r_1", "3.7[mm]", "\u5185\u534a\u5f84\uff1a\u8f7d\u6d41\u5b50\u7ba1");
    model.param().set("d_2", "2.2[mm]", "\u539a\u5ea6\uff1a\u4e2d\u5fc3\u7ba1");
    model.param().set("r_2", "18.8[mm]", "\u5185\u534a\u5f84\uff1a\u4e2d\u5fc3\u7ba1");
    model.param().set("d_3", "3.5[mm]", "\u539a\u5ea6\uff1a\u9798\u7ba1");
    model.param().set("r_0", "25[mm]", "\u5185\u534a\u5f84\uff1a\u9798\u7ba1");
    model.param().set("d_c", "6[mm]", "\u76f4\u5f84\uff1a\u7ebf\u5708");
    model.param().set("r_c", "33[mm]", "\u8f74\u5411\u957f\u5ea6\uff1a\u7ebf\u5708\u4e2d\u5fc3");
    model.param().set("L_1", "63[mm]", "\u9ad8\u5ea6\uff1a\u4e0b\u7ebf\u5708\u7684\u4e2d\u5fc3");
    model.param().set("L_2", "121[mm]", "\u9ad8\u5ea6\uff1a\u4e0a\u7ebf\u5708\u7684\u4e2d\u5fc3");
    model.param().set("Q_1", "1[l/min]", "\u6c14\u6d41\uff1a\u8f7d\u6d41\u5b50\u7ba1");
    model.param().set("Q_2", "3[l/min]", "\u6c14\u6d41\uff1a\u4e2d\u5fc3\u7ba1");
    model.param().set("Q_3", "31[l/min]", "\u6c14\u6d41\uff1a\u9798\u7ba1");
    model.param().set("M", "0.04[kg/mole]", "\u6469\u5c14\u8d28\u91cf\uff1a\u6c29");
    model.param().set("mv_stp", "22.4[l/mole]", "stp \u4e0b\u7684\u6469\u5c14\u4f53\u79ef");
    model.param().set("mdot1", "M*Q_1/mv_stp", "\u8d28\u91cf\u6d41\u7387\uff1a\u8f7d\u6d41\u5b50\u7ba1");
    model.param().set("mdot2", "M*Q_2/mv_stp", "\u8d28\u91cf\u6d41\u7387\uff1a\u4e2d\u5fc3\u7ba1");
    model.param().set("mdot3", "M*Q_3/mv_stp", "\u8d28\u91cf\u6d41\u7387\uff1a\u9798\u7ba1");
    model.param().set("rho_stp", "1.91[kg/m^3]", "stp \u4e0b\u7684\u6c29\u5bc6\u5ea6");
    model.param().set("A1", "pi*(r_1)^2", "\u6a2a\u622a\u9762\u79ef\uff1a\u8f7d\u6c14\u6d41");
    model.param().set("A2", "pi*(r_2^2-(r_1+d_1)^2)", "\u6a2a\u622a\u9762\u79ef\uff1a\u4e2d\u5fc3\u6c14\u6d41");
    model.param().set("A3", "pi*(r_0^2-(r_2+d_2)^2)", "\u6a2a\u622a\u9762\u79ef\uff1a\u9798\u7ba1\u6c14\u6d41");
    model.param().set("v1", "mdot1/rho_stp/A1", "\u901f\u5ea6\uff1a\u8f7d\u6c14\u6d41");
    model.param().set("v2", "mdot2/rho_stp/A2", "\u901f\u5ea6\uff1a\u4e2d\u5fc3\u6c14\u6d41");
    model.param().set("v3", "mdot3/rho_stp/A3", "\u901f\u5ea6\uff1a\u9798\u7ba1\u6c14\u6d41");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_3", "L_3"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d_1", "L_0"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r_1", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"d_2", "L_0"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"r_2", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"d_3", "L_3"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"r_0", "0"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "d_c/2");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"r_c", "L_1"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "d_c/2");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"r_c", "(L_1+L_2)/2"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "d_c/2");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"r_c", "L_2"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7b49\u79bb\u5b50\u4f53");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u77f3\u82f1");
    model.component("comp1").selection("sel3").set(2, 3, 4);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u7ebf\u5708");
    model.component("comp1").selection("sel4").set(6, 7, 8);

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
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Quartz");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.99);
    model.component("comp1").material("mat3").set("roughness", 0.02);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "820[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("emissivity", "0.7");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2600[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3[W/(m*K)]", "0", "0", "0", "3[W/(m*K)]", "0", "0", "0", "3[W/(m*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int5", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RadiationHeatTransfer", "RadiationHeatTransfer", "Radiation heat transfer");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").label("Argon (1[atm])");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "rho");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"250", "1.37933"}, 
         {"500", "0.97353"}, 
         {"1.0000E+03", "4.8672E-01"}, 
         {"1.5000E+03", "3.2450E-01"}, 
         {"2.0000E+03", "2.4338E-01"}, 
         {"2.5000E+03", "1.9411E-01"}, 
         {"3.0000E+03", "1.6226E-01"}, 
         {"3.5000E+03", "1.3908E-01"}, 
         {"4.0000E+03", "1.2170E-01"}, 
         {"4.5000E+03", "1.0818E-01"}, 
         {"5.0000E+03", "9.7361E-02"}, 
         {"5.5000E+03", "8.8510E-02"}, 
         {"6.0000E+03", "8.1133E-02"}, 
         {"6.5000E+03", "7.4888E-02"}, 
         {"7.0000E+03", "6.9527E-02"}, 
         {"7.5000E+03", "6.4865E-02"}, 
         {"8.0000E+03", "6.0757E-02"}, 
         {"8.5000E+03", "5.7083E-02"}, 
         {"9.0000E+03", "5.3740E-02"}, 
         {"9.5000E+03", "5.0639E-02"}, 
         {"1.0000E+04", "4.7696E-02"}, 
         {"1.0500E+04", "4.4838E-02"}, 
         {"1.1000E+04", "4.2003E-02"}, 
         {"1.1500E+04", "3.9145E-02"}, 
         {"1.2000E+04", "3.6243E-02"}, 
         {"1.2500E+04", "3.3310E-02"}, 
         {"1.3000E+04", "3.0387E-02"}, 
         {"1.3500E+04", "2.7556E-02"}, 
         {"1.4000E+04", "2.4906E-02"}, 
         {"1.4500E+04", "2.2539E-02"}, 
         {"1.5000E+04", "2.0510E-02"}, 
         {"1.5500E+04", "1.8832E-02"}, 
         {"1.6000E+04", "1.7417E-02"}, 
         {"1.6500E+04", "1.6392E-02"}, 
         {"1.7000E+04", "1.5516E-02"}, 
         {"1.7500E+04", "1.4795E-02"}, 
         {"1.8000E+04", "1.4187E-02"}, 
         {"1.8500E+04", "1.3660E-02"}, 
         {"1.9000E+04", "1.3207E-02"}, 
         {"1.9500E+04", "1.2775E-02"}, 
         {"2.0000E+04", "1.2369E-02"}, 
         {"2.0500E+04", "1.1977E-02"}, 
         {"2.1000E+04", "1.1590E-02"}, 
         {"2.1500E+04", "1.1201E-02"}, 
         {"2.2000E+04", "1.0802E-02"}, 
         {"2.2500E+04", "1.0392E-02"}, 
         {"2.3000E+04", "9.9693E-03"}, 
         {"2.3500E+04", "9.5460E-03"}, 
         {"2.4000E+04", "9.1103E-03"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("funcname", "cp");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"250", "519.5800000000002"}, 
         {"500", "520.33"}, 
         {"1.0000E+03", "5.2041E+02"}, 
         {"1.5000E+03", "5.2036E+02"}, 
         {"2.0000E+03", "5.2034E+02"}, 
         {"2.5000E+03", "5.2033E+02"}, 
         {"3.0000E+03", "5.2033E+02"}, 
         {"3.5000E+03", "5.2033E+02"}, 
         {"4.0000E+03", "5.2033E+02"}, 
         {"4.5000E+03", "5.2034E+02"}, 
         {"5.0000E+03", "5.2045E+02"}, 
         {"5.5000E+03", "5.2095E+02"}, 
         {"6.0000E+03", "5.2276E+02"}, 
         {"6.5000E+03", "5.2801E+02"}, 
         {"7.0000E+03", "5.4091E+02"}, 
         {"7.5000E+03", "5.6859E+02"}, 
         {"8.0000E+03", "6.2133E+02"}, 
         {"8.5000E+03", "7.1515E+02"}, 
         {"9.0000E+03", "8.6785E+02"}, 
         {"9.5000E+03", "1.1027E+03"}, 
         {"1.0000E+04", "1.4577E+03"}, 
         {"1.0500E+04", "1.9469E+03"}, 
         {"1.1000E+04", "2.6087E+03"}, 
         {"1.1500E+04", "3.3854E+03"}, 
         {"1.2100E+04", "4.7392E+03"}, 
         {"1.2600E+04", "6.0073E+03"}, 
         {"1.3200E+04", "7.5907E+03"}, 
         {"1.3800E+04", "8.9539E+03"}, 
         {"1.4300E+04", "9.6220E+03"}, 
         {"1.4800E+04", "9.6348E+03"}, 
         {"1.5300E+04", "8.9748E+03"}, 
         {"1.5800E+04", "7.8353E+03"}, 
         {"1.6300E+04", "6.5129E+03"}, 
         {"1.6800E+04", "5.2621E+03"}, 
         {"1.7300E+04", "4.2265E+03"}, 
         {"1.7800E+04", "3.4503E+03"}, 
         {"1.8300E+04", "2.9240E+03"}, 
         {"1.8800E+04", "2.6221E+03"}, 
         {"1.9400E+04", "2.5240E+03"}, 
         {"1.9900E+04", "2.6606E+03"}, 
         {"2.0400E+04", "2.9976E+03"}, 
         {"2.0900E+04", "3.5472E+03"}, 
         {"2.1400E+04", "4.3186E+03"}, 
         {"2.1900E+04", "5.3062E+03"}, 
         {"2.2400E+04", "6.4791E+03"}, 
         {"2.2900E+04", "7.7741E+03"}, 
         {"2.3500E+04", "9.3495E+03"}, 
         {"2.4000E+04", "1.0553E+04"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"J/kg/K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("funcname", "mu");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"250", "2.1671500000000004E-5"}, 
         {"500", "3.4224E-5"}, 
         {"1.0000E+03", "5.7633E-05"}, 
         {"1.5000E+03", "7.3960E-05"}, 
         {"2.0000E+03", "8.9715E-05"}, 
         {"2.5000E+03", "1.0511E-04"}, 
         {"3.0000E+03", "1.1987E-04"}, 
         {"3.5000E+03", "1.3389E-04"}, 
         {"4.0000E+03", "1.4715E-04"}, 
         {"4.5000E+03", "1.5972E-04"}, 
         {"5.0000E+03", "1.7169E-04"}, 
         {"5.5000E+03", "1.8314E-04"}, 
         {"6.0000E+03", "1.9415E-04"}, 
         {"6.5000E+03", "2.0481E-04"}, 
         {"7.0000E+03", "2.1517E-04"}, 
         {"7.5000E+03", "2.2529E-04"}, 
         {"8.0000E+03", "2.3518E-04"}, 
         {"8.5000E+03", "2.4480E-04"}, 
         {"9.0000E+03", "2.5402E-04"}, 
         {"9.5000E+03", "2.6252E-04"}, 
         {"1.0000E+04", "2.6965E-04"}, 
         {"1.0500E+04", "2.7431E-04"}, 
         {"1.1000E+04", "2.7489E-04"}, 
         {"1.1500E+04", "2.6947E-04"}, 
         {"1.2000E+04", "2.5646E-04"}, 
         {"1.2500E+04", "2.3552E-04"}, 
         {"1.3000E+04", "2.0811E-04"}, 
         {"1.3500E+04", "1.7748E-04"}, 
         {"1.4000E+04", "1.4715E-04"}, 
         {"1.4500E+04", "1.2038E-04"}, 
         {"1.5000E+04", "9.8656E-05"}, 
         {"1.5500E+04", "8.2258E-05"}, 
         {"1.6000E+04", "7.0625E-05"}, 
         {"1.6500E+04", "6.2838E-05"}, 
         {"1.7000E+04", "5.7957E-05"}, 
         {"1.7500E+04", "5.5177E-05"}, 
         {"1.8000E+04", "5.3865E-05"}, 
         {"1.8500E+04", "5.3537E-05"}, 
         {"1.9000E+04", "5.4238E-05"}, 
         {"1.9500E+04", "5.4778E-05"}, 
         {"2.0000E+04", "5.5304E-05"}, 
         {"2.0500E+04", "5.5537E-05"}, 
         {"2.1000E+04", "5.5219E-05"}, 
         {"2.1500E+04", "5.4140E-05"}, 
         {"2.2000E+04", "5.2186E-05"}, 
         {"2.2500E+04", "4.9382E-05"}, 
         {"2.3000E+04", "4.5896E-05"}, 
         {"2.3500E+04", "4.2296E-05"}, 
         {"2.4000E+04", "3.8250E-05"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("fununit", new String[]{"Pa*s"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("funcname", "k");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"250", "0.016914500000000002"}, 
         {"500", "0.026712"}, 
         {"1.0000E+03", "4.4982E-02"}, 
         {"1.5000E+03", "5.7725E-02"}, 
         {"2.0000E+03", "7.0022E-02"}, 
         {"2.5000E+03", "8.2038E-02"}, 
         {"3.0000E+03", "9.3561E-02"}, 
         {"3.5000E+03", "1.0458E-01"}, 
         {"5.1000E+03", "1.3604E-01"}, 
         {"5.6000E+03", "1.4582E-01"}, 
         {"6.1000E+03", "1.5756E-01"}, 
         {"6.6000E+03", "1.7388E-01"}, 
         {"7.2000E+03", "2.0308E-01"}, 
         {"7.7000E+03", "2.3823E-01"}, 
         {"8.2000E+03", "2.8698E-01"}, 
         {"8.7000E+03", "3.5411E-01"}, 
         {"9.2000E+03", "4.4416E-01"}, 
         {"9.7000E+03", "5.6042E-01"}, 
         {"1.0200E+04", "7.0582E-01"}, 
         {"1.0700E+04", "8.8084E-01"}, 
         {"1.1200E+04", "1.0867E+00"}, 
         {"1.1700E+04", "1.3184E+00"}, 
         {"1.2200E+04", "1.5679E+00"}, 
         {"1.2700E+04", "1.8220E+00"}, 
         {"1.3200E+04", "2.0670E+00"}, 
         {"1.3700E+04", "2.2684E+00"}, 
         {"1.4200E+04", "2.4121E+00"}, 
         {"1.4700E+04", "2.4813E+00"}, 
         {"1.5200E+04", "2.4851E+00"}, 
         {"1.5700E+04", "2.4511E+00"}, 
         {"1.6200E+04", "2.4116E+00"}, 
         {"1.6700E+04", "2.3906E+00"}, 
         {"1.7200E+04", "2.3993E+00"}, 
         {"1.7700E+04", "2.4392E+00"}, 
         {"1.8200E+04", "2.5066E+00"}, 
         {"1.8700E+04", "2.5964E+00"}, 
         {"1.9200E+04", "2.7031E+00"}, 
         {"1.9700E+04", "2.8235E+00"}, 
         {"2.0200E+04", "2.9536E+00"}, 
         {"2.0700E+04", "3.0904E+00"}, 
         {"2.1200E+04", "3.2315E+00"}, 
         {"2.1700E+04", "3.3747E+00"}, 
         {"2.2200E+04", "3.5185E+00"}, 
         {"2.2700E+04", "3.6616E+00"}, 
         {"2.3200E+04", "3.8034E+00"}, 
         {"2.3700E+04", "3.9456E+00"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int4")
         .set("fununit", new String[]{"W/m/K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int4").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").label("Interpolation 5");
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").set("funcname", "sigma");
    model.component("comp1").material("mat4").propertyGroup("def").func("int5")
         .set("table", new String[][]{{"250", "4.3719000000000004E-23"}, 
         {"500", "3.0784E-23"}, 
         {"1.0000E+03", "3.9216E-23"}, 
         {"1.5000E+03", "1.7575E-18"}, 
         {"2.0000E+03", "1.6592E-11"}, 
         {"2.5000E+03", "2.3956E-07"}, 
         {"3.0000E+03", "5.4577E-05"}, 
         {"3.5000E+03", "2.9073E-03"}, 
         {"4.0000E+03", "6.2885E-02"}, 
         {"4.5000E+03", "7.1816E-01"}, 
         {"5.0000E+03", "5.1615E+00"}, 
         {"5.5000E+03", "2.3647E+01"}, 
         {"6.0000E+03", "7.6821E+01"}, 
         {"6.5000E+03", "1.8290E+02"}, 
         {"7.0000E+03", "3.5808E+02"}, 
         {"7.5000E+03", "6.2440E+02"}, 
         {"8.0000E+03", "9.9034E+02"}, 
         {"8.5000E+03", "1.4378E+03"}, 
         {"9.0000E+03", "1.9358E+03"}, 
         {"9.5000E+03", "2.4584E+03"}, 
         {"1.0000E+04", "2.9900E+03"}, 
         {"1.0600E+04", "3.6295E+03"}, 
         {"1.1100E+04", "4.1603E+03"}, 
         {"1.1700E+04", "4.7920E+03"}, 
         {"1.2200E+04", "5.3120E+03"}, 
         {"1.2700E+04", "5.8233E+03"}, 
         {"1.3200E+04", "6.3220E+03"}, 
         {"1.3700E+04", "6.8047E+03"}, 
         {"1.4200E+04", "7.2664E+03"}, 
         {"1.4700E+04", "7.7047E+03"}, 
         {"1.5200E+04", "8.1189E+03"}, 
         {"1.5700E+04", "8.5107E+03"}, 
         {"1.6200E+04", "8.8829E+03"}, 
         {"1.6700E+04", "9.2394E+03"}, 
         {"1.7200E+04", "9.5831E+03"}, 
         {"1.7700E+04", "9.9164E+03"}, 
         {"1.8200E+04", "1.0240E+04"}, 
         {"1.8700E+04", "1.0551E+04"}, 
         {"1.9200E+04", "1.0845E+04"}, 
         {"1.9700E+04", "1.1122E+04"}, 
         {"2.0200E+04", "1.1371E+04"}, 
         {"2.0700E+04", "1.1582E+04"}, 
         {"2.1200E+04", "1.1746E+04"}, 
         {"2.1700E+04", "1.1856E+04"}, 
         {"2.2200E+04", "1.1907E+04"}, 
         {"2.2700E+04", "1.1905E+04"}, 
         {"2.3200E+04", "1.1860E+04"}, 
         {"2.3700E+04", "1.1783E+04"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int5").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "cp(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("dynamicviscosity", "mu(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))"});
    model.component("comp1").material("mat4").propertyGroup("def").set("ratioofspecificheat", "1.66");
    model.component("comp1").material("mat4").propertyGroup("def").set("sigma_min", "1[S/m]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("sigma_min", "");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer")
         .label("Radiation heat transfer");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("funcname", "Qrad");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"4.8251E+03", "1.3345E+01"}, 
         {"5.0578E+03", "3.7829E+01"}, 
         {"5.2777E+03", "1.1457E+02"}, 
         {"5.5109E+03", "3.4681E+02"}, 
         {"5.6892E+03", "8.0898E+02"}, 
         {"5.9350E+03", "2.2180E+03"}, 
         {"6.2216E+03", "6.9239E+03"}, 
         {"6.4801E+03", "1.7194E+04"}, 
         {"6.8342E+03", "6.3086E+04"}, 
         {"7.2679E+03", "2.2330E+05"}, 
         {"7.7421E+03", "8.7089E+05"}, 
         {"8.2552E+03", "2.8778E+06"}, 
         {"8.8073E+03", "8.3262E+06"}, 
         {"9.2511E+03", "1.8001E+07"}, 
         {"9.6950E+03", "3.8916E+07"}, 
         {"1.0179E+04", "8.9704E+07"}, 
         {"1.0756E+04", "1.8670E+08"}, 
         {"1.1319E+04", "4.0177E+08"}, 
         {"1.1868E+04", "7.3404E+08"}, 
         {"1.2532E+04", "1.3275E+09"}, 
         {"1.3362E+04", "2.3406E+09"}, 
         {"1.4369E+04", "4.0321E+09"}, 
         {"1.6925E+04", "5.5210E+09"}, 
         {"2.0956E+04", "1.4343E+10"}});
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("fununit", new String[]{"W/m^3"});
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").set("Qrad", "Qrad(T)");
    model.component("comp1").material("mat4").propertyGroup("RadiationHeatTransfer").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel4");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat4").selection().named("sel2");

    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel4");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Power");
    model.component("comp1").physics("mf").feature("coil1").set("PCoil", "Pext");
    model.component("comp1").physics("ht").selection().set(1, 4);
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").selection().set(4);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 6000);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2, 8, 13, 15, 17);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics("spf").selection().named("sel2");
    model.component("comp1").physics("spf").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "v1");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(8);
    model.component("comp1").physics("spf").feature("inl2").set("U0in", "v2");
    model.component("comp1").physics("spf").create("inl3", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl3").selection().set(13);
    model.component("comp1").physics("spf").feature("inl3").set("U0in", "v3");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3);
    model.component("comp1").physics("spf").feature("out1").set("SuppressBackflow", false);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature().move("edg1", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2, 8, 13);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature().move("bl2", 1);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set(6, 7, 8);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection()
         .set(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "8[um]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("fstat").set("freq", "f0");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", "1e-4");
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("rstep", 1.2);
    model.sol("sol1").feature("s1").feature("fc1").set("minsteprecovery", 0.1);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 200);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.U");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "nitf1.T");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg7").feature().create("surf2", "Surface");
    model.result("pg7").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("solutionparams", "parent");
    model.result("pg7").feature("surf2").set("expr", "nitf1.T");
    model.result("pg7").feature("surf2").set("smooth", "internal");
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("data", "parent");
    model.result("pg7").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf2").feature("sel1").selection().set(4);
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature().create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("solutionparams", "parent");
    model.result("pg7").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg7").feature("arws1").set("xnumber", 30);
    model.result("pg7").feature("arws1").set("ynumber", 30);
    model.result("pg7").feature("arws1").set("arrowtype", "cone");
    model.result("pg7").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("data", "parent");
    model.result("pg7").feature("arws1").feature().create("col1", "Color");
    model.result("pg7").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg7").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg7").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg7").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u4e8c\u7ef4\u6e29\u5ea6");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "T");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().move("pg8", 6);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u5bfc\u7387");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "mf.sigmarr");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().move("pg9", 7);
    model.result("pg8").run();
    model.result("pg4").run();
    model.result("pg9").run();
    model.result("pg1").run();
    model.result("pg6").run();

    model.title("\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53 (ICP) \u70ac");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5927\u6c14\u538b\u4e0b\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u70ac\u7684\u7535\u7279\u6027\u548c\u70ed\u7279\u6027\uff0c\u5176\u4e2d\u5047\u8bbe\u5728\u5c40\u90e8\u70ed\u529b\u5b66\u5e73\u8861\u6761\u4ef6\u4e0b\u653e\u7535\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("icp_torch.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
