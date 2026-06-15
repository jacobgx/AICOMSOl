/*
 * lib_single_particle.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class lib_single_particle {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("epss_neg", "0.6", "\u6d3b\u6027\u7535\u6781\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epss_pos", "0.6", "\u6d3b\u6027\u7535\u6781\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("epss_binder_neg", "0.1", "\u9ecf\u5408\u5242\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epss_binder_pos", "0.1", "\u9ecf\u5408\u5242\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("epsl_neg", "1-epss_neg-epss_binder_neg", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epsl_sep", "0.4", "\u9694\u819c\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epsl_pos", "1-epss_pos-epss_binder_pos", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("sigmas_neg", "1[S/m]", "\u6709\u6548\u7535\u6781\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "1[S/m]", "\u6709\u6548\u7535\u6781\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("rp_neg", "6[um]", "\u7535\u6781\u9897\u7c92\u534a\u5f84\uff0c\u8d1f\u6781");
    model.param().set("rp_pos", "5[um]", "\u7535\u6781\u9897\u7c92\u534a\u5f84\uff0c\u6b63\u6781");
    model.param().set("cs_max_neg", "31507[mol/m^3]", "\u6700\u5927\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("cs_max_pos", "49000[mol/m^3]", "\u6700\u5927\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.param()
         .set("i0_ref_neg", "10[A/m^2]", "\u9502\u5316 50% \u65f6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0_ref_pos", "10[A/m^2]", "\u9502\u5316 50% \u65f6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("L_sep", "20[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_pos", "45[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param()
         .set("L_neg", "(1-0.1)*epss_pos*cs_max_pos*L_pos/(0.8*epss_neg*cs_max_neg)", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_ccs", "10[um]", "\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("V_cell", "90[%]*(10.5[mm])^2*pi*70[mm]", "\u6d3b\u6027\u7535\u6c60\u4f53\u79ef");
    model.param().set("A_cell", "V_cell/(L_neg+L_sep+L_pos+L_ccs/2)", "\u7535\u6c60\u9762\u79ef");
    model.param().set("soc_init", "0", "\u521d\u59cb SOC");
    model.param().set("T", "25[degC]", "\u6e29\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat1").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat2").label("Graphite, LixC6 MCMB (Negative, Li-ion Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("fununit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "2.781186612"}, 
         {"0.01", "1.520893224"}, 
         {"0.02", "0.893922607"}, 
         {"0.03", "0.581284406"}, 
         {"0.04", "0.42452844"}, 
         {"0.05", "0.344895805"}, 
         {"0.06", "0.303146342"}, 
         {"0.07", "0.279578072"}, 
         {"0.08", "0.264093089"}, 
         {"0.09", "0.251347845"}, 
         {"0.1", "0.238588379"}, 
         {"0.11", "0.224803164"}, 
         {"0.12", "0.210294358"}, 
         {"0.13", "0.196408586"}, 
         {"0.14", "0.184624188"}, 
         {"0.15", "0.175188157"}, 
         {"0.16", "0.167373311"}, 
         {"0.17", "0.160452107"}, 
         {"0.18", "0.154025412"}, 
         {"0.19", "0.147948522"}, 
         {"0.2", "0.142214997"}, 
         {"0.21", "0.13688271"}, 
         {"0.22", "0.132033114"}, 
         {"0.23", "0.127747573"}, 
         {"0.24", "0.124091616"}, 
         {"0.25", "0.121103387"}, 
         {"0.26", "0.11878567"}, 
         {"0.27", "0.117102317"}, 
         {"0.28", "0.115980205"}, 
         {"0.29", "0.115317054"}, 
         {"0.3", "0.114993965"}, 
         {"0.31", "0.114890105"}, 
         {"0.32", "0.114886278"}, 
         {"0.33", "0.114884619"}, 
         {"0.34", "0.114873068"}, 
         {"0.35", "0.114824904"}, 
         {"0.36", "0.114644725"}, 
         {"0.37", "0.114372614"}, 
         {"0.38", "0.114017954"}, 
         {"0.39", "0.11359371"}, 
         {"0.4", "0.11311133"}, 
         {"0.41", "0.112575849"}, 
         {"0.42", "0.111980245"}, 
         {"0.43", "0.111297682"}, 
         {"0.44", "0.110470149"}, 
         {"0.45", "0.109393081"}, 
         {"0.46", "0.107900592"}, 
         {"0.47", "0.10576964"}, 
         {"0.48", "0.102783317"}, 
         {"0.49", "0.09889031"}, 
         {"0.5", "0.094391564"}, 
         {"0.51", "0.089921069"}, 
         {"0.52", "0.086112415"}, 
         {"0.53", "0.083265315"}, 
         {"0.54", "0.081326247"}, 
         {"0.55", "0.080074892"}, 
         {"0.56", "0.07928329"}, 
         {"0.57", "0.078778765"}, 
         {"0.58", "0.078447703"}, 
         {"0.59", "0.078220432"}, 
         {"0.6", "0.078055641"}, 
         {"0.61", "0.077929111"}, 
         {"0.62", "0.077826563"}, 
         {"0.63", "0.077739397"}, 
         {"0.64", "0.077662227"}, 
         {"0.65", "0.077591472"}, 
         {"0.66", "0.077524557"}, 
         {"0.67", "0.077459463"}, 
         {"0.68", "0.077394455"}, 
         {"0.69", "0.077327934"}, 
         {"0.7", "0.077258337"}, 
         {"0.71", "0.077184077"}, 
         {"0.72", "0.077103499"}, 
         {"0.73", "0.077014851"}, 
         {"0.74", "0.076916258"}, 
         {"0.75", "0.07680571"}, 
         {"0.76", "0.07668104"}, 
         {"0.77", "0.07653992"}, 
         {"0.78", "0.076379839"}, 
         {"0.79", "0.076198086"}, 
         {"0.8", "0.075991699"}, 
         {"0.81", "0.075757371"}, 
         {"0.82", "0.075491288"}, 
         {"0.83", "0.075188813"}, 
         {"0.84", "0.07484398"}, 
         {"0.85", "0.074448647"}, 
         {"0.86", "0.07399118"}, 
         {"0.87", "0.073454466"}, 
         {"0.88", "0.072812991"}, 
         {"0.89", "0.072028722"}, 
         {"0.9", "0.071045433"}, 
         {"0.91", "0.069780996"}, 
         {"0.92", "0.068116222"}, 
         {"0.93", "0.065874599"}, 
         {"0.94", "0.062770873"}, 
         {"0.95", "0.058253898"}, 
         {"0.96", "0.051075794"}, 
         {"0.97", "0.038790069"}, 
         {"0.98", "0.020172191"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("funcname", "dEeqdT");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"0", "3.0e-4"}, 
         {"0.17", "0"}, 
         {"0.24", "-6e-5"}, 
         {"0.28", "-1.6e-4"}, 
         {"0.5", "-1.6e-4"}, 
         {"0.54", "-9e-5"}, 
         {"0.71", "-9e-5"}, 
         {"0.85", "-1.0e-4"}, 
         {"1.0", "-1.2e-4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "E_int(c/csmax)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "nu_int(c/csmax)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"100[S/m]", "0", "0", "0", "100[S/m]", "0", "0", "0", "100[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "V. Srinivasan, and J. Newman, \u201cDesign and Optimization of a Natural Graphite/Iron Phosphate Lithium Ion Cell,\u201d J. Electrochem. Soc., vol. 151, p. 1530, 2004.");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "K. Kumaresan, G. Sikha, and R. E. White, \u201cThermal Model for a Li-Ion Cell,\u201d J. Electrochem. Soc., vol. 155, p. A164, 2008.");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "S. Chen, C. Wan, and Y. Wang, J. Power Sources, 140, 111 (2005).");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "750[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "31507[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("T_ref", "318[K]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "D. P Karthikeyan, G. Sikha, and R. E. White, \u201cThermodynamic model development for lithium intercalation electrodes,\u201d J. Power Sources, vol. 185, p. 1398, 2008.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"0.006802721088435382", "0.12500000000000178"}, 
         {"0.06316812439261421", "1.2736486486486491"}, 
         {"0.11175898931000966", "2.523648648648649"}, 
         {"0.17978620019436342", "3.5709459459459474"}, 
         {"0.2400388726919339", "4.449324324324325"}, 
         {"0.2905733722060252", "5.192567567567568"}, 
         {"0.3566569484936831", "5.66554054054054"}, 
         {"0.4188532555879494", "5.969594594594595"}, 
         {"0.48104956268221566", "6.10472972972973"}, 
         {"0.5432458697764819", "6.173648648648647"}, 
         {"0.58600583090379", "6.306081081081081"}, 
         {"0.6112730806608356", "7.726351351351352"}, 
         {"0.6443148688046647", "8.570945945945946"}, 
         {"0.694849368318756", "9.449324324324323"}, 
         {"0.7414965986394557", "10.29391891891892"}, 
         {"0.7764820213799805", "10.902027027027025"}, 
         {"0.8231292517006802", "11.543918918918918"}, 
         {"0.8542274052478133", "12.152027027027026"}, 
         {"0.8833819241982507", "12.827702702702702"}, 
         {"0.9183673469387755", "12.996621621621621"}, 
         {"0.9494655004859086", "13.16554054054054"}});
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat2").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat2").propertyGroup("ic")
         .setPropertyInfo("dvol", "S. Schweidler, L. de Biasi, A. Schiele, P. Hartmann, T. Brezesinski and J. Janek, \"Volume Changes of Graphite Anodes Revisited: A Combined Operando X-Ray Diffraction and In Situ Pressure Analysis Study\", J. Phys. Chem. C, 2018, 122, 8829\u20138835");
    model.component("comp1").material("mat2").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp1").material("mat3").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("NMC 111, LiNi0.33Mn0.33Co0.33O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "4.44"}, 
         {"0.032", "4.34"}, 
         {"0.102", "4.23"}, 
         {"0.187", "4.13"}, 
         {"0.289", "4.025"}, 
         {"0.38", "3.945"}, 
         {"0.543", "3.835"}, 
         {"0.775", "3.71"}, 
         {"0.872", "3.62"}, 
         {"0.925", "3.51"}, 
         {"0.943", "3.42"}, 
         {"0.957", "3.30"}, 
         {"0.966", "3.165"}, 
         {"0.970", "3.02"}, 
         {"0.972", "2.90"}, 
         {"0.975", "2.688"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.25");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "78[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "Jing Ying Ko et al, J. Electrochem. Soc., 166, A2939");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "49000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
    model.component("comp1").material("mat3").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.9260263416001121", "-0.010256410256411108"}, 
         {"0.8670351688384477", "-0.1948717948717955"}, 
         {"0.8113086731119519", "-0.27692307692307727"}, 
         {"0.7506669468964551", "-0.37948717948718036"}, 
         {"0.6949460557657279", "-0.502564102564103"}, 
         {"0.628563822334314", "-0.5846153846153856"}, 
         {"0.55562421185372", "-0.6666666666666674"}, 
         {"0.501531455793751", "-0.7076923076923083"}, 
         {"0.4441600112091916", "-0.7487179487179496"}, 
         {"0.3851716407454113", "-0.953846153846154"}, 
         {"0.3278338237354632", "-1.241025641025642"}, 
         {"0.2737943113352951", "-1.671794871794872"}, 
         {"0.24269440941572107", "-2.0205128205128213"}});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat3").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat3").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp1").material("mat3").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importedname", "NMC_333.txt");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importeddim", "2D");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnametable", new String[][]{{"log_sigmas", "1"}});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "log_sigmas"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("argunit", new String[]{"1", "1/K"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("sourcetype", "model");
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .set("electricconductivity", new String[]{"10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .setPropertyInfo("electricconductivity", "Ruhul Amin and Yet-Ming Chiang 2016 J. Electrochem. Soc. 163 A1512");
    model.component("comp1").material("mat3").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp1").material("mat3").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("concentration");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").selection().set(2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u9694\u819c");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u8d1f\u6781");
    model.component("comp1").selection("sel2").set(1);

    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").label("\u6b63\u6781");
    model.component("comp1").selection("sel3").set(3);

    model.component("comp1").material("mat3").selection().named("sel3");

    model.component("comp1").physics("liion").prop("Ac").set("Ac", "A_cell");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", "soc_init");
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().named("sel3");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").label("\u591a\u5b54\u7535\u6781 - \u8d1f\u6781");
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_neg");
    model.component("comp1").physics("liion").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("liion").feature("pce2").label("\u591a\u5b54\u7535\u6781 - \u6b63\u6781");
    model.component("comp1").physics("liion").feature("pce2").selection().named("sel3");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0_ref_pos");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(0);
    model.component("comp1").selection("sel4").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel4").set(1);

    model.component("comp1").physics("liion").feature("egnd1").selection().named("sel4");
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(4);

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(0);
    model.component("comp1").selection("sel5").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").selection("sel5").set(4);

    model.component("comp1").physics("liion").feature("ecd1").selection().named("sel5");
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "I_1C");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("sol_neg", "liion.soc_average_pce1", "\u9502\u5316\u5ea6\uff0c\u8d1f\u6781");
    model.component("comp1").variable("var1")
         .set("sol_pos", "liion.soc_average_pce2", "\u9502\u5316\u5ea6\uff0c\u6b63\u6781");
    model.component("comp1").variable("var1")
         .set("soc_cell", "liion.SOC_cell", "\u7535\u6c60\u5355\u5143\u8377\u7535\u72b6\u6001");
    model.component("comp1").variable("var1")
         .set("E_ocp_neg", "mat2.def.Eeq(sol_neg)", "\u8d1f\u6781\u5f00\u8def\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("E_ocp_pos", "mat3.def.Eeq(sol_pos)", "\u6b63\u6781\u5f00\u8def\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("E_ocv_cell", "E_ocp_pos-E_ocp_neg", "\u5f00\u8def\u7535\u6c60\u7535\u538b");
    model.component("comp1").variable("var1")
         .set("E_pol_tot", "E_cell-E_ocv_cell", "\u603b\u7535\u6c60\u5355\u5143\u6781\u5316");
    model.component("comp1").variable("var1")
         .set("I_1C", "liion.I_1C_cell/A_cell", "1 h \u5145\u653e\u7535\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").set("probename", "E_cell");
    model.component("comp1").probe("point1").set("type", "integral");
    model.component("comp1").probe("point1").selection().named("sel5");
    model.component("comp1").probe("point1").set("expr", "phis");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "\u7535\u6c60\u7535\u538b");

    model.result().table().create("tbl1", "Table");

    model.component("comp1").probe("point1").set("table", "tbl1");

    model.result().table("tbl1").label("\u7535\u6c60\u7535\u538b\u63a2\u9488\u6570\u636e");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,0.9)");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").selection().all();
    model.result("pg2").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg2").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"liion.SOC_cell"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u8377\u7535\u72b6\u6001"});
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("unit", new String[]{"", ""});
    model.result("pg3").feature("glob2")
         .set("expr", new String[]{"liion.soc_average_pce1", "liion.soc_average_pce2"});
    model.result("pg3").feature("glob2")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 - \u8d1f\u6781", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 - \u6b63\u6781"});
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg3")
         .label("\u7535\u6c60\u548c\u5e73\u5747\u7535\u6781\u7535\u6c60\u7684\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg3").set("ylabel", "SOC (1)");
    model.result("pg3").set("titletype", "none");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg6").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg2").run();

    model.sol().remove("sol1");
    model.sol().remove("sol2");

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().remove("pg1");

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u57fa\u7840\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u5305\u542b\u5728\u4e00\u7ef4\u4e2d\u5b9a\u4e49\u7684\u9502\u79bb\u5b50\u7535\u6c60\u7684\u7269\u7406\u573a\u3001\u51e0\u4f55\u548c\u7f51\u683c\u7684\u6a21\u677f\u57fa\u7840\u6a21\u578b\u3002\n\n\u8be5\u6a21\u578b\u4f7f\u7528\u56db\u4e2a\u9502\u5316\u53c2\u6570\u6765\u5b9a\u4e49\u8d1f\u6781\u548c\u6b63\u6781\u7684\u76f8\u5bf9\u5e73\u8861\uff0c\u4ee5\u53ca\u5168\u5c40\u7535\u6c60\u8377\u7535\u72b6\u6001 (SOC) \u53d8\u91cf\u3002\u6b64\u5916\uff0c\u8fd8\u4f7f\u7528\u7535\u6c60\u6a2a\u622a\u9762\u5bb9\u91cf\u53c2\u6570\u6765\u5b9a\u4e49\u7535\u6781\u539a\u5ea6\u3002\n\n\u201c\u7535\u6c60\u6a21\u5757\u201d\u6848\u4f8b\u5e93\u4e2d\u7684\u5404\u79cd\u6559\u7a0b\u90fd\u4f7f\u7528\u4e86\u8be5\u57fa\u7840\u6a21\u578b\u3002");

    model.label("lib_base_model_1d.mph");

    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").create("expll1", "ExplicitEventList", -1);
    model.component("comp1").physics("ev").feature("expll1").set("variableName", "C_rate");
    model.component("comp1").physics("ev").feature("expll1").set("initialValue", -1);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "2000[s]", 0, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "2300[s]", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 1, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "3300[s]", 2, 0);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "I_1C*C_rate");

    model.param().set("L_pos", "60[um]");
    model.param().set("soc_init", "0.75");

    model.component("comp1").geom("geom1").run();

    model.study("std1").feature("time").set("tunit", "s");
    model.study("std1").feature("time").set("tlist", "0 5000");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("L_el", "L_sep+1/2*(L_neg+L_pos)", "\u7535\u89e3\u6db2\u57df\u957f\u5ea6");
    model.param("par2").set("E_IR_1C", "45[mV]", "1C \u7535\u89e3\u6db2\u7535\u538b\u964d");
    model.param("par2")
         .set("Q_host_neg", "L_neg*epss_neg*cs_max_neg*A_cell*F_const", "\u53ef\u7528\u5bb9\u91cf\uff0c\u8d1f\u6781");
    model.param("par2")
         .set("Q_host_pos", "L_pos*epss_pos*cs_max_pos*A_cell*F_const", "\u53ef\u7528\u5bb9\u91cf\uff0c\u6b63\u6781");
    model.param("par2")
         .set("Ds_neg", "1.4523e-13*exp(68025.7/8.314*(1/(318[K]/1[K])-1/(T/1[K])))[m^2/s]", "\u63d2\u5c42\u6269\u6563\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param("par2").set("Ds_pos", "1e-14[m^2/s]", "\u63d2\u5c42\u6269\u6563\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param("par2").set("tau_neg", "rp_neg^2/Ds_neg", "\u6269\u6563\u65f6\u95f4\u5e38\u6570\uff0c\u8d1f\u6781");
    model.param("par2").set("tau_pos", "rp_pos^2/Ds_pos", "\u6269\u6563\u65f6\u95f4\u5e38\u6570\uff0c\u6b63\u6781");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 1);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("i1", "Interval");
    model.component("comp2").geom("geom2").feature("i1").setIndex("coord", "L_el", 1);

    model.component("comp2").physics().create("liion2", "LithiumIonBatteryMPH", "geom2");

    model.study("std1").feature("cdi").setSolveFor("/physics/liion2", true);
    model.study("std1").feature("time").setSolveFor("/physics/liion2", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("liion2").prop("Ac").set("Ac", "A_cell");
    model.component("comp2").physics("liion2").feature("sep1").set("ElectrolyteMaterial", "mat1");
    model.component("comp2").physics("liion2").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp2").physics("liion2").create("tpce1", "ThinPorousElectrode", 0);
    model.component("comp2").physics("liion2").feature("tpce1").selection().set(1);
    model.component("comp2").physics("liion2").feature("tpce1").set("d_pe", "L_neg");
    model.component("comp2").physics("liion2").feature("tpce1").set("epss", "epss_neg");
    model.component("comp2").physics("liion2").feature("tpce1").feature("pin1").set("ParticleMaterial", "mat2");
    model.component("comp2").physics("liion2").feature("tpce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp2").physics("liion2").feature("tpce1").feature("per1").set("MaterialOption", "mat2");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp2").physics("liion2").feature("tpce1").feature("per1").set("i0_ref", "i0_ref_neg");
    model.component("comp2").physics("liion2").create("tpce2", "ThinPorousElectrode", 0);
    model.component("comp2").physics("liion2").feature("tpce2").selection().set(2);
    model.component("comp2").physics("liion2").feature("tpce2").set("d_pe", "L_pos");
    model.component("comp2").physics("liion2").feature("tpce2").set("epss", "epss_pos");
    model.component("comp2").physics("liion2").feature("tpce2").set("BoundaryCondition", "TotalCurrent");
    model.component("comp2").physics("liion2").feature("tpce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp2").physics("liion2").feature("tpce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp2").physics("liion2").feature("tpce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp2").physics("liion2").feature("tpce2").feature("per1").set("i0_ref", "i0_ref_pos");
    model.component("comp2").physics("liion2").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp2").physics("liion2").feature("socicd1").set("NegativeElectrode", "Boundary");
    model.component("comp2").physics("liion2").feature("socicd1").set("PositiveElectrode", "Boundary");
    model.component("comp2").physics("liion2").feature("socicd1").set("SOC_init", "soc_init");
    model.component("comp2").physics("liion2").feature("socicd1").feature("negebs1").selection().set(1);
    model.component("comp2").physics("liion2").feature("socicd1").feature("posebs1").selection().set(2);
    model.component("comp2").physics("liion2").feature("tpce2").set("TotalCurrentType", "CrateMultiple");
    model.component("comp2").physics("liion2").feature("tpce2").set("Crate", "comp1.C_rate");

    model.component("comp2").probe().create("var1", "GlobalVariable");
    model.component("comp2").probe("var1").set("expr", "liion2.phis_tpce2");
    model.component("comp2").probe("var1").set("descr", "\u7535\u52bf");
    model.component("comp2").probe("var1").set("descractive", true);

    model.result().table().create("tbl2", "Table");

    model.component("comp2").probe("var1").set("table", "tbl2");
    model.component("comp2").probe("var1").set("window", "window1");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().geom("geom2", 0);
    model.component("comp2").cpl("intop1").selection().set(1);
    model.component("comp2").cpl("intop1").set("opname", "intop_neg");

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", false);
    model.study("std2").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/liion2", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", false);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").feature("time").setSolveFor("/physics/liion2", true);
    model.study("std2").feature("time").set("tlist", "0 5000");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"var1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp2").probe("var1").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset5");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(2);
    model.result("pg2").feature("ptgr1").set("expr", "phil2-intop_neg(phil2)");
    model.result("pg2").run();
    model.result("pg2").run();

    model.component().copy("comp3", "comp2");

    model.component("comp3").probe("var2").set("expr", "liion3.phis_tpce2");

    model.result().table().create("tbl3", "Table");

    model.component("comp3").probe("var2").set("table", "tbl3");

    model.component("comp3").variable().create("var2");
    model.component("comp3").variable("var2").set("sigmal_eff", "liion3.I_1C_cell*L_el/(E_IR_1C*A_cell)");

    model.component("comp3").physics("liion3").prop("ElectrolyteType").set("ElectrolyteType", "SingleIonConductor");
    model.component("comp3").physics("liion3").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp3").physics("liion3").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp3").physics("liion3").feature("sep1")
         .set("sigmal", new String[]{"sigmal_eff", "0", "0", "0", "sigmal_eff", "0", "0", "0", "sigmal_eff"});
    model.component("comp3").physics("liion3").feature("sep1").set("IonicCorrModel", "NoCorr");

    model.component("comp3").mesh("mesh3").create("edg1", "Edge");
    model.component("comp3").mesh("mesh3").feature("edg1").create("dis1", "Distribution");
    model.component("comp3").mesh("mesh3").feature("edg1").feature("dis1").set("numelem", 1);

    model.study().create("std3");
    model.study("std3").create("cdi", "CurrentDistributionInitialization");
    model.study("std3").feature("cdi").set("ftplistmethod", "manual");
    model.study("std3").feature("cdi").set("solnum", "auto");
    model.study("std3").feature("cdi").set("notsolnum", "auto");
    model.study("std3").feature("cdi").set("outputmap", new String[]{});
    model.study("std3").feature("cdi").set("ngenAUX", "1");
    model.study("std3").feature("cdi").set("goalngenAUX", "1");
    model.study("std3").feature("cdi").set("ngenAUX", "1");
    model.study("std3").feature("cdi").set("goalngenAUX", "1");
    model.study("std3").feature("cdi").setSolveFor("/physics/liion", false);
    model.study("std3").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std3").feature("cdi").setSolveFor("/physics/liion2", false);
    model.study("std3").feature("cdi").setSolveFor("/physics/liion3", true);
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").set("plotgroup", "Default");
    model.study("std3").feature("time").set("ftplistmethod", "manual");
    model.study("std3").feature("time").set("initialtime", "0");
    model.study("std3").feature("time").set("solnum", "auto");
    model.study("std3").feature("time").set("notsolnum", "auto");
    model.study("std3").feature("time").set("outputmap", new String[]{});
    model.study("std3").feature("time").setSolveFor("/physics/liion", false);
    model.study("std3").feature("time").setSolveFor("/physics/ev", true);
    model.study("std3").feature("time").setSolveFor("/physics/liion2", false);
    model.study("std3").feature("time").setSolveFor("/physics/liion3", true);
    model.study("std3").feature("time").set("tlist", "0 5000");
    model.study("std3").feature("time").set("probesel", "manual");
    model.study("std3").feature("time").set("probes", new String[]{"var2"});
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.component("comp3").probe("var2").genResult("none");

    model.sol("sol5").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.component().create("comp4", true);

    model.component("comp4").physics().create("lb", "LumpedBattery");

    model.study("std1").feature("cdi").setSolveFor("/physics/lb", true);
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/lb", true);
    model.study("std2").feature("time").setSolveFor("/physics/lb", true);
    model.study("std3").feature("cdi").setSolveFor("/physics/lb", true);
    model.study("std3").feature("time").setSolveFor("/physics/lb", true);

    model.component("comp4").physics("lb").prop("BatterySettings").set("LumpedBatModel", "TwoElectrodes");
    model.component("comp4").physics("lb").prop("BatterySettings").set("AppliedCurrentType", "CrateMultiple");
    model.component("comp4").physics("lb").prop("BatterySettings").set("Crate", "comp1.C_rate");
    model.component("comp4").physics("lb").prop("BatterySettings").set("Q_host_neg0", "Q_host_neg");
    model.component("comp4").physics("lb").prop("BatterySettings").set("Q_host_pos0", "Q_host_pos");
    model.component("comp4").physics("lb").prop("BatterySettings").set("SOC_cell0", "soc_init");
    model.component("comp4").physics("lb").feature("negep1").set("ElectrodeMaterial", "mat2");
    model.component("comp4").physics("lb").feature("negep1").set("minput_temperature", "T");
    model.component("comp4").physics("lb").feature("posep1").set("ElectrodeMaterial", "mat3");
    model.component("comp4").physics("lb").feature("posep1").set("minput_temperature", "T");

    model.component("comp4").variable().create("var3");
    model.component("comp4").variable("var3")
         .set("J0_neg", "(lb.DOC_neg_surface*(1-lb.DOC_neg_surface))^0.5*2*i0_ref_neg*(3/rp_neg)/(cs_max_neg*F_const/1[h])");
    model.component("comp4").variable("var3")
         .descr("J0_neg", "Dimensionless exchange current density, negative electrode");
    model.component("comp4").variable("var3")
         .set("J0_pos", "(lb.DOC_pos_surface*(1-lb.DOC_pos_surface))^0.5*2*i0_ref_pos*(3/rp_pos)/(cs_max_pos*F_const/1[h])");
    model.component("comp4").variable("var3")
         .descr("J0_pos", "Dimensionless exchange current density, positive electrode");

    model.component("comp4").physics("lb").feature("vl1").set("eta_ir1C", "E_IR_1C");
    model.component("comp4").physics("lb").feature("vl1").set("minput_temperature", "T");
    model.component("comp4").physics("lb").feature("vl1").set("J0_neg", "J0_neg");
    model.component("comp4").physics("lb").feature("vl1").set("J0_pos", "J0_pos");
    model.component("comp4").physics("lb").feature("vl1").set("IncludeConcentrationOverpotentialNegative", true);
    model.component("comp4").physics("lb").feature("vl1").set("tau_neg", "tau_neg");
    model.component("comp4").physics("lb").feature("vl1").set("IncludeConcentrationOverpotentialPositive", true);
    model.component("comp4").physics("lb").feature("vl1").set("tau_pos", "tau_pos");

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/liion", false);
    model.study("std4").feature("time").setSolveFor("/physics/ev", true);
    model.study("std4").feature("time").setSolveFor("/physics/liion2", false);
    model.study("std4").feature("time").setSolveFor("/physics/liion3", false);
    model.study("std4").feature("time").setSolveFor("/physics/lb", true);

    model.component("comp4").probe().create("var3", "GlobalVariable");
    model.component("comp4").probe("var3").set("descractive", true);

    model.result().table().create("tbl4", "Table");

    model.component("comp4").probe("var3").set("table", "tbl4");
    model.component("comp4").probe("var3").set("window", "window1");

    model.study("std4").feature("time").set("tlist", "0 5000");
    model.study("std4").feature("time").set("probesel", "manual");
    model.study("std4").feature("time").set("probes", new String[]{"var3"});
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.component("comp4").probe("var3").genResult("none");

    model.sol("sol7").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "DFN", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "SPMe", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp3").set("legendmethod", "manual");
    model.result("pg1").feature("tblp3").setIndex("legends", "SPM", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp4").set("linestyle", "dashed");
    model.result("pg1").feature("tblp4").set("legendmethod", "manual");
    model.result("pg1").feature("tblp4").setIndex("legends", "SPM (Lumped)", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 2290);
    model.result("pg1").set("xmax", 2350);
    model.result("pg1").set("ymin", 3.56);
    model.result("pg1").set("ymax", 3.67);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", false);

    model.study("std1").feature("time").set("probesel", "manual");
    model.study("std1").feature("time").set("probes", new String[]{"point1"});
    model.study("std1").feature("cdi").setSolveFor("/physics/liion2", false);
    model.study("std1").feature("cdi").setSolveFor("/physics/liion3", false);
    model.study("std1").feature("time").setSolveFor("/physics/liion2", false);
    model.study("std1").feature("time").setSolveFor("/physics/liion3", false);
    model.study("std1").feature("time").setSolveFor("/physics/lb", false);
    model.study("std2").feature("time").set("probes", new String[]{"var1"});
    model.study("std2").feature("cdi").setSolveFor("/physics/liion3", false);
    model.study("std2").feature("time").setSolveFor("/physics/liion3", false);
    model.study("std2").feature("time").setSolveFor("/physics/lb", false);
    model.study("std3").feature("time").set("probes", new String[]{"var2"});
    model.study("std3").feature("time").setSolveFor("/physics/lb", false);

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u7684\u5355\u9897\u7c92\u5efa\u6a21");

    model
         .description("\u5355\u9897\u7c92\u65b9\u6cd5\u7528\u4e8e\u7b80\u5316\u591a\u5b54\u63d2\u5c42\u7535\u6781\u7684\u5efa\u6a21\u8fc7\u7a0b\uff0c\u901a\u5e38\u7528\u4e8e\u51cf\u8f7b\u8001\u5316\u6a21\u578b\u3001\u7535\u6c60\u7ec4\u4eff\u771f\u3001\u63a7\u5236\u7cfb\u7edf\u6216\u53c2\u6570\u4f30\u8ba1\u7b49\u5e94\u7528\u4e2d\u7684\u8ba1\u7b97\u8d1f\u8377\u3002\n\n\u672c\u6559\u7a0b\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u548c\u201c\u96c6\u603b\u7535\u6c60\u201d\u63a5\u53e3\u4e2d\u7684\u5404\u79cd\u7279\u5f81\u6765\u5b9a\u4e49\u5355\u9897\u7c92\u6a21\u578b\u3002\n\n\u5176\u4e2d\u8ba1\u7b97\u5e76\u6bd4\u8f83\u4e86\u4e0d\u540c\u6a21\u578b\u5728\u5145\u653e\u7535\u8d1f\u8f7d\u5faa\u73af\u4e0b\u7684\u7535\u6c60\u5355\u5143\u7535\u538b\u54cd\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("lib_single_particle.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
