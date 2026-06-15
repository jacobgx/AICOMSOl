/*
 * district_heating_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:47 by COMSOL 6.3.0.290. */
public class district_heating_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("nipfl", "NonisothermalPipeFlow", "geom1");
    model.component("comp1").physics().create("ce", "CoefficientFormEdgePDE", "geom1");
    model.component("comp1").physics("ce").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ce").prop("Units").set("DependentVariableQuantity", "power");
    model.component("comp1").physics("ce").field("dimensionless").field("P");
    model.component("comp1").physics("ce").field("dimensionless").component(new String[]{"P"});
    model.component("comp1").physics("ce").prop("Units").set("SourceTermQuantity", "power");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/nipfl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ce", true);

    model.component("comp1").geom("geom1").insertFile("district_heating_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel3");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("T1", "70[degC]", "\u5165\u53e3\u6e29\u5ea6");
    model.param("par2").set("T2", "65[degC]", "\u5165\u53e3\u6e29\u5ea6");
    model.param("par2").set("dp1", "10[kPa]", "\u5165\u53e3\u538b\u529b");
    model.param("par2").set("dp2", "dp1", "\u5165\u53e3\u538b\u529b");
    model.param("par2").set("dp1c", "0", "\u5165\u53e3\u63a7\u5236");
    model.param("par2").set("dp2c", "0", "\u5165\u53e3\u63a7\u5236");
    model.param("par2").set("Tout", "-7[degC]", "\u5916\u90e8\u6e29\u5ea6");
    model.param("par2").set("pExp", "10", "\u7ea6\u675f\u805a\u96c6\u53c2\u6570");
    model.param("par2").set("consumerPower", "5[kW]", "\u6d88\u8017\u529f\u7387");
    model.param("par2").set("roomT", "20[degC]", "\u6d88\u8017\u88c5\u7f6e\u6e29\u5ea6");
    model.param("par2").set("pumpWeight", "1e3[1/W]", "\u76ee\u6807\u91cd\u91cf [EUR/W]");
    model.param("par2").set("kRoom", "10[W/K/m]", "\u6d88\u8017\u88c5\u7f6e\u70ed\u8026\u5408");
    model.param("par2").set("kOut", "kRoom/1e2", "\u5916\u90e8\u70ed\u8026\u5408");
    model.param("par2").set("dInit", "20[cm]", "\u521d\u59cb\u7f51\u7edc\u76f4\u5f84");
    model.param("par2").set("dInit2", "0.5*dInit", "\u521d\u59cb\u6d88\u8017\u88c5\u7f6e\u76f4\u5f84");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").common().create("cvf1", "ControlVariableField");
    model.component("comp1").common("cvf1").label("\u7ba1");
    model.component("comp1").common("cvf1").set("name", "pipeControl");
    model.component("comp1").common("cvf1").selection().geom("geom1", 1);
    model.component("comp1").common("cvf1").selection().named("geom1_boxsel1");
    model.component("comp1").common("cvf1").set("shapeFunctionType", "constantOverGeometricEntities");
    model.component("comp1").common("cvf1").set("useBounds", true);
    model.component("comp1").common("cvf1").set("lbound", "log(0.01)");
    model.component("comp1").common("cvf1").set("ubound", "log(2)");
    model.component("comp1").common().create("cvf2", "ControlVariableField");
    model.component("comp1").common("cvf2").label("\u65c1\u8def");
    model.component("comp1").common("cvf2").set("name", "bypass");
    model.component("comp1").common("cvf2").selection().geom("geom1", 0);
    model.component("comp1").common("cvf2").selection().named("geom1_boxsel3");
    model.component("comp1").common("cvf2").set("shapeFunctionType", "shdisc");
    model.component("comp1").common("cvf2").set("order", "0");
    model.component("comp1").common("cvf2").set("useBounds", true);
    model.component("comp1").common("cvf2").set("lbound", "log(0.01)");
    model.component("comp1").common("cvf2").set("ubound", "log(10)");
    model.component("comp1").common().duplicate("cvf3", "cvf2");
    model.component("comp1").common("cvf3").label("\u7528\u6237");
    model.component("comp1").common("cvf3").set("name", "consumers");
    model.component("comp1").common("cvf3").set("lbound", "log(0.1)");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u6240\u6709\u7ba1");
    model.component("comp1").cpl("intop1").set("opname", "intopAll");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").label("\u70ed\u7ba1");
    model.component("comp1").cpl("intop2").set("opname", "intopHot");
    model.component("comp1").cpl("intop2").selection().named("geom1_boxsel1");
    model.component("comp1").cpl().duplicate("intop3", "intop2");
    model.component("comp1").cpl("intop3").label("\u7528\u6237");
    model.component("comp1").cpl("intop3").set("opname", "intopConsumers");
    model.component("comp1").cpl("intop3").selection().named("geom1_unisel2");
    model.component("comp1").cpl().duplicate("intop4", "intop3");
    model.component("comp1").cpl("intop4").label("\u5165\u53e3 1");
    model.component("comp1").cpl("intop4").set("opname", "intopInlet1");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop4").selection().named("geom1_ballsel1");
    model.component("comp1").cpl().duplicate("intop5", "intop4");
    model.component("comp1").cpl("intop5").label("\u51fa\u53e3 1");
    model.component("comp1").cpl("intop5").set("opname", "intopOutlet1");
    model.component("comp1").cpl("intop5").selection().named("geom1_ballsel2");
    model.component("comp1").cpl().duplicate("intop6", "intop5");
    model.component("comp1").cpl("intop6").label("\u51fa\u53e3 2");
    model.component("comp1").cpl("intop6").selection().named("geom1_ballsel3");
    model.component("comp1").cpl().duplicate("intop7", "intop6");
    model.component("comp1").cpl("intop7").label("\u5165\u53e3 2");
    model.component("comp1").cpl("intop7").set("opname", "intopInlet2");
    model.component("comp1").cpl("intop7").selection().named("geom1_ballsel4");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u7528\u6237\u5e73\u5747\u503c");
    model.component("comp1").cpl("aveop1").set("opname", "aveopConsumer");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_unisel2");
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").label("\u7528\u6237\u6700\u4f4e\u503c");
    model.component("comp1").cpl("minop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("minop1").selection().named("geom1_boxsel3");
    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().named("geom1_boxsel1");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"x", "y", "0"});
    model.component("comp1").cpl().duplicate("genext2", "genext1");
    model.component("comp1").cpl("genext2").selection().geom("geom1", 0);
    model.component("comp1").cpl("genext2").selection().named("geom1_boxsel3");

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").set("type", "cpl");
    model.nodeGroup("grp1").add("cpl", "intop1");
    model.nodeGroup("grp1").add("cpl", "intop2");
    model.nodeGroup("grp1").add("cpl", "intop3");
    model.nodeGroup("grp1").add("cpl", "intop4");
    model.nodeGroup("grp1").add("cpl", "intop5");
    model.nodeGroup("grp1").add("cpl", "intop6");
    model.nodeGroup("grp1").add("cpl", "intop7");
    model.nodeGroup("grp1").add("cpl", "aveop1");
    model.nodeGroup("grp1").add("cpl", "minop1");
    model.nodeGroup("grp1").add("cpl", "genext1");
    model.nodeGroup("grp1").add("cpl", "genext2");
    model.nodeGroup("grp1").label("\u8fd0\u7b97\u7b26");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u7ba1\u8d39\u7528");
    model.component("comp1").func("an1").set("funcname", "pipeCost");
    model.component("comp1").func("an1").set("expr", "2202+(2922-2202)*(x-0.032)/(0.4-0.032)");
    model.component("comp1").func("an1").set("fununit", "1/m");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u70ed\u7ba1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("geom1_boxsel1");
    model.component("comp1").variable("var1").set("qHeat", "kOut*(Tout-T)");
    model.component("comp1").variable("var1").descr("qHeat", "\u70ed\u8026\u5408");
    model.component("comp1").variable("var1").set("pipeD", "dInit*exp(pipeControl)");
    model.component("comp1").variable("var1").descr("pipeD", "\u7ba1\u5f84");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u51b7\u7ba1");
    model.component("comp1").variable("var2").selection().named("geom1_boxsel2");
    model.component("comp1").variable("var2").set("pipeD", "genext1(pipeD)");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").label("\u7528\u6237");
    model.component("comp1").variable("var3").selection().named("geom1_unisel2");
    model.component("comp1").variable("var3").set("qHeat", "exp(genext2(consumers))*kRoom*(roomT-T)");
    model.component("comp1").variable("var3").set("pipeD", "exp(genext2(bypass))*dInit2");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").label("\u5165\u53e3/\u51fa\u53e3");
    model.component("comp1").variable("var4").selection().named("geom1_unisel3");
    model.component("comp1").variable("var4").set("qHeat", "kOut*(Tout-T)");
    model.component("comp1").variable("var4").set("pipeD", "dInit");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("\u76ee\u6807");
    model.component("comp1").variable("var5")
         .set("constr", "log(aveopConsumer(exp(pExp*(1+P/consumerPower)^2)))/pExp");
    model.component("comp1").variable("var5").descr("constr", "\u7ea6\u675f");
    model.component("comp1").variable("var5").set("pipePrice", "2*intopHot(pipeCost(pipeD))");
    model.component("comp1").variable("var5").descr("pipePrice", "\u7ba1\u4ef7\u683c [\u6b27\u5143]");
    model.component("comp1").variable("var5").set("power1", "intopInlet1((p-1[atm])*nipfl.Qv)");
    model.component("comp1").variable("var5").descr("power1", "\u6cf5\u529f\u7387");
    model.component("comp1").variable("var5").set("power2", "intopInlet2((p-1[atm])*nipfl.Qv)");
    model.component("comp1").variable("var5").descr("power2", "\u6cf5\u529f\u7387");
    model.component("comp1").variable("var5").set("pumpPrice", "pumpWeight*(power1+power2)");
    model.component("comp1").variable("var5").descr("pumpPrice", "\u6cf5\u4ef7\u683c [\u6b27\u5143]");
    model.component("comp1").variable("var5").set("obj", "pipePrice+pumpPrice");
    model.component("comp1").variable("var5").descr("obj", "\u76ee\u6807\u51fd\u6570");
    model.component("comp1").variable("var5").set("flow1in", "intopInlet1(nipfl.Qm)");
    model.component("comp1").variable("var5").descr("flow1in", "\u8d28\u91cf\u6d41\u7387\uff0c\u5165\u53e3 1");
    model.component("comp1").variable("var5").set("flow2in", "intopInlet2(nipfl.Qm)");
    model.component("comp1").variable("var5").descr("flow2in", "\u8d28\u91cf\u6d41\u7387\uff0c\u5165\u53e3 2");
    model.component("comp1").variable("var5")
         .set("Pin", "flow1in*intopOutlet1((T1-T)*mat1.def.Cp(T))+flow2in*intopOutlet2((T2-T)*mat1.def.Cp(T))");
    model.component("comp1").variable("var5").descr("Pin", "\u529f\u7387\u8f93\u5165");
    model.component("comp1").variable("var5").set("Pconsumer", "intopConsumers(-P/Lz)");
    model.component("comp1").variable("var5").descr("Pconsumer", "\u603b\u6d88\u8017\u529f\u7387");
    model.component("comp1").variable("var5").set("Efficiency", "Pconsumer/Pin");
    model.component("comp1").variable("var5").descr("Efficiency", "\u7f51\u7edc\u6548\u7387");
    model.component("comp1").variable("var5").set("Pout", "intopAll(-qHeat)");
    model.component("comp1").variable("var5").descr("Pout", "\u603b\u70ed\u635f\u8017");
    model.component("comp1").variable("var5").clear();

