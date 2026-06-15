/*
 * lib_test_cycle_surrogate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:02 by COMSOL 6.3.0.290. */
public class lib_test_cycle_surrogate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Applications");

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

    model.param().label("Parameters for Li-Ion Battery Model");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("epss_neg", "0.6", "Active electrode volume fraction, negative");
    model.param().set("epss_pos", "0.6", "Active electrode volume fraction, positive");
    model.param().set("epss_binder_neg", "0.1", "Binder volume fraction, negative");
    model.param().set("epss_binder_pos", "0.1", "Binder volume fraction, positive");
    model.param().set("epsl_neg", "1-epss_neg-epss_binder_neg", "Electrolyte volume fraction, negative");
    model.param().set("epsl_sep", "0.4", "Separator volume fraction");
    model.param().set("epsl_pos", "1-epss_pos-epss_binder_pos", "Electrolyte volume fraction, positive");
    model.param().set("sigmas_neg", "1[S/m]", "Effective electrode conductivity, negative");
    model.param().set("sigmas_pos", "1[S/m]", "Effective electrode conductivity, positive");
    model.param().set("rp_neg", "6[um]", "Electrode particle radius, negative");
    model.param().set("rp_pos", "5[um]", "Electrode particle radius, positive");
    model.param().set("cs_max_neg", "31507[mol/m^3]", "Maximum concentration, negative");
    model.param().set("cs_max_pos", "49000[mol/m^3]", "Maximum concentration, positive");
    model.param().set("i0_ref_neg", "10[A/m^2]", "Exchange current density at 50% lithiation, negative");
    model.param().set("i0_ref_pos", "10[A/m^2]", "Exchange current density at 50% lithiation, positive");
    model.param().set("L_sep", "20[um]", "Separator thickness");
    model.param().set("L_pos", "45[um]", "Positive electrode thickness");
    model.param()
         .set("L_neg", "(1-0.1)*epss_pos*cs_max_pos*L_pos/(0.8*epss_neg*cs_max_neg)", "Negative electrode thickness");
    model.param().set("L_ccs", "10[um]", "Thickness of current collectors");
    model.param().set("V_cell", "90[%]*(10.5[mm])^2*pi*70[mm]", "Active cell volume");
    model.param().set("A_cell", "V_cell/(L_neg+L_sep+L_pos+L_ccs/2)", "Cell area");
    model.param().set("soc_init", "0", "Initial SOC");
    model.param().set("T", "25[degC]", "Temperature");

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
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("fununit", new String[]{""});
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
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
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
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
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
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("source", "file");
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
    model.component("comp1").selection("sel1").label("Separator");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("Negative Electrode");
    model.component("comp1").selection("sel2").set(1);

    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").label("Positive Electrode");
    model.component("comp1").selection("sel3").set(3);

    model.component("comp1").material("mat3").selection().named("sel3");

    model.component("comp1").physics("liion").prop("Ac").set("Ac", "A_cell");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", "soc_init");
    model.component("comp1").physics("liion").prop("AdvancedSettings").set("logcl", true);
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().named("sel3");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").label("Porous Electrode - Negative");
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("FastAssembly", true);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_neg");
    model.component("comp1").physics("liion").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("liion").feature("pce2").label("Porous Electrode - Positive");
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
    model.component("comp1").selection("sel4").label("Negative CC");
    model.component("comp1").selection("sel4").set(1);

    model.component("comp1").physics("liion").feature("egnd1").selection().named("sel4");
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(4);

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(0);
    model.component("comp1").selection("sel5").label("Positive CC");
    model.component("comp1").selection("sel5").set(4);

    model.component("comp1").physics("liion").feature("ecd1").selection().named("sel5");
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "I_1C");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("Variables for Li-Ion Battery Model");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("sol_neg", "liion.soc_average_pce1", "Degree of lithiation, negative");
    model.component("comp1").variable("var1")
         .set("sol_pos", "liion.soc_average_pce2", "Degree of lithiation, positive");
    model.component("comp1").variable("var1")
         .set("E_ocp_neg", "mat2.def.Eeq(sol_neg)", "Open-circuit potential in negative electrode");
    model.component("comp1").variable("var1")
         .set("E_ocp_pos", "mat3.def.Eeq(sol_pos)", "Open-circuit potential in positive electrode");
    model.component("comp1").variable("var1").set("E_ocv_cell", "E_ocp_pos-E_ocp_neg", "Open-circuit cell voltage");
    model.component("comp1").variable("var1")
         .set("I_1C", "liion.I_1C_cell/A_cell", "1 h charge/discharge current density");
    model.component("comp1").variable("var1").set("I_app", "I_1C*C", "Applied current density");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").set("probename", "E_cell");
    model.component("comp1").probe("point1").set("type", "integral");
    model.component("comp1").probe("point1").selection().named("sel5");
    model.component("comp1").probe("point1").set("expr", "phis");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "Cell voltage");

    model.result().table().create("tbl1", "Table");

    model.component("comp1").probe("point1").set("table", "tbl1");

    model.component("comp1").physics("liion").feature("ecd1").set("nis", "I_app");
    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").label("Events: Battery Test Cycle");
    model.component("comp1").physics("ev").create("expll1", "ExplicitEventList", -1);
    model.component("comp1").physics("ev").feature("expll1").set("variableName", "C");
    model.component("comp1").physics("ev").feature("expll1").set("variableDescription", "C rate");
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "1e-6", 0, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", "C0", 0, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", 10, 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", "C1", 1, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", 20, 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", "C2", 2, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", 30, 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", "C3", 3, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 4, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 4, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 4, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", "0[s]", 4, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("reinitializationExpression", 0, 4, 0);
    model.component("comp1").physics("ev").feature("expll1").setIndex("time", 40, 4, 0);
    model.component("comp1").physics("ev").feature("expll1").set("ignoreIdenticalConsecutive", true);

    model.param().create("par2");
    model.param("par2").label("Parameters for Test Cycle");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("C0", "-5", "C-rate, first step");
    model.param("par2").set("C1", "0", "C-rate, second step");
    model.param("par2").set("C2", "0", "C-rate, third step");
    model.param("par2").set("C3", "5", "C-rate, fourth step");
    model.param("par2").set("E_stop", "2.6[V]", "Stop (threshold) voltage");
    model.param().create("par3");
    model.param("par3").label("Parameters Set from App");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("C0_app_phys", "C0", "C-rate for first step, set from app");
    model.param("par3").set("C1_app_phys", "C1", "C-rate for second step, set from app");
    model.param("par3").set("C2_app_phys", "C2", "C-rate for third step, set from app");
    model.param("par3").set("C3_app_phys", "C3", "C-rate for fourth step, set from app");
    model.param("par3").set("soc_init_app_phys", "0.5", "Initial state of charge, set from app");
    model.param("par3").set("C0_app_init", "-5", "Initial C-rate for first step, used in app");
    model.param("par3").set("C1_app_init", "0", "Initial C-rate for second step, used in app");
    model.param("par3").set("C2_app_init", "0", "Initial C-rate for third step, used in app");
    model.param("par3").set("C3_app_init", "5", "Initial C-rate for fourth step, used in app");
    model.param("par3")
         .set("soc_init_app_init", "0.5", "Initial value for the initial state of charge, used in app");
    model.param().create("par4");
    model.param("par4").label("Parameters for Plot Axis Limits");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("xmin", "0", "x minimum, used for app graphics");
    model.param("par4").set("xmax", "40", "x maximum, used for app graphics");
    model.param("par4").set("yminE", "3.4", "y minimum, cell voltage, used for app graphics");
    model.param("par4").set("ymaxE", "4.2", "y maximum, cell voltage, used for app graphics");
    model.param("par4").set("yminR", "0", "y minimum, internal resistance, used for app graphics");
    model.param("par4").set("ymaxR", "0.02", "y maximum, internal resistance, used for app graphics");
    model.param("par4").set("ymin_second", "-6", "y minimum, secondary y, used for app graphics");
    model.param("par4").set("ymax_second", "6", "y maximum, secondary y, used for app graphics");

    model.study("std1").label("Study 1: Data Generation");
    model.study("std1").create("sm", "SurrogateModelTraining");
    model.study("std1").feature("sm").setIndex("funcname", "", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "E_cell", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "comp1.E_cell", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "configure");
    model.study("std1").feature("sm").setEntry("innermostparameter", "0", "interp");
    model.study("std1").feature("sm").setEntry("interptimes", "0", "30*(1+1e-9) range(30.1, 0.1, 40)");
    model.study("std1").feature("sm").setIndex("funcname", "", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "E_ocv_cell", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "comp1.E_ocv_cell", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "configure");
    model.study("std1").feature("sm").setEntry("innermostparameter", "1", "interp");
    model.study("std1").feature("sm").setEntry("interptimes", "1", "30*(1+1e-9) range(30.1, 0.1, 40)");
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 0);
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 0);
    model.study("std1").feature("sm").setIndex("pname", "C0", 0);
    model.study("std1").feature("sm").setEntry("lboundselection", "col1", "-5");
    model.study("std1").feature("sm").setEntry("uboundselection", "col1", "5");
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 1);
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 1);
    model.study("std1").feature("sm").setIndex("pname", "C1", 1);
    model.study("std1").feature("sm").setEntry("lboundselection", "col2", "-5");
    model.study("std1").feature("sm").setEntry("uboundselection", "col2", "5");
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 2);
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 2);
    model.study("std1").feature("sm").setIndex("pname", "C2", 2);
    model.study("std1").feature("sm").setEntry("lboundselection", "col3", "-5");
    model.study("std1").feature("sm").setEntry("uboundselection", "col3", "5");
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 3);
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 3);
    model.study("std1").feature("sm").setIndex("pname", "C3", 3);
    model.study("std1").feature("sm").setEntry("lboundselection", "col4", "-5");
    model.study("std1").feature("sm").setEntry("uboundselection", "col4", "5");
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 4);
    model.study("std1").feature("sm").setIndex("pname", "A_cell", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2", 4);
    model.study("std1").feature("sm").setIndex("pname", "soc_init", 4);
    model.study("std1").feature("sm").setEntry("lboundselection", "col5", "0.01");
    model.study("std1").feature("sm").setEntry("uboundselection", "col5", "1");
    model.study("std1").feature("sm").set("nsolvenonadp", 1200);
    model.study("std1").feature("sm").set("useseed", "manual");
    model.study("std1").feature("sm").set("errorhandling", "later");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tstepsclosest");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("initialstepbdf", "1e-6");
    model.sol("sol1").feature("t1").set("eventout", false);

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "0 30*(1+1e-9) range(30.1, 0.1, 40)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("Parametric Solutions 1");

    model.batch("pd1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("point1").genResult("none");

    model.batch("pd1").run("compute");
    model.batch("pd1").set("studyinputparamname", new String[]{});
    model.batch("pd1").set("parameterselection", new String[][]{{}, {}});
    model.batch("pd1").set("includeintraining", new String[][]{{}, {}});
    model.batch("pd1").set("studyinputparamname", new String[]{});
    model.batch("pd1").set("parameterselection", new String[][]{{}, {}});
    model.batch("pd1").set("includeintraining", new String[][]{{}, {}});

    model.func().create("dnn1", "DNN");
    model.func("dnn1").label("Deep Neural Network");
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 1, 1});
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"Input, Input features=0", "Output, Output features=0, Activation=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{2}, -1);
    model.func("dnn1").move("outfeatures", new int[]{2}, -1);
    model.func("dnn1").move("activation", new int[]{2}, -1);
    model.func("dnn1").move("layerDescription", new int[]{2}, -1);
    model.func("dnn1").setIndex("outfeatures", "25", 1);
    model.func("dnn1").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.func("dnn1").set("outfeatures", new int[]{1, 25, 1, 1});

    return model;
  }

  public static Model run3(Model model) {
    model.func("dnn1").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.func("dnn1")
         .set("layerDescription", new String[]{"Input, Input features=0", "Hidden, Output features=25, Activation=tanh", "Output, Output features=0, Activation=tanh", ""});
    model.func("dnn1").move("layertype", new int[]{3}, -1);
    model.func("dnn1").move("outfeatures", new int[]{3}, -1);
    model.func("dnn1").move("activation", new int[]{3}, -1);
    model.func("dnn1").move("layerDescription", new int[]{3}, -1);
    model.func("dnn1").setIndex("outfeatures", "25", 2);
    model.func("dnn1").setIndex("activation", "none", 3);
    model.func("dnn1").set("source", "resultTable");
    model.func("dnn1").setEntry("args", "col1", "C0");
    model.func("dnn1").setEntry("descr", "col1", "C rate");
    model.func("dnn1").setEntry("args", "col2", "C1");
    model.func("dnn1").setEntry("descr", "col2", "C rate");
    model.func("dnn1").setEntry("args", "col3", "C2");
    model.func("dnn1").setEntry("descr", "col3", "C rate");
    model.func("dnn1").setEntry("args", "col4", "C3");
    model.func("dnn1").setEntry("descr", "col4", "C rate");
    model.func("dnn1").setEntry("args", "col5", "soc_init");
    model.func("dnn1").setEntry("descr", "col5", "Initial state of charge");
    model.func("dnn1").setEntry("args", "col6", "t");
    model.func("dnn1").setEntry("descr", "col6", "Time");
    model.func("dnn1").setEntry("unit", "col6", "s");
    model.func("dnn1").setEntry("columnType", "col7", "value");
    model.func("dnn1").setEntry("funcs", "col7", "E_dnn");
    model.func("dnn1").setEntry("descr", "col7", "Cell voltage");
    model.func("dnn1").setEntry("unit", "col7", "V");
    model.func("dnn1").setEntry("funcs", "col8", "E_ocv_dnn");
    model.func("dnn1").setEntry("descr", "col8", "Cell open circuit voltage");
    model.func("dnn1").setEntry("unit", "col8", "V");
    model.func("dnn1").set("epochs", 20000);
    model.func("dnn1").set("validation", "fraction");
    model.func("dnn1").run();
    model.func("dnn1").set("lr", "1e-4");
    model.func("dnn1").continueRun();
    model.func("dnn1").setIndex("plotaxis", false, 0);
    model.func("dnn1").setIndex("plotaxis", false, 1);
    model.func("dnn1").setIndex("plotaxis", false, 2);
    model.func("dnn1").setIndex("plotaxis", true, 5);
    model.func("dnn1").setIndex("plotfixedvalue", "C0_app_phys", 0);
    model.func("dnn1").setIndex("plotfixedvalue", "C1_app_phys", 1);
    model.func("dnn1").setIndex("plotfixedvalue", "C2_app_phys", 2);
    model.func("dnn1").setIndex("plotfixedvalue", "C3_app_phys", 3);
    model.func("dnn1").setIndex("plotfixedvalue", "soc_init_app_phys", 4);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("Analytic: Arbitrary Test Cycle E_cell");
    model.component("comp1").func("an1").set("funcname", "E_cell_an");
    model.component("comp1").func("an1")
         .set("expr", "if(t <= 10, E_dnn(0,0,0,C0,soc_init,t+30), if(t <= 20, E_dnn(0,0,C0,C1,soc_init,t+20), if(t <= 30, E_dnn(0,C0,C1,C2,soc_init,t+10), if(t<=40, E_dnn(C0,C1,C2,C3,soc_init,t),0))))");
    model.component("comp1").func("an1").set("args", "C0, C1, C2, C3, soc_init, t");
    model.component("comp1").func("an1").set("fununit", "V");
    model.component("comp1").func("an1").setIndex("argunit", 1, 0);
    model.component("comp1").func("an1").setIndex("argunit", 1, 1);
    model.component("comp1").func("an1").setIndex("argunit", 1, 2);
    model.component("comp1").func("an1").setIndex("argunit", 1, 3);
    model.component("comp1").func("an1").setIndex("argunit", 1, 4);
    model.component("comp1").func("an1").setIndex("argunit", "s", 5);
    model.component("comp1").func("an1").setIndex("plotaxis", false, 0);
    model.component("comp1").func("an1").setIndex("plotaxis", false, 1);
    model.component("comp1").func("an1").setIndex("plotaxis", false, 2);
    model.component("comp1").func("an1").setIndex("plotaxis", true, 5);
    model.component("comp1").func("an1").setIndex("plotargs", 40, 5, 2);
    model.component("comp1").func("an1").setIndex("plotfixedvalue", "C0_app_phys", 0);
    model.component("comp1").func("an1").setIndex("plotfixedvalue", "C1_app_phys", 1);
    model.component("comp1").func("an1").setIndex("plotfixedvalue", "C2_app_phys", 2);
    model.component("comp1").func("an1").setIndex("plotfixedvalue", "C3_app_phys", 3);
    model.component("comp1").func("an1").setIndex("plotfixedvalue", "soc_init_app_phys", 4);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").label("Analytic: Arbitrary Test Cycle E_ocv");
    model.component("comp1").func("an2").set("funcname", "E_ocv_cell_an");
    model.component("comp1").func("an2")
         .set("expr", "if(t <= 10, E_ocv_dnn(0,0,0,C0,soc_init,t+30), if(t <= 20, E_ocv_dnn(0,0,C0,C1,soc_init,t+20), if(t <= 30, E_ocv_dnn(0,C0,C1,C2,soc_init,t+10), if(t<=40, E_ocv_dnn(C0,C1,C2,C3,soc_init,t),0))))");
    model.component("comp1").func("an2").set("args", "C0, C1, C2, C3, soc_init, t");
    model.component("comp1").func("an2").set("fununit", "V");
    model.component("comp1").func("an2").setIndex("argunit", 1, 0);
    model.component("comp1").func("an2").setIndex("argunit", 1, 1);
    model.component("comp1").func("an2").setIndex("argunit", 1, 2);
    model.component("comp1").func("an2").setIndex("argunit", 1, 3);
    model.component("comp1").func("an2").setIndex("argunit", 1, 4);
    model.component("comp1").func("an2").setIndex("argunit", "s", 5);
    model.component("comp1").func("an2").setIndex("plotaxis", false, 0);
    model.component("comp1").func("an2").setIndex("plotaxis", false, 1);
    model.component("comp1").func("an2").setIndex("plotaxis", false, 2);
    model.component("comp1").func("an2").setIndex("plotaxis", true, 5);
    model.component("comp1").func("an2").setIndex("plotargs", 40, 5, 2);
    model.component("comp1").func("an2").setIndex("plotfixedvalue", "C0_app_phys", 0);
    model.component("comp1").func("an2").setIndex("plotfixedvalue", "C1_app_phys", 1);
    model.component("comp1").func("an2").setIndex("plotfixedvalue", "C2_app_phys", 2);
    model.component("comp1").func("an2").setIndex("plotfixedvalue", "C3_app_phys", 3);
    model.component("comp1").func("an2").setIndex("plotfixedvalue", "soc_init_app_phys", 4);
    model.component("comp1").func().create("an3", "Analytic");
    model.component("comp1").func("an3").label("Analytic: C rate");
    model.component("comp1").func("an3").set("funcname", "C");
    model.component("comp1").func("an3")
         .set("expr", "if(t <= 10, C0, if(t >=10 && t <= 20, C1, if(t >= 20 && t <= 30, C2, if(t>=20 && t<=40, C3, 0))))");
    model.component("comp1").func("an3").set("args", "C0, C1, C2, C3, t");
    model.component("comp1").func("an3").set("fununit", "1");
    model.component("comp1").func("an3").setIndex("argunit", 1, 0);
    model.component("comp1").func("an3").setIndex("argunit", 1, 1);
    model.component("comp1").func("an3").setIndex("argunit", 1, 2);
    model.component("comp1").func("an3").setIndex("argunit", 1, 3);
    model.component("comp1").func("an3").setIndex("argunit", "s", 4);
    model.component("comp1").func("an3").setIndex("plotaxis", false, 0);
    model.component("comp1").func("an3").setIndex("plotaxis", false, 1);
    model.component("comp1").func("an3").setIndex("plotaxis", false, 2);
    model.component("comp1").func("an3").setIndex("plotaxis", true, 4);
    model.component("comp1").func("an3").setIndex("plotargs", 40, 4, 2);
    model.component("comp1").func("an3").setIndex("plotfixedvalue", "C0_app_phys", 0);
    model.component("comp1").func("an3").setIndex("plotfixedvalue", "C1_app_phys", 1);
    model.component("comp1").func("an3").setIndex("plotfixedvalue", "C2_app_phys", 2);
    model.component("comp1").func("an3").setIndex("plotfixedvalue", "C3_app_phys", 3);

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
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").label("Study 2: App Computation");
    model.study("std2").feature("time").set("tlist", "range(0, 0.1, 40)");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "A_cell", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "C0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "C0_app_phys", 0);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m^2", 1);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m^2", 1);
    model.study("std2").feature("param").setIndex("pname", "C1", 1);
    model.study("std2").feature("param").setIndex("plistarr", "C1_app_phys", 1);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 2);
    model.study("std2").feature("param").setIndex("plistarr", "", 2);
    model.study("std2").feature("param").setIndex("punit", "m^2", 2);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 2);
    model.study("std2").feature("param").setIndex("plistarr", "", 2);
    model.study("std2").feature("param").setIndex("punit", "m^2", 2);
    model.study("std2").feature("param").setIndex("pname", "C2", 2);
    model.study("std2").feature("param").setIndex("plistarr", "C2_app_phys", 2);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 3);
    model.study("std2").feature("param").setIndex("plistarr", "", 3);
    model.study("std2").feature("param").setIndex("punit", "m^2", 3);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 3);
    model.study("std2").feature("param").setIndex("plistarr", "", 3);
    model.study("std2").feature("param").setIndex("punit", "m^2", 3);
    model.study("std2").feature("param").setIndex("pname", "C3", 3);
    model.study("std2").feature("param").setIndex("plistarr", "C3_app_phys", 3);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 4);
    model.study("std2").feature("param").setIndex("plistarr", "", 4);
    model.study("std2").feature("param").setIndex("punit", "m^2", 4);
    model.study("std2").feature("param").setIndex("pname", "A_cell", 4);
    model.study("std2").feature("param").setIndex("plistarr", "", 4);
    model.study("std2").feature("param").setIndex("punit", "m^2", 4);
    model.study("std2").feature("param").setIndex("pname", "soc_init", 4);
    model.study("std2").feature("param").setIndex("plistarr", "soc_init_app_phys", 4);
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol5").feature("t1").set("initialstepbdf", "1e-6");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("Parametric Solutions 2");

    model.batch("p1").feature("so1").set("psol", "sol7");

    model.component("comp1").probe("point1").genResult("none");

    model.batch("p1").run("compute");

