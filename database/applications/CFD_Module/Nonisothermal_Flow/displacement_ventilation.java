/*
 * displacement_ventilation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class displacement_ventilation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Nonisothermal_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("H", "3[m]");
    model.param().descr("H", "\u8bd5\u9a8c\u7bb1\u9ad8\u5ea6");
    model.param().set("D", "2.5[m]");
    model.param().descr("D", "\u8bd5\u9a8c\u7bb1\u6df1\u5ea6");
    model.param().set("W", "3.65[m]");
    model.param().descr("W", "\u8bd5\u9a8c\u7bb1\u5bbd\u5ea6");
    model.param().set("Hd", "0.5[m]");
    model.param().descr("Hd", "\u6269\u6563\u5668\u5165\u53e3\u9ad8\u5ea6");
    model.param().set("Ad", "1.7[m^2]");
    model.param().descr("Ad", "\u6269\u6563\u5668\u5165\u53e3\u9762\u79ef");
    model.param().set("As", "0.0324[m^2]");
    model.param().descr("As", "\u6e90\u5165\u53e3\u9762\u79ef");
    model.param().set("Ao", "0.04[m^2]");
    model.param().descr("Ao", "\u51fa\u53e3\u9762\u79ef");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Ms", "0.028[m^3/s]");
    model.component("comp1").variable("var1").descr("Ms", "\u6e90\u5165\u53e3\u7684\u4f53\u79ef\u6d41\u91cf");
    model.component("comp1").variable("var1").set("Md", "0.051[m^3/s]");
    model.component("comp1").variable("var1")
         .descr("Md", "\u6269\u6563\u5668\u5165\u53e3\u7684\u4f53\u79ef\u6d41\u91cf");
    model.component("comp1").variable("var1").set("Us", "Ms/As");
    model.component("comp1").variable("var1").descr("Us", "\u6e90\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("Ud", "Md/Ad");
    model.component("comp1").variable("var1").descr("Ud", "\u6269\u6563\u5668\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("Tdiff", "21[degC]");
    model.component("comp1").variable("var1").descr("Tdiff", "\u6269\u6563\u5668\u7a7a\u6c14\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("Tsource", "45[degC]");
    model.component("comp1").variable("var1").descr("Tsource", "\u6e90\u7a7a\u6c14\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("Tout", "17[degC]");
    model.component("comp1").variable("var1").descr("Tout", "\u5916\u90e8\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"D", "W", "2*H"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-W/2", "-H"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"0.05[m]", "1", "2*Hd"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "Ad/Hd", 1);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-0.05[m]", "-Ad/Hd/2", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-Hd", 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "blk2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"4[m]", "4[m]", "4[m]"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-1[m]", "-2[m]", "-4[m]"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"3[m]", "2[m]", "5[m]"});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"-0.2[m]", "-2[m]", "0"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "-1[m]", 2);
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk4");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"sqrt(As)", "sqrt(As)/2", "1"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "H/2", 2);
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"D/2-sqrt(As)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "-0.05[m]", 2);
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new String[]{"sqrt(Ao)", "sqrt(Ao)/2", "1"});
    model.component("comp1").geom("geom1").feature("blk6").setIndex("size", "0.45[m]", 2);
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new String[]{"D/2-sqrt(Ao)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk6").setIndex("pos", "H-0.3[m]", 2);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 2, 5);
    model.component("comp1").geom("geom1").run("mcd1");

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("rref", new String[]{"0", "0", "H+0.15[m]"});
    model.component("comp1").physics("spf").feature("grav1").set("buoyancyInducedTurbulence", true);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(13);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "Us");
    model.component("comp1").physics("spf").feature("inl1").set("IT_list", "upper_end_fully_turbulent");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().set(1);
    model.component("comp1").physics("spf").feature("inl2").set("U0in", "Ud");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(10);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(2);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(2);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Tdiff");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp2").selection().set(13);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Tsource");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(10);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "0.4[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Tout");
    model.component("comp1").physics("ht").feature("hf1").selection().set(6, 7, 8, 17);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.015);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(9, 11, 12, 14, 15, 16);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 0.015);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").run();

    model.result().setOnlyPlotWhenRequested(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "T");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u58c1\u6e29");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17);
    model.result("pg5").feature().create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("solutionparams", "parent");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg5").feature("arwv1").set("xnumber", 30);
    model.result("pg5").feature("arwv1").set("ynumber", 30);
    model.result("pg5").feature("arwv1").set("znumber", 30);
    model.result("pg5").feature("arwv1").set("arrowtype", "cone");
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("data", "parent");
    model.result("pg5").feature("arwv1").feature().create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("data", "dset1");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.07);
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "T");
    model.result("pg3").feature("str1").feature("col1").set("descr", "\u6e29\u5ea6");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "ThermalDark");
    model.result("pg3").feature("str1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("str1").feature("col1").set("rangecolormin", 293);
    model.result("pg3").feature("str1").feature("col1").set("rangecolormax", 300);
    model.result("pg3").feature("str1").set("titletype", "manual");
    model.result("pg3").feature("str1").set("title", "\u6309\u6e29\u5ea6\u7740\u8272\u7684\u6d41\u7ebf");
    model.result("pg3").label("\u6d41\u7ebf");
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg6").feature().create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("solutionparams", "parent");
    model.result("pg6").feature("iso1").set("expr", "T");
    model.result("pg6").feature("iso1").set("number", 10);
    model.result("pg6").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("iso1").set("smooth", "internal");
    model.result("pg6").feature("iso1").set("showsolutionparams", "on");
    model.result("pg6").feature("iso1").set("data", "parent");
    model.result("pg6").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg6").set("view", "view2");
    model.result("pg6").feature("iso1").set("unit", "degC");
    model.result("pg6").feature("iso1").set("levelmethod", "levels");
    model.result("pg6").feature("iso1").set("levels", "22 23 24 25 26");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("displacement_ventilation_exp.txt");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("source", "table");
    model.result("pg7").feature("tblp1").set("table", "tbl1");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("xaxisdata", 2);
    model.result("pg7").feature("tblp1").set("linestyle", "none");
    model.result("pg7").feature("tblp1").set("linemarker", "circle");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c\u503c", 0);
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "D/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "D/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H", 1, 2);
    model.result("pg7").set("data", "cln1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "z");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "T");
    model.result("pg7").feature("lngr1").set("xdataunit", "degC");
    model.result("pg7").feature("lngr1").set("linewidth", 3);
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u6a21\u578b\u503c", 0);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "T [degC]");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "z [m]");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 46);
    model.result("pg7").set("ymin", 0);
    model.result("pg7").set("ymax", 3);
    model.result("pg7").run();

    model.title("\u7f6e\u6362\u901a\u98ce");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u4e00\u4e2a\u7f6e\u6362\u901a\u98ce\u7cfb\u7edf\u7684\u6027\u80fd\u3002\u4f7f\u7528\u201c\u975e\u7b49\u6e29\u6e4d\u6d41\uff0ck-\u03c9 \u6a21\u578b\u201d\u63a5\u53e3\u6a21\u62df\u6d41\u4f53\u7684\u6d41\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("displacement_ventilation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
