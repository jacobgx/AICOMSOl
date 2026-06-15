/*
 * pem_electrolyzer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class pem_electrolyzer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mm", "VolumeAveragedMixtureModelLaminar", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mm", true);

    model.component("comp1").geom("geom1").insertFile("pem_electrolyzer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("intsel3");

    model.param().set("N_ch", "3");
    model.param().set("L_ch", "118*h_a/5");

    model.component("comp1").geom("geom1").run("intsel3");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("Flow_rate_full_cell", "260[ml/min]", "\u8fdb\u6c34\u6d41\u7387\uff0c\u5168\u7535\u89e3\u69fd");
    model.param().set("N_ch_full_cell", "23", "\u7535\u6781\u901a\u9053\u6570\uff0c\u5168\u7535\u89e3\u69fd");
    model.param().set("Flow_rate", "Flow_rate_full_cell*N_ch/N_ch_full_cell", "\u8fdb\u6c34\u6d41\u7387");
    model.param().set("O2_mass_flow_rate_full_cell", "5[mg/s]", "\u51c0\u6c27\u91ca\u653e");
    model.param().set("D_bubbles", "10[um]", "\u6c27\u6c14\u6ce1\u76f4\u5f84");
    model.param().set("rhoO2", "32[g/mol]*1[atm]/R_const/25[degC]", "\u5bc6\u5ea6\uff0c\u6c27\u6c14");
    model.param().set("t", "0[s]", "\u521d\u59cb\u5316\u7684\u865a\u62df\u65f6\u95f4\u53c2\u6570");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("O2_flux", "O2_mass_flow_rate_full_cell/(N_ch_full_cell*L_ch*w_ch)*step1((phid))*rm1(t/1[s])", "\u5c40\u90e8\u6c27\u8d28\u91cf\u901a\u91cf");
    model.component("comp1").variable("var1")
         .set("H2O_flux", "-O2_flux*18/16", "\u5c40\u90e8\u6c34\u8d28\u91cf\u901a\u91cf");
    model.component("comp1").variable("var1")
         .set("disp_flow", "O2_flux/mm.rhod", "\u5206\u6563\u76f8\u4f53\u79ef\u6d41\u7387");
    model.component("comp1").variable("var1")
         .set("mixture_flow", "O2_flux/mm.rhod+H2O_flux/mm.rhoc", "\u603b\u4f53\u79ef\u6d41\u7387");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.95);
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 0);
    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("cutoffactive", true);

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Oxygen");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"150.0", "600.0", "-5.55818182E-7+9.24202797E-8*T^1-8.71841492E-11*T^2+4.82983683E-14*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"150.0", "600.0", "959.514545-0.416383077*T^1+7.63158508E-4*T^2+1.46018648E-6*T^3-3.24009324E-9*T^4+1.6E-12*T^5"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.032/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("dermethod", "manual");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argders", new String[][]{{"pA", "d(pA*0.032/R_const/T,pA)"}, {"T", "d(pA*0.032/R_const/T,T)"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"150.0", "600.0", "-0.0070110303+1.688723E-4*T^1-2.28911422E-7*T^2+1.6991453E-10*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"150.0", "600.0", "959.514545-0.416383077*T^1+7.63158508E-4*T^2+1.46018648E-6*T^3-3.24009324E-9*T^4+1.6E-12*T^5"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.03199[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");

    model.component("comp1").physics("mm").prop("PhysicalModelProperty")
         .set("DispersedPhase", "LiquidDropletsBubbles");
    model.component("comp1").physics("mm").prop("PhysicalModelProperty").set("SlipModel", "SchillerNaumann");
    model.component("comp1").physics("mm").feature("mp1").set("ContinuousPhase", "mat1");
    model.component("comp1").physics("mm").feature("mp1").set("DispersedPhase", "mat2");
    model.component("comp1").physics("mm").feature("mp1").set("rhod_mat", "userdef");
    model.component("comp1").physics("mm").feature("mp1").set("rhod", "rhoO2");
    model.component("comp1").physics("mm").feature("mp1").set("diam", "D_bubbles");
    model.component("comp1").physics("mm").feature("mp1").set("MixtureViscosityModel", "VolumeAveraged");
    model.component("comp1").physics("mm").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("mm").feature("inl1").label("\u5165\u53e3 - \u6db2\u6001\u6c34");
    model.component("comp1").physics("mm").feature("inl1").selection().named("geom1_boxsel5");
    model.component("comp1").physics("mm").feature("inl1").set("U0in", "Flow_rate/(pi*R_in^2)");
    model.component("comp1").physics("mm").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("mm").feature("inl2")
         .label("\u5165\u53e3 - \u7535\u6781\u8868\u9762\u6790\u6c27");
    model.component("comp1").physics("mm").feature("inl2").selection().named("geom1_boxsel4");
    model.component("comp1").physics("mm").feature("inl2").set("U0in", "mixture_flow");
    model.component("comp1").physics("mm").feature("inl2")
         .set("DispersedPhaseBoundaryCondition", "DispersedPhaseFlux");
    model.component("comp1").physics("mm").feature("inl2").set("Nphid", "disp_flow");
    model.component("comp1").physics("mm").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("mm").feature("out1").selection().named("geom1_boxsel6");
    model.component("comp1").physics("mm").create("gr1", "Gravity", 3);
    model.component("comp1").physics("mm").feature("gr1").selection().all();

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel3");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").named("geom1_intsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").named("geom1_intsel3");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_boxsel3");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", "floor(L_ch/(0.5*w_ch))");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().named("geom1_intsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "h_a/4");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("zscale", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "w_ch/4");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").set("zscale", 2);
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet2").feature("size1").set("hmax", "h_a");
    model.component("comp1").mesh("mesh1").run("ftet2");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_boxsel3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_intsel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "w_ch/15");
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 0);
    model.study("std1").feature("time").set("tlist", "0 1 2 10");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("control", "user");
    model.sol("sol1").feature("v1").feature("comp1_phid").set("solvefor", false);
    model.sol("sol1").feature("v1").feature("comp1_slipvel").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_j").set("scalemethod", "init");
    model.sol("sol1").feature("v2").feature("comp1_p").set("scalemethod", "init");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u538b\u529b");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "p");
    model.result("pg1").feature("surf1").set("descr", "\u538b\u529b");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "mm.U");
    model.result("pg2").feature("slc1").set("descr", "\u8d28\u91cf\u5e73\u5747\u901f\u5ea6\u573a");
    model.result("pg2").feature("slc1").set("quickplane", "xy");
    model.result("pg2").feature("slc1").set("quickzmethod", "coord");
    model.result("pg2").feature("slc1").set("quickz", "h_a/2");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6c14\u4f53\u4f53\u79ef\u5206\u6570");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "-w_ch/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L_ch/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "h_a/2", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "N_ch*w_ch*2-3*w_ch/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L_ch/2", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", "h_a/2", 1, 2);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6781\u901a\u9053\u4e2d\u7684\u901f\u5ea6");
    model.result("pg4").set("data", "cln1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "mm.U");
    model.result("pg4").feature("lngr1").set("descr", "\u8d28\u91cf\u5e73\u5747\u901f\u5ea6\u573a");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();

    model.param().set("N_ch", "23");
    model.param().set("L_ch", "118*h_a");

    model.component("comp1").geom("geom1").run("intsel3");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").func("step1").set("location", 0.995);
    model.component("comp1").func("step1").set("smooth", 0.01);

    model.sol("sol1").copySolution("sol3");
    model.sol("sol3").label("\u89e3 - \u8f83\u5c0f\u7684\u51e0\u4f55");
    model.sol("sol1").createAutoSequence("std1");
    model.sol("sol1").feature("v1").set("control", "user");
    model.sol("sol1").feature("v1").feature("comp1_phid").set("solvefor", false);
    model.sol("sol1").feature("v1").feature("comp1_slipvel").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_slipvel").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_slipvel").set("scaleval", "1e-5");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg3");
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u7535\u89e3\u69fd");

    model
         .description("\u5728\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u7535\u89e3\u6c60 (PEMEC) \u4e2d\uff0c\u4e24\u4e2a\u7535\u6781\u5ba4\u88ab\u805a\u5408\u7269\u819c\u9694\u5f00\u3002\u4ece\u9633\u6781\u4fa7\u9001\u5165\u6db2\u6001\u6c34\uff0c\u5206\u522b\u5728\u9633\u6781\u4ea7\u751f\u6c27\u6c14\uff0c\u5728\u9634\u6781\u4fa7\u4ea7\u751f\u6c22\u6c14\u3002\n\n\u4e3a\u4e86\u5728\u64cd\u4f5c\u8fc7\u7a0b\u4e2d\u83b7\u5f97\u5747\u5300\u5206\u5e03\u7684\u6d41\u91cf\u548c\u4f4e\u538b\u964d\uff0c\u6d41\u573a\u6a21\u5f0f\u7684\u5404\u4e2a\u8bbe\u8ba1\u975e\u5e38\u91cd\u8981\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u6df7\u5408\u7269\u6a21\u578b\u6765\u6a21\u62df PEMEC \u9633\u6781\u4fa7\u7684\u4e24\u76f8\u6d41\u4f53\u52a8\u529b\u5b66\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pem_electrolyzer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
