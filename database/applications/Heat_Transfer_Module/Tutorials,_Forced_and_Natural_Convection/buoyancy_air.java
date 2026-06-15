/*
 * buoyancy_air.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:33 by COMSOL 6.3.0.290. */
public class buoyancy_air {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("L", "10[cm]");
    model.param().descr("L", "\u6b63\u65b9\u5f62\u8fb9\u957f");
    model.param().set("W", "L/2");
    model.param().descr("W", "\u4f53\u79ef\u539a\u5ea6");
    model.param().set("DeltaT", "10[K]");
    model.param().descr("DeltaT", "\u6e29\u5dee");
    model.param().set("Tc", "283.15[K]");
    model.param().descr("Tc", "\u4f4e\u6e29");
    model.param().set("Th", "Tc+DeltaT");
    model.param().descr("Th", "\u9ad8\u6e29");
    model.param().set("T_avg", "(Tc+Th)/2");
    model.param().descr("T_avg", "\u5e73\u5747\u6e29\u5ea6\u8fd1\u4f3c\u503c");
    model.param().set("p_ref", "1[atm]");
    model.param().descr("p_ref", "\u53c2\u8003\u538b\u529b");
    model.param().set("p0", "0[Pa]");
    model.param().descr("p0", "\u76f8\u5bf9\u538b\u529b\u7ea6\u675f");
    model.param().set("dt_range_start", "-2");
    model.param()
         .descr("dt_range_start", "\u5bf9 DeltaT \u6267\u884c\u53c2\u6570\u5316\u626b\u63cf\u7684\u8303\u56f4\u8d77\u70b9\uff08\u5bf9\u503c\u6c42 log10\uff0c\u5355\u4f4d\u4e3a K\uff09");
    model.param().set("dt_range_stop", "1");
    model.param()
         .descr("dt_range_stop", "\u5bf9 DeltaT \u6267\u884c\u53c2\u6570\u5316\u626b\u63cf\u7684\u8303\u56f4\u7ec8\u70b9\uff08\u5bf9\u503c\u6c42 log10\uff0c\u5355\u4f4d\u4e3a K\uff09");
    model.param().set("rho", "1.225[kg/m^3]");
    model.param().descr("rho", "T_avg \u65f6 1 \u4e2a\u5927\u6c14\u538b\u4e0b\u7684\u5bc6\u5ea6");
    model.param().set("mu", "1.789e-5[N*s/m^2]");
    model.param().descr("mu", "T_avg \u65f6\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("k", "0.02537[W/(m*K)]");
    model.param().descr("k", "T_avg \u65f6\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cp", "1.005[kJ/(kg*K)]");
    model.param().descr("Cp", "T_avg \u65f6\u7684\u70ed\u5bb9");
    model.param().set("alpha", "1/T_avg");
    model.param().descr("alpha", "T_avg \u65f6\u7684\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("U0", "sqrt(g_const*alpha*DeltaT*L)");
    model.param().descr("U0", "\u6d6e\u529b\u5f15\u8d77\u7684\u5178\u578b\u901f\u5ea6");
    model.param().set("U1", "U0/sqrt(Pr)");
    model.param().descr("U1", "\u6d6e\u529b\u5f15\u8d77\u7684\u5178\u578b\u901f\u5ea6");
    model.param().set("Pr", "mu*Cp/k");
    model.param().descr("Pr", "\u666e\u6717\u7279\u6570");
    model.param().set("Gr", "(U0*rho*L/mu)^2");
    model.param().descr("Gr", "\u683c\u62c9\u65af\u970d\u592b\u6570");
    model.param().set("Ra", "Pr*Gr");
    model.param().descr("Ra", "\u745e\u5229\u6570");
    model.param().set("eps_t", "L/(Pr*Ra)^0.25");
    model.param().descr("eps_t", "\u70ed\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("eps_s", "eps_t*sqrt(Pr)");
    model.param().descr("eps_s", "\u526a\u5207\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L");
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("rref", new String[]{"0", "L", "0"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "p0");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("spf").feature("prpc1").set("p0", "p0");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_avg");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_avg");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Tc");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(4);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Th");

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "DeltaT", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "10^{range(dt_range_start,1,dt_range_stop)}", 0);
    model.study("std1").label("\u4e8c\u7ef4\u7814\u7a76");

    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("PseudoTimeSetting", "Off");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "nitf1.T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg4").feature("arws1").set("xnumber", 30);
    model.result("pg4").feature("arws1").set("ynumber", 30);
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg4").feature("arws1").feature().create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("xnumber", 20);
    model.result("pg4").feature("arws1").set("ynumber", 20);
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "L/5", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "L/2", 1, 1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8fb9\u754c\u5c42\u7684\u6e29\u5ea6");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{4}, 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "T");
    model.result("pg5").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8fb9\u754c\u5c42\u901f\u5ea6");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{4}, 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("spf2", "LaminarFlow", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/spf2", false);

    model.component("comp2").physics("spf2").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp2").physics().create("ht2", "HeatTransferInFluids", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").physics("ht2").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp2").multiphysics().create("nitf2", "NonIsothermalFlow", 3);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf2", false);

    model.component("comp2").multiphysics("nitf2").set("Fluid_physics", "spf2");
    model.component("comp2").multiphysics("nitf2").set("Heat_physics", "ht2");

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf2", true);

    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"L", "L/2", "L"});
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").label("Air");
    model.component("comp2").material("mat2").set("family", "air");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat2").materialType("nonSolid");

    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp2").physics("spf2").prop("PhysicalModelProperty").set("rref", new String[]{"0", "0", "L"});
    model.component("comp2").physics("spf2").feature("init1").set("p_init", "p0");
    model.component("comp2").physics("spf2").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp2").physics("spf2").feature("prpc1").selection().set(4);
    model.component("comp2").physics("spf2").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("spf2").feature("sym1").selection().set(2);
    model.component("comp2").physics("ht2").prop("PhysicalModelProperty").set("Tref", "T_avg");
    model.component("comp2").physics("ht2").feature("init1").set("Tinit", "T_avg");
    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp1").selection().set(1);
    model.component("comp2").physics("ht2").feature("temp1").set("T0", "Tc");
    model.component("comp2").physics("ht2").create("temp2", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp2").selection().set(6);
    model.component("comp2").physics("ht2").feature("temp2").set("T0", "Th");
    model.component("comp2").physics("ht2").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("ht2").feature("sym1").selection().set(2);

    model.component("comp2").multiphysics("nitf2").set("BoussinesqApproximation", true);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(2);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 3, 5, 9);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemcount", 16);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("elemcount", 8);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().set(1, 3, 4, 5, 6);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "3[mm]/5");
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "DeltaT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "10^{range(dt_range_start,1,dt_range_stop)}", 0);
    model.study("std2").feature("stat").set("pcontinuationmode", "no");
    model.study("std2").feature("stat").set("preusesol", "yes");
    model.study("std2").label("\u4e09\u7ef4\u7814\u7a76");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset3").set("geom", "geom2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u901f\u5ea6 (spf2)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("slc1", "Slice");
    model.result("pg7").feature("slc1").label("\u5207\u9762");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("smooth", "internal");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset3");
    model.result().dataset("surf1").selection().geom("geom2", 2);
    model.result().dataset("surf1").selection().set(1, 3, 4, 5, 6);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u538b\u529b (spf2)");
    model.result("pg8").set("data", "surf1");
    model.result("pg8").setIndex("looplevel", 4, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "p2");
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6e29\u5ea6 (ht2)");
    model.result("pg9").feature().create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("solutionparams", "parent");
    model.result("pg9").feature("vol1").set("expr", "T2");
    model.result("pg9").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("vol1").set("smooth", "internal");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("data", "parent");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2)");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u58c1\u6e29");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "ht2.Tvar");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection().geom("geom2", 2);
    model.result("pg10").feature("surf1").feature("sel1").selection().set(1, 3, 4, 5, 6);
    model.result("pg10").feature().create("arwv1", "ArrowVolume");
    model.result("pg10").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg10").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg10").feature("arwv1").set("solutionparams", "parent");
    model.result("pg10").feature("arwv1").set("expr", new String[]{"nitf2.ux", "nitf2.uy", "nitf2.uz"});
    model.result("pg10").feature("arwv1").set("xnumber", 30);
    model.result("pg10").feature("arwv1").set("ynumber", 30);
    model.result("pg10").feature("arwv1").set("znumber", 30);
    model.result("pg10").feature("arwv1").set("arrowtype", "cone");
    model.result("pg10").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg10").feature("arwv1").set("data", "parent");
    model.result("pg10").feature("arwv1").feature().create("col1", "Color");
    model.result("pg10").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg10").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg10").feature("arwv1").feature("filt1").set("expr", "spf2.U>nitf2.Uave");
    model.result("pg7").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result("pg7").run();
    model.result("pg7").feature("slc1").set("resolution", "fine");
    model.result("pg7").run();
    model.result("pg7").set("data", "mir1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("data", "mir1");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").set("data", "mir1");
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").set("data", "mir1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("surf1").create("tran1", "Transparency");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("arwv1").set("xnumber", 12);
    model.result("pg10").feature("arwv1").set("ynumber", 12);
    model.result("pg10").feature("arwv1").set("znumber", 12);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u901f\u5ea6\uff0c\u524d\u5e73\u9762");
    model.result("pg11").set("data", "mir1");
    model.result("pg11").create("slc1", "Slice");
    model.result("pg11").feature("slc1").set("quickplane", "zx");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u6e29\u5ea6\uff0c\u901f\u5ea6");
    model.result("pg12").set("data", "mir1");
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "T2");
    model.result("pg12").feature("slc1").set("quickxmethod", "coord");
    model.result("pg12").feature("slc1").set("quickx", "L/2");
    model.result("pg12").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg12").run();
    model.result("pg12").create("arwv1", "ArrowVolume");
    model.result("pg12").feature("arwv1").set("expr", new String[]{"0", "v2", "w2"});
    model.result("pg12").feature("arwv1").set("xnumber", 1);
    model.result("pg12").feature("arwv1").set("ynumber", 15);
    model.result("pg12").feature("arwv1").set("znumber", 15);
    model.result("pg12").feature("arwv1").set("color", "black");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("slc2", "Slice");
    model.result("pg12").feature("slc2").set("quickplane", "zx");
    model.result("pg12").feature("slc2").set("quickymethod", "coord");
    model.result("pg12").feature("slc2").set("expr", "T2");
    model.result("pg12").feature("slc2").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("slc2").set("inheritplot", "slc1");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("arwv2", "ArrowVolume");
    model.result("pg12").feature("arwv2").set("color", "black");
    model.result("pg12").run();
    model.result("pg12").run();

    model.title("\u7a7a\u6c14\u4e2d\u7684\u6d6e\u529b\u6d41");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u4ee5\u4e24\u5757\u7ad6\u76f4\u677f\u4e3a\u8fb9\u754c\u7684\u5145\u6ee1\u7a7a\u6c14\u7684\u8154\u4e2d\u81ea\u7136\u5bf9\u6d41\u7684\u7a33\u6001\u72b6\u6001\u3002\u4e3a\u4ea7\u751f\u6d6e\u529b\u6d41\uff0c\u5c06\u677f\u52a0\u70ed\u5230\u4e0d\u540c\u7684\u6e29\u5ea6\uff0c\u6e29\u5ea6\u8bbe\u7f6e\u5728\u4e00\u5b9a\u8303\u56f4\u5185\uff0c\u4ee5\u4fdd\u6301\u5c42\u6d41\u72b6\u6001\u3002\u672c\u4f8b\u5728\u4e8c\u7ef4\u548c\u4e09\u7ef4\u51e0\u4f55\u4e2d\u8fd0\u884c\u4eff\u771f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("buoyancy_air.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