//    To import content from file, use:
//    model.result().param().loadFile("FILENAME");
    model.result().param()
         .set("C0_app", "C0_app_init", "C-rate for first segment, used as input for surrogate model");
    model.result().param()
         .set("C1_app", "C1_app_init", "C-rate for second segment, used as input for surrogate model");
    model.result().param()
         .set("C2_app", "C2_app_init", "C-rate for third segment, used as input for surrogate model");
    model.result().param()
         .set("C3_app", "C3_app_init", "C-rate for fourth segment, used as input for surrogate model");
    model.result().param()
         .set("soc_init_app", "soc_init_app_init", "Initial state of charge, used as input for surrogate model");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Cell Voltage");
    model.result("pg2").set("data", "dset5");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Time (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Voltage (V)");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("yseclabelactive", true);
    model.result("pg2").set("yseclabel", "C-Rate (1)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", "xmin");
    model.result("pg2").set("xmax", "xmax");
    model.result("pg2").set("ymin", "yminE");
    model.result("pg2").set("ymax", "ymaxE");
    model.result("pg2").set("yminsec", "ymin_second");
    model.result("pg2").set("ymaxsec", "ymax_second");
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").set("legendposoutside", "bottom");
    model.result("pg2").set("legendrowcount", 2);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "E_cell", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Physics, Cell Voltage", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "E_ocv_cell", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "Physics, Open-Circuit Cell Voltage", 1);
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").feature("glob1").label("Physics, Cell Voltage and Open-Circuit Cell Voltage");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").label("Physics, C-Rate");
    model.result("pg2").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob2").setIndex("expr", "C", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "Physics, C-Rate", 0);
    model.result("pg2").feature("glob2").set("linecolor", "red");
    model.result("pg2").feature("glob2").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("markerpos", "datapoints");
    model.result("pg2").feature("glob3").set("linewidth", "preference");
    model.result("pg2").feature("glob3").label("Surrogate Model, Cell Voltage and Open-Circuit Cell Voltage");
    model.result("pg2").feature("glob3")
         .setIndex("expr", "E_cell_an(C0_app,C1_app,C2_app,C3_app,soc_init_app,t)", 0);
    model.result("pg2").feature("glob3").setIndex("descr", "Surrogate Model, Cell Voltage", 0);
    model.result("pg2").feature("glob3")
         .setIndex("expr", "E_ocv_cell_an(C0_app,C1_app,C2_app,C3_app,soc_init_app,t)", 1);
    model.result("pg2").feature("glob3").setIndex("descr", "Surrogate Model, Open-Circuit Cell Voltage", 1);
    model.result("pg2").feature("glob3").set("linestyle", "dashed");
    model.result("pg2").feature("glob3").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob3").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").create("glob4", "Global");
    model.result("pg2").feature("glob4").set("markerpos", "datapoints");
    model.result("pg2").feature("glob4").set("linewidth", "preference");
    model.result("pg2").feature("glob4").label("Surrogate Model, C-Rate");
    model.result("pg2").feature("glob4").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob4").setIndex("expr", "C(C0_app,C1_app,C2_app,C3_app,t)", 0);
    model.result("pg2").feature("glob4").setIndex("descr", "Surrogate Model, C-Rate", 0);
    model.result("pg2").feature("glob4").set("linestyle", "dashed");
    model.result("pg2").feature("glob4").set("linecolor", "red");
    model.result("pg2").feature("glob4").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("glob2").active(false);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Internal Resistance");
    model.result("pg3").set("ylabel", "Internal Resistance (\u03a9)");
    model.result("pg3").set("ymin", "yminR");
    model.result("pg3").set("ymax", "ymaxR");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").label("Physics, Internal Resistance");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").setIndex("expr", "(E_cell-E_ocv_cell)/(I_app*A_cell)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Physics, Internal Resistance", 0);
    model.result("pg3").run();
    model.result("pg3").feature("glob3").label("Surrogate Model, Internal Resistance");
    model.result("pg3").feature("glob3").set("expr", new String[]{});
    model.result("pg3").feature("glob3").set("descr", new String[]{});
    model.result("pg3").feature("glob3")
         .setIndex("expr", "(E_cell_an(C0_app,C1_app,C2_app,C3_app,soc_init_app,t)-E_ocv_cell_an(C0_app,C1_app,C2_app,C3_app,soc_init_app, t))/(I_1C*C(C0_app,C1_app,C2_app,C3_app,t)*A_cell)", 0);
    model.result("pg3").feature("glob3").setIndex("descr", "Surrogate Model, Internal Resistance", 0);
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///lib_test_cycle_surrogate.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .label("Surrogate Model for an NMC111/Graphite Li-ion Battery Test Cycle");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "This app demonstrates the usage of a surrogate model function for predicting the cell voltage, cell open circuit voltage and internal resistance of an NMC111/graphite battery cell undergoing a battery test cycle. The surrogate function, a Deep Neural Network, has been fitted to a subset of the possible input data values. Five input data values can be set: the current in four segments of the cycle and the initial state of charge of the battery cell. The low computational cost of evaluating the surrogate function allows knobs to be used to interactively combine the input values and predict the cell voltage and internal resistance. Once a combination of values has been selected, the prediction of the surrogate model can be verified by computing the actual physical Li-ion battery model.");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("Software Information");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("Software Properties from Root");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("Input Data");
    model.result().report("rpt1").feature("sec2").feature().create("head1", "Heading");
    model.result().report("rpt1").feature("sec2").feature("head1")
         .label("Input Parameters for Li-Ion Battery Physical Model");
    model.result().report("rpt1").feature("sec2").feature("head1")
         .set("text", "Input Parameters for Li-Ion Battery Physical Model");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").label("Parameters for Embedded Model");
    model.result().report("rpt1").feature("sec2").feature().create("param2", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param2").label("Parameters for Test Cycle");
    model.result().report("rpt1").feature("sec2").feature("param2").set("noderef", "par2");
    model.result().report("rpt1").feature("sec2").feature().create("head2", "Heading");
    model.result().report("rpt1").feature("sec2").feature("head2")
         .label("Input Variables for Li-Ion Battery Physical Model");
    model.result().report("rpt1").feature("sec2").feature("head2")
         .set("text", "Input Variables for Li-Ion Battery Physical Model");
    model.result().report("rpt1").feature("sec2").feature().create("var1", "Variables");
    model.result().report("rpt1").feature("sec2").feature("var1").label("Variables for Physics-Based Model");
    model.result().report("rpt1").feature("sec2").feature().create("head3", "Heading");
    model.result().report("rpt1").feature("sec2").feature("head3")
         .label("Generation of Input Data for Fitting the Surrogate Model");
    model.result().report("rpt1").feature("sec2").feature("head3")
         .set("text", "Generation of Input Data for Fitting the Surrogate Model");
    model.result().report("rpt1").feature("sec2").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec2").feature("txt1")
         .set("text", "The first study in the model is used to generate training data for the surrogate model. The settings used are shown in the current section.");
    model.result().report("rpt1").feature("sec2").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec2").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("std1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("std1").setIndex("children", true, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("std1").setIndex("children", true, 1, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("Surrogate Model");
    model.result().report("rpt1").feature("sec3").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec3").feature("txt1")
         .set("text", "The current section shows the options used to train the surrogate model. It also shows an example of the predicted test cycle.");
    model.result().report("rpt1").feature("sec3").feature().create("func1", "Functions");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("Results");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("Cell Voltage");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("Internal Resistance");

    model.title(null);

    model.description("");

    model.label("lib_test_cycle_surrogate_embedded.mph");

    model.title("\u7535\u6c60\u6d4b\u8bd5\u5faa\u73af\u7684\u4ee3\u7406\u6a21\u578b\u8bad\u7ec3");

    model
         .description("\u672c App \u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4ee3\u7406\u6a21\u578b\u51fd\u6570\u6765\u9884\u6d4b NMC111/\u77f3\u58a8\u7535\u6c60\u5355\u5143\u5728\u7ecf\u5386\u7535\u6c60\u6d4b\u8bd5\u5faa\u73af\u65f6\u7684\u7535\u6c60\u7535\u538b\u3001\u5f00\u8def\u7535\u538b\u548c\u5185\u963b\u3002\n\n\u4ee3\u7406\u51fd\u6570\uff0c\u5373\u6df1\u5ea6\u795e\u7ecf\u7f51\u7edc\uff0c\u5df2\u62df\u5408\u5230\u4e00\u4e9b\u53ef\u80fd\u7684\u8f93\u5165\u6570\u636e\u503c\u7684\u5b50\u96c6\u4e2d\u3002\u60a8\u53ef\u4ee5\u8bbe\u7f6e\u4e94\u4e2a\u8f93\u5165\u6570\u636e\u503c\uff1a\u5faa\u73af\u4e2d\u56db\u4e2a\u9636\u6bb5\u7684\u7535\u6d41\u548c\u7535\u6c60\u5355\u5143\u7684\u521d\u59cb\u8377\u7535\u72b6\u6001\u3002\u7531\u4e8e\u4ee3\u7406\u51fd\u6570\u7684\u8ba1\u7b97\u6210\u672c\u4f4e\uff0c\u56e0\u6b64\u53ef\u4ee5\u4f7f\u7528\u65cb\u94ae\u6765\u4ea4\u4e92\u5730\u7ec4\u5408\u8fd9\u4e9b\u8f93\u5165\u503c\uff0c\u4ece\u800c\u9884\u6d4b\u7535\u6c60\u7535\u538b\u548c\u5185\u963b\u3002\n\n\u9009\u5b9a\u4e00\u7ec4\u503c\u540e\uff0c\u4fbf\u53ef\u4ee5\u901a\u8fc7\u8ba1\u7b97\u5b9e\u9645\u7684\u7269\u7406\u9502\u79bb\u5b50\u7535\u6c60\u6a21\u578b\u6765\u9a8c\u8bc1\u4ee3\u7406\u6a21\u578b\u7684\u9884\u6d4b\u7ed3\u679c\u3002");

    model.label("lib_test_cycle_surrogate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