//    To import content from file, use:
//    model.component("comp1").variable("var5").loadFile("FILENAME");
    model.component("comp1").variable("var5")
         .set("constr", "log(aveopConsumer(exp(pExp*(1+P/consumerPower)^2)))/pExp", "\u7ea6\u675f");
    model.component("comp1").variable("var5")
         .set("pipePrice", "2*intopHot(pipeCost(pipeD))", "\u7ba1\u4ef7\u683c [\u6b27\u5143]");
    model.component("comp1").variable("var5")
         .set("power1", "intopInlet1((p-1[atm])*nipfl.Qv)", "\u6cf5\u9001\u529f\u7387");
    model.component("comp1").variable("var5")
         .set("power2", "intopInlet2((p-1[atm])*nipfl.Qv)", "\u6cf5\u9001\u529f\u7387");
    model.component("comp1").variable("var5")
         .set("pumpPrice", "pumpWeight*(power1+power2)", "\u6cf5\u4ef7\u683c [\u6b27\u5143]");
    model.component("comp1").variable("var5").set("obj", "pipePrice+pumpPrice", "\u76ee\u6807\u51fd\u6570");
    model.component("comp1").variable("var5")
         .set("flow1in", "intopInlet1(nipfl.Qm)", "\u8d28\u91cf\u6d41\u7387\uff0c\u5165\u53e3 1");
    model.component("comp1").variable("var5")
         .set("flow2in", "intopInlet2(nipfl.Qm)", "\u8d28\u91cf\u6d41\u7387\uff0c\u5165\u53e3 2");
    model.component("comp1").variable("var5")
         .set("Pin", "flow1in*intopOutlet1((T1-T)*mat1.def.Cp(T))+flow2in*intopOutlet2((T2-T)*mat1.def.Cp(T))", "\u80fd\u91cf\u8f93\u5165");
    model.component("comp1").variable("var5")
         .set("Pconsumer", "intopConsumers(-P/Lz)", "\u603b\u6d88\u8017\u529f\u7387");
    model.component("comp1").variable("var5").set("Efficiency", "Pconsumer/Pin", "\u7f51\u7edc\u6548\u7387");
    model.component("comp1").variable("var5").set("Pout", "intopAll(-qHeat)", "\u603b\u70ed\u635f\u8017");

    model.component("comp1").physics("nipfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("nipfl").feature("pipe1").set("innerd", "pipeD");
    model.component("comp1").physics("nipfl").feature("pr1").set("p0", "1[atm]");
    model.component("comp1").physics("nipfl").feature("temp1").set("Tin", "T1");
    model.component("comp1").physics("nipfl").create("hs1", "HeatSource", 1);
    model.component("comp1").physics("nipfl").feature("hs1").selection().all();
    model.component("comp1").physics("nipfl").feature("hs1").set("Q", "qHeat");
    model.component("comp1").physics("nipfl").create("pr2", "Pressure", 0);
    model.component("comp1").physics("nipfl").feature("pr2").selection().named("geom1_ballsel1");
    model.component("comp1").physics("nipfl").feature("pr2").set("p0", "1[atm]+dp1*exp(dp1c)");
    model.component("comp1").physics("nipfl").feature().duplicate("pr3", "pr2");
    model.component("comp1").physics("nipfl").feature("pr3").selection().named("geom1_ballsel4");
    model.component("comp1").physics("nipfl").feature("pr3").set("p0", "1[atm]+dp2*exp(dp2c)");
    model.component("comp1").physics("nipfl").create("temp2", "Temperature", 0);
    model.component("comp1").physics("nipfl").feature("temp2").selection().named("geom1_ballsel4");
    model.component("comp1").physics("nipfl").feature("temp2").set("Tin", "T2");
    model.component("comp1").physics("nipfl").create("hofl1", "HeatOutflow", 0);
    model.component("comp1").physics("nipfl").feature("hofl1").selection().named("geom1_ballsel2");
    model.component("comp1").physics("nipfl").feature().duplicate("hofl2", "hofl1");
    model.component("comp1").physics("nipfl").feature("hofl2").selection().named("geom1_ballsel3");
    model.component("comp1").physics("ce").label("\u5e73\u5747\u7528\u6237\u529f\u7387");
    model.component("comp1").physics("ce").selection().named("geom1_unisel2");
    model.component("comp1").physics("ce").feature("cfeq1")
         .setIndex("c", new String[]{"1e6*Lz^2", "0", "0", "0", "1e6*Lz^2", "0", "0", "0", "1e6*Lz^2"}, 0);
    model.component("comp1").physics("ce").feature("cfeq1").setIndex("a", 1, 0);
    model.component("comp1").physics("ce").feature("cfeq1").setIndex("f", "qHeat*Lz", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u538b\u529b (nipfl)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "p");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*nipfl.dh");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (nipfl)");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("showsolutionparams", "on");
    model.result("pg2").feature("arwl1").set("arrowcount", 20);
    model.result("pg2").feature("arwl1").set("arrowlength", "normalized");
    model.result("pg2").feature("arwl1").set("showsolutionparams", "on");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("col1", "Color");
    model.result("pg2").feature("arwl1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "abs(u)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (nipfl)");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "0.5*nipfl.dh");
    model.result("pg3").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").label("\u5e73\u5747\u7528\u6237\u529f\u7387");
    model.result("pg4").feature("line1").set("expr", "P");
    model.result("pg1").run();

    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u6e29\u5ea6");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_T", "comp1_pipeControl"});
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").label("\u529f\u7387");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_P", "comp1_pipeControl"});
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef").label("\u6d41\u52a8");
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_p", "comp1_u", "comp1_pipeControl"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").set("placement", "gausspoints");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("unit", "degC");
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").add("plotgroup", "pg1");
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").label("\u521d\u59cb\u8bbe\u8ba1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/nipfl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ce", true);
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/nipfl", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ce", true);
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "mma");
    model.study("std2").feature("opt").set("nsolvemax", 50);
    model.study("std2").feature("opt").set("optobj", new String[]{"comp1.constr"});
    model.study("std2").feature("opt").set("descr", new String[]{"\u7ea6\u675f"});
    model.study("std2").feature("opt").setIndex("optobj", "log10(comp1.constr)", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u53ef\u884c\u8bbe\u8ba1");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");
    model.sol("sol2").feature("o1").set("movelimitactive", true);
    model.sol("sol2").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u6e29\u5ea6");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_T", "comp1_pipeControl"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss2").label("\u529f\u7387");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_P", "comp1_pipeControl"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u6d41\u52a8");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_p", "comp1_u", "comp1_pipeControl"});
    model.sol("sol2").feature("o1").feature("s1").feature("d1").set("linsolver", "pardiso");

    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");

    model.nodeGroup("grp2").add("plotgroup", "pg4");

    model.result().duplicate("pg5", "pg2");

    model.nodeGroup("grp2").add("plotgroup", "pg5");

    model.result().duplicate("pg6", "pg3");

    model.nodeGroup("grp2").add("plotgroup", "pg6");

    model.result("pg4").run();

    model.nodeGroup("grp2").remove("plotgroup", "pg6", false);
    model.nodeGroup("grp2").remove("plotgroup", "pg5", false);
    model.nodeGroup("grp2").remove("plotgroup", "pg4", false);

    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u529f\u7387");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "-P");
    model.result("pg7").feature("line1").set("rangecoloractive", true);
    model.result("pg7").feature("line1").set("rangecolormin", "0.8*consumerPower");
    model.result("pg7").feature("line1").set("rangecolormax", "1.2*consumerPower");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("radiusexpr", "5");
    model.result("pg7").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg7").feature("line1").set("colortable", "ThermalDark");
    model.result("pg4").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg4");
    model.nodeGroup("grp3").add("plotgroup", "pg5");
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").label("\u53ef\u884c\u8bbe\u8ba1");

    model.study("std2").feature("opt").set("plot", true);
    model.study("std2").feature("opt").set("plotgroup", "pg7");
    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.result("pg4").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u53ef\u884c\u8bbe\u8ba1");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"Efficiency"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"\u7f51\u7edc\u6548\u7387"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"Efficiency", "pumpPrice"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u7f51\u7edc\u6548\u7387", "\u6cf5\u4ef7\u683c [\u6b27\u5143]"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"Efficiency", "pumpPrice", "pipePrice"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u7f51\u7edc\u6548\u7387", "\u6cf5\u4ef7\u683c [\u6b27\u5143]", "\u7ba1\u4ef7\u683c [\u6b27\u5143]"});
    model.result().evaluationGroup("eg1").run();

    model.study("std3").create("opt", "Optimization");
    model.study("std3").feature("opt").set("optsolver", "mma");
    model.study("std3").feature("opt").set("nsolvemax", 2000);
    model.study("std3").feature("opt").set("optobj", new String[]{"comp1.obj"});
    model.study("std3").feature("opt").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.study("std3").feature("opt").set("objectivescaling", "init");
    model.study("std3").feature("opt").setIndex("pname", "consumerPower", 0);
    model.study("std3").feature("opt").setIndex("initval", "5[kW]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "consumerPower", 0);
    model.study("std3").feature("opt").setIndex("initval", "5[kW]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "dInit", 1);
    model.study("std3").feature("opt").setIndex("initval", "20[cm]", 1);
    model.study("std3").feature("opt").setIndex("scale", 1, 1);
    model.study("std3").feature("opt").setIndex("lbound", "", 1);
    model.study("std3").feature("opt").setIndex("ubound", "", 1);
    model.study("std3").feature("opt").setIndex("pname", "dInit", 1);
    model.study("std3").feature("opt").setIndex("initval", "20[cm]", 1);
    model.study("std3").feature("opt").setIndex("scale", 1, 1);
    model.study("std3").feature("opt").setIndex("lbound", "", 1);
    model.study("std3").feature("opt").setIndex("ubound", "", 1);
    model.study("std3").feature("opt").setIndex("pname", "dp1c", 0);
    model.study("std3").feature("opt").setIndex("lbound", "log(0.1)", 0);
    model.study("std3").feature("opt").setIndex("ubound", "log(10)", 0);
    model.study("std3").feature("opt").setIndex("pname", "dp2c", 1);
    model.study("std3").feature("opt").setIndex("lbound", "log(0.1)", 1);
    model.study("std3").feature("opt").setIndex("ubound", "log(10)", 1);
    model.study("std3").feature("opt").set("constraintExpression", new String[]{"comp1.constr"});
    model.study("std3").feature("opt").setIndex("constraintExpression", "log10(comp1.constr/1e-3)", 0);
    model.study("std3").feature("opt").setIndex("constraintUbound", 0, 0);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u4f18\u5316");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");
    model.sol("sol3").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u6e29\u5ea6");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_T", "comp1_pipeControl"});
    model.sol("sol3").feature("o1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ss2").label("\u529f\u7387");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_P", "comp1_pipeControl"});
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u6d41\u52a8");
    model.sol("sol3").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_bypass", "comp1_consumers", "comp1_p", "comp1_u", "conpar3", "conpar4", "comp1_pipeControl"});
    model.sol("sol3").feature("o1").feature("s1").feature("d1").set("linsolver", "pardiso");

    model.result("pg4").run();
    model.result().duplicate("pg8", "pg4");

    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result().duplicate("pg9", "pg5");

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result().duplicate("pg10", "pg6");

    model.nodeGroup("grp3").add("plotgroup", "pg10");

    model.result().duplicate("pg11", "pg7");

    model.nodeGroup("grp3").add("plotgroup", "pg11");

    model.result("pg8").run();

    model.nodeGroup("grp3").remove("plotgroup", "pg11", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg10", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg9", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg8", false);

    model.result("pg11").run();
    model.result("pg8").run();
    model.result("pg8").label("\u538b\u529b (nipfl) 2");
    model.result("pg8").set("data", "dset3");
    model.result("pg9").run();
    model.result("pg9").label("\u901f\u5ea6 (nipfl) 2");
    model.result("pg9").set("data", "dset3");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset3");
    model.result("pg10").label("\u6e29\u5ea6 (nipfl) 2");
    model.result("pg11").run();
    model.result("pg11").label("\u529f\u7387\u548c\u7ba1\u5f84");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("line2", "Line");
    model.result("pg11").feature("line2").set("expr", "nipfl.dh");
    model.result("pg11").feature("line2").set("descr", "\u6c34\u529b\u76f4\u5f84");
    model.result("pg11").feature("line2").set("linetype", "tube");
    model.result("pg11").feature("line2").set("radiusexpr", "nipfl.dh/2");
    model.result("pg11").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg11").feature("line2").set("tuberadiusscale", 200);
    model.result("pg11").feature("line2").set("smooth", "none");
    model.result("pg11").feature("line2").create("sel1", "Selection");
    model.result("pg11").feature("line2").feature("sel1").selection().named("geom1_boxsel1");
    model.result("pg11").run();
    model.result("pg11").create("ann1", "Annotation");
    model.result("pg11").feature("ann1")
         .set("text", "eval(dp1*exp(dp1c),kPa) kPa, eval(dp2*exp(dp2c),kPa) kPa, P=[eval(minop1(-P),kW), eval(-minop1(P),kW)] kW");
    model.result("pg11").feature("ann1").set("level", "global");
    model.result("pg11").feature("ann1").set("posxexpr", 20);
    model.result("pg11").feature("ann1").set("posyexpr", 285);
    model.result("pg11").feature("ann1").set("exprprecision", 3);
    model.result("pg11").feature("ann1").set("showpoint", false);
    model.result("pg11").feature("ann1").set("backgroundcolor", "green");
    model.result("pg11").feature().duplicate("ann2", "ann1");
    model.result("pg11").run();
    model.result("pg11").feature("ann2")
         .set("text", "c0=[eval(minop1(exp(bypass))), eval(-minop1(-exp(bypass)))], c1=[eval(minop1(exp(consumers))), eval(-minop1(-exp(consumers)))]");
    model.result("pg11").feature("ann2").set("posyexpr", 250);
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u65c1\u8def");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").create("line1", "Line");
    model.result("pg12").feature("line1").set("expr", "genext2(exp(bypass))");
    model.result("pg12").feature("line1").set("linetype", "tube");
    model.result("pg12").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg12").feature("line1").set("tuberadiusscale", 10);
    model.result("pg12").run();
    model.result("pg12").set("titletype", "label");
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u7528\u6237");
    model.result("pg13").run();
    model.result("pg13").feature("line1").set("expr", "genext2(exp(consumers))");
    model.result("pg8").run();

    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").add("plotgroup", "pg8");
    model.nodeGroup("grp4").add("plotgroup", "pg9");
    model.nodeGroup("grp4").add("plotgroup", "pg10");
    model.nodeGroup("grp4").add("plotgroup", "pg11");
    model.nodeGroup("grp4").add("plotgroup", "pg12");
    model.nodeGroup("grp4").add("plotgroup", "pg13");
    model.nodeGroup("grp4").label("\u4f18\u5316");

    model.study("std3").feature("opt").set("plot", true);
    model.study("std3").feature("opt").set("plotgroup", "pg11");
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");

    model.sol("sol3").feature("o1").set("movelimitactive", true);
    model.sol("sol3").feature("o1").set("gcmma", false);

    model.study("std3").createAutoSequences("all");
    model.study("std3").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std3").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std3").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std3").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol3").runAll();

    model.result("pg8").run();

    model.study("std3").feature("opt").set("probewindow", "");

    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u4f18\u5316");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").run();
    model.result("pg12").run();
    model.result("pg12").feature("line1").set("rangecoloractive", true);
    model.result("pg12").feature("line1").set("rangecolormax", 2);
    model.result("pg12").run();
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg11").run();
    model.result("pg11").run();

    model.title("\u533a\u57df\u4f9b\u70ed\u7f51\u7edc\u7684\u62d3\u6251\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u62d3\u6251\u4f18\u5316\u6765\u8bbe\u8ba1\u533a\u57df\u4f9b\u70ed\u7f51\u7edc\u7684\u5e03\u5c40\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("district_heating_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
