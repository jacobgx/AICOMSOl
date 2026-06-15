/*
 * li_plating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_plating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Aging_and_Abuse");

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
    model.param()
         .set("epss_pos", "0.6", "\u6d3b\u6027\u7535\u6781\u6750\u6599\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("epsbinder_pos", "0.1*epss_pos", "\u9ecf\u5408\u5242\uff08\u5305\u62ec\u5bfc\u7535\u586b\u6599\uff09\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("epsl_pos", "1-epss_pos-epsbinder_pos", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("L_pos", "70[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("rp_pos", "6[um]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param()
         .set("epss_neg", "0.6", "\u6d3b\u6027\u7535\u6781\u6750\u6599\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("epsl_neg", "1-epss_neg", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param()
         .set("L_neg", "L_pos*cs_max_pos*epss_pos*(1-socminpos)*(1-Li_loss)/((1-Q_excess)*epss_neg*cs_max_neg*(1-socminneg))", "\u6839\u636e\u7535\u6781\u5bb9\u91cf\u5e73\u8861\u8ba1\u7b97\u7684\u8d1f\u6781\u539a\u5ea6");
    model.param().set("rp_neg", "6[um]", "\u8d1f\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("L_sep", "30[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("epsl_sep", "0.5", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u9694\u819c");
    model.param().set("L_CC_pos", "5[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_CC_neg", "5[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("Jelly_vol", "0.95", "\u7535\u6c60\u4e2d\u7684\u76f8\u5bf9\u5377\u82af\u4f53\u79ef");
    model.param().set("L_batt", "L_CC_neg+L_CC_pos+L_neg+L_sep+L_neg", "\u7535\u6c60\u5355\u5143\u539a\u5ea6");
    model.param().set("Vol", "(18[mm]/2)^2*pi*65[mm]", "\u7535\u6c60\u4f53\u79ef");
    model.param().set("A_cell", "Vol*Jelly_vol/L_batt", "\u7535\u6c60\u5355\u5143\u533a\u57df");
    model.param().set("C_rate", "1", "\u500d\u7387");
    model.param()
         .set("cs_max_pos", "50060[mol/m^3]", "\u6700\u5927\u6b63\u6781\u6d3b\u6027\u7535\u6781\u6750\u6599\u627f\u8f7d\u5bb9\u91cf");
    model.param()
         .set("cs_max_neg", "31507[mol/m^3]", "\u6700\u5927\u8d1f\u6781\u6d3b\u6027\u7535\u6781\u6750\u6599\u627f\u8f7d\u5bb9\u91cf");
    model.param()
         .set("cLicov", "rhoLi/MLi*L_Limono", "\u8868\u9762\u7535\u9540\u5355\u5c42\u4e2d\u9502\u7684\u6469\u5c14\u91cf\uff0c\u8d1f\u6781\u6d3b\u6027\u7535\u6781\u6750\u6599");
    model.param()
         .set("socminpos", "0.222", "\u9502\u5316\u4e0b\u9650\uff0c\u6b63\u6781\u6d3b\u6027\u7535\u6781\u6750\u6599");
    model.param().set("Q_excess", "0.25", "\u8d1f\u6781\u8fc7\u5269\u627f\u8f7d\u5bb9\u91cf");
    model.param()
         .set("Li_loss", "0.05", "\u5f62\u6210\u5faa\u73af\u671f\u95f4\u7684\u521d\u59cb\u9502\u5e93\u5b58\u635f\u5931");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param().set("T_ref_neg", "298[K]", "\u53c2\u8003\u6e29\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("T_ref_pos", "298[K]", "\u53c2\u8003\u6e29\u5ea6\uff0c\u6b63\u6781");
    model.param().set("Ea_neg", "77.05[kJ/mol]", "\u52a8\u529b\u5b66\u6d3b\u5316\u80fd\uff0c\u8d1f\u6781");
    model.param().set("Ea_pos", "61[kJ/mol]", "\u52a8\u529b\u5b66\u6d3b\u5316\u80fd\uff0c\u6b63\u6781");
    model.param().set("I_taper", "0.5[A]", "\u9010\u6e10\u51cf\u5c0f\u7684\u7535\u6d41");
    model.param().set("Vhigh", "4.2[V]", "\u622a\u6b62\u7535\u538b\u4e0a\u9650");
    model.param().set("Vlow", "2.7[V]", "\u622a\u6b62\u7535\u538b\u4e0b\u9650");
    model.param().set("MLi", "6.941[g/mol]", "\u9502\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("rhoLi", "0.534[g/cm^3]", "\u9502\u7684\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_LiMetal", "1[A/m^2]", "\u9502\u7535\u9540/\u5265\u79bb\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("Ydead", "0.1", "\u5265\u79bb\u8fc7\u7a0b\u4e2d\u5f62\u6210\u7684\u6b7b\u9502\u7684\u5206\u6570");
    model.param()
         .set("socminneg", "0.025", "\u9502\u5316\u4e0b\u9650\uff0c\u8d1f\u6781\u6d3b\u6027\u7535\u6781\u6750\u6599");
    model.param().set("SOCcell_init", "0", "\u521d\u59cb\u7535\u6c60 SOC");
    model.param()
         .set("i0_ref_neg", "10[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0_ref_pos", "10[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("sigmas_neg", "1[S/m]", "\u6709\u6548\u7535\u6781\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "1[S/m]", "\u6709\u6548\u7535\u6781\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("L_Limono", "1e-10[m]", "\u9540\u9502\u5355\u5c42\u539a\u5ea6");

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

    return model;
  }

  public static Model run2(Model model) {
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
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
    model.component("comp1").material("mat3").label("NMC 811, LiNi0.8Mn0.1Co0.1O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.2228930343076256", "4.256817954840526"}, 
         {"0.23718770939025557", "4.2212385803217725"}, 
         {"0.2503742701253948", "4.198216215024365"}, 
         {"0.2635608308605341", "4.184354581468334"}, 
         {"0.2767473915956734", "4.175555457558853"}, 
         {"0.28993395233081265", "4.169287588472648"}, 
         {"0.3031205130659519", "4.163501863162304"}, 
         {"0.3163070738010912", "4.156631314356272"}, 
         {"0.3294936345362305", "4.145300935623516"}, 
         {"0.3426801952713697", "4.130836622347658"}, 
         {"0.355866756006509", "4.113841054248525"}, 
         {"0.3690533167416483", "4.09395262349422"}, 
         {"0.38223987747678756", "4.0746668724597415"}, 
         {"0.3954264382119268", "4.056104337089057"}, 
         {"0.4086129989470661", "4.037903409550268"}, 
         {"0.4217995596822054", "4.021944450569238"}, 
         {"0.43498612041734463", "4.007287279783036"}, 
         {"0.44817268115248393", "3.9945104697226936"}, 
         {"0.4613592418876232", "3.9798050845589046"}, 
         {"0.47454580262276247", "3.96497916345115"}, 
         {"0.4877323633579017", "3.9507559220632222"}, 
         {"0.500918924093041", "3.9348451774597786"}, 
         {"0.5141054848281803", "3.918090681248576"}, 
         {"0.5272920455633195", "3.901215649093408"}, 
         {"0.5404786062984588", "3.884581688826171"}, 
         {"0.5536651670335981", "3.8661396893994517"}, 
         {"0.5668517277687374", "3.850108408852042"}, 
         {"0.5800382885038766", "3.834920879912391"}, 
         {"0.593224849239016", "3.819612815028774"}, 
         {"0.6064114099741551", "3.806233325248605"}, 
         {"0.6195979707092945", "3.795023482459815"}, 
         {"0.6327845314444338", "3.7852600709986106"}, 
         {"0.6459710921795729", "3.77646094708913"}, 
         {"0.6591576529147123", "3.7660948559080984"}, 
         {"0.6723442136498515", "3.7569341241667216"}, 
         {"0.6855307743849908", "3.748376072145172"}, 
         {"0.6987173351201301", "3.7407823076753464"}, 
         {"0.7119038958552694", "3.7321037197098312"}, 
         {"0.7250904565904086", "3.724148347408109"}, 
         {"0.738277017325548", "3.7154697594425943"}, 
         {"0.7514635780606872", "3.7046215244857006"}, 
         {"0.7646501387958264", "3.69582240057622"}, 
         {"0.7778366995309657", "3.686541132890878"}, 
         {"0.791023260266105", "3.6770187933176044"}, 
         {"0.8042098210012443", "3.6672553818564"}, 
         {"0.8173963817363835", "3.6556839312357132"}, 
         {"0.8305829424715228", "3.643871408727096"}, 
         {"0.8437695032066621", "3.633505317546064"}, 
         {"0.8569560639418013", "3.6226570825891704"}, 
         {"0.8701426246769406", "3.6130142070719313"}, 
         {"0.8833291854120799", "3.603009723722796"}, 
         {"0.8965157461472192", "3.592643632541764"}, 
         {"0.9097023068823584", "3.585049868071939"}, 
         {"0.9228888676174977", "3.5790230708736646"}, 
         {"0.9351335311572699", "3.5724538619275457"}, 
         {"0.9505178520149323", "3.5661257248693574"}, 
         {"0.9624485498229155", "3.559857855783152"}, 
         {"0.9756351105580549", "3.5525051632012574"}, 
         {"0.9888216712931941", "3.541536392300398"}, 
         {"0.998240643246865", "3.488540755603573"}, 
         {"0.9994965061740211", "3.3640592684056174"}, 
         {"1", "3.0"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.220410330184718", "0.014014955374719168"}, 
         {"0.23880574126341392", "0.017971951837248937"}, 
         {"0.2546142976591682", "0.024888006339594898"}, 
         {"0.27162047196369177", "0.034054905194386115"}, 
         {"0.282638556724369", "0.036404922469418094"}, 
         {"0.3020399668464311", "0.026993759520293023"}, 
         {"0.3142556695158776", "0.020431501191878212"}, 
         {"0.33365707963793967", "0.011727335839959649"}, 
         {"0.35521420199578646", "0.0148088719318831"}, 
         {"0.3681484754104945", "0.017914441947786144"}, 
         {"0.3861127440420335", "0.021513559747933717"}, 
         {"0.40838843714514184", "0.01892213947907556"}, 
         {"0.4213227105598499", "0.01702025282904991"}, 
         {"0.43856840844612727", "0.013603757832495345"}, 
         {"0.46156267229449716", "0.005230230696620208"}, 
         {"0.47449694570920525", "-0.0006265898806357695"}, 
         {"0.49389835583126734", "-0.007070755018626973"}, 
         {"0.5161740489343756", "-0.004108523109428802"}, 
         {"0.5291083223490838", "-0.001082689664639258"}, 
         {"0.5463540202353612", "0.002312976397604097"}, 
         {"0.569348284083731", "0.004079283221664273"}, 
         {"0.582282557498439", "0.004952229246388926"}, 
         {"0.6009653968752395", "0.004819533211418328"}, 
         {"0.6239596607236095", "-0.006710676178650704"}, 
         {"0.6368939341383175", "-0.014536990062410493"}, 
         {"0.6541396320245949", "-0.02419065684384447"}, 
         {"0.6771338958729649", "-0.029277265326040275"}, 
         {"0.6900681692876729", "-0.031219020261622682"}, 
         {"0.7073138671739503", "-0.033690193909531485"}, 
         {"0.7303081310223203", "-0.03336889094642845"}, 
         {"0.7432424044370283", "-0.032113209380358915"}, 
         {"0.7619252438138289", "-0.03159295149409996"}, 
         {"0.7849195076621986", "-0.03042821280099617"}, 
         {"0.7971352103316451", "-0.02983057002436801"}, 
         {"0.8150994789631842", "-0.02874483754190857"}, 
         {"0.838093742811554", "-0.024193952462184226"}, 
         {"0.851028016226262", "-0.02144719701629197"}, 
         {"0.8689922848578011", "-0.01850371317212532"}, 
         {"0.8898308364703862", "-0.024913854049501444"}, 
         {"0.9027651098850944", "-0.030147134640649775"}, 
         {"0.922885090752418", "-0.038671237648857465"}, 
         {"0.9430050716197416", "-0.05040518062533475"}, 
         {"0.958813628015496", "-0.06017799045704739"}, 
         {"0.9758198023200195", "-0.07034189432587595"}, 
         {"0.983245033354389", "-0.07341413831343596"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("fununit", new String[]{"mV/K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.17[S/m]", "0", "0", "0", "0.17[S/m]", "0", "0", "0", "0.17[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"5e-13*exp(1200*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "5e-13*exp(1200*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "5e-13*exp(1200*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.58[W/(m*K)]", "0", "0", "0", "1.58[W/(m*K)]", "0", "0", "0", "1.58[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "840.1[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "4.87[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "50060[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "Sturm, Rheinfeld, Zilberman, Spingler, Kosch, Frie and Jossen, \"Modeling and simulation of inhomogeneities in a 18650 nickel-rich, silicon-graphite lithium-ion cell during fast charging\", Journal of Power Sources 412 (2019) 204\u2013223");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
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
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.25[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.5[V]");
    model.component("comp1").material("mat3").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.8878314072059823", "-0.01908801696712681"}, 
         {"0.8558803535010198", "-0.09544008483563182"}, 
         {"0.8273283480625425", "-0.19724284199363806"}, 
         {"0.7919782460910945", "-0.2905620360551433"}, 
         {"0.7627464309993202", "-0.3584305408271482"}, 
         {"0.7321549966009517", "-0.42629904559915177"}, 
         {"0.7042828008157715", "-0.5026511134676568"}, 
         {"0.6709721278042148", "-0.5705196182396608"}, 
         {"0.6349422161794698", "-0.6383881230116648"}, 
         {"0.6084296397008837", "-0.7147401908801698"}, 
         {"0.5791978246091094", "-0.8335100742311776"}, 
         {"0.5506458191706322", "-0.9862142099681872"}, 
         {"0.5193745751189666", "-1.1049840933191946"}, 
         {"0.4894629503738953", "-1.2067868504772008"}, 
         {"0.45955132562882384", "-1.3425238600212097"}, 
         {"0.42556084296397", "-1.51219512195122"}, 
         {"0.39700883752549276", "-1.6818663838812302"}, 
         {"0.36573759347382717", "-1.8515376458112414"}, 
         {"0.3358259687287558", "-2.106044538706257"}, 
         {"0.3099932019034669", "-2.496288441145281"}, 
         {"0.27464309993201896", "-3.1240721102863205"}, 
         {"0.2447314751869476", "-3.921527041357371"}, 
         {"0.213460231135282", "-4.820784729586427"}, 
         {"0.18218898708361642", "-5.669141039236479"}, 
         {"0.15091774303195082", "-6.534464475079533"}});
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
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
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
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u663e\u5f0f 2");
    model.component("comp1").selection("sel2").set(1);

    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").label("\u663e\u5f0f 3");
    model.component("comp1").selection("sel3").set(3);

    model.component("comp1").material("mat3").selection().named("sel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("sel3");
    model.component("comp1").variable("var1").set("Arreh", "exp((Ea_pos/R_const)*(1/(T_ref_pos)-1/(T)))");
    model.component("comp1").variable("var1").descr("Arreh", "Arrhenius kinetics correction");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("sel2");
    model.component("comp1").variable("var2").set("Arreh", "exp((Ea_neg/R_const)*(1/(T_ref_neg)-1/(T)))");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl("intop1").set("opname", "negative");

    model.component("comp1").physics("liion").prop("Ac").set("Ac", "A_cell");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("CellVoltageInputType", "userdef");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_0SOC", "Vlow");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_100SOC", "Vhigh");
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", "SOCcell_init");
    model.component("comp1").physics("liion").feature("socicd1").set("f_loss_neg", "Li_loss");
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().named("sel3");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s2", 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s2", 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "LiMetal", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", "rhoLi", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", "MLi", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "LiDead", 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", "rhoLi", 1, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", "MLi", 1, 0);
    model.component("comp1").physics("liion").feature("pce1").set("AddVolumeChangeToElectrodeVolumeFraction", false);
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "Arreh*i0_ref_neg");
    model.component("comp1").physics("liion").feature("pce1").feature().duplicate("per2", "per1");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .set("ElectrodeKinetics", "ConcentrationDependentKinetics");
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .set("i0", "i0_ref_LiMetal*(cl/1[M])^0.5");
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .set("CR", "min(liion.c_pce1_LiMetal/(cLicov*liion.Av_pce1_per2),1)");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("VLiTheta", 0);
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .setIndex("Vib", "if(liion.iloc_per2<0,1,1+Ydead)", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .setIndex("Vib", "if(liion.iloc_per2>0,-Ydead,0)", 1, 0);
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().named("sel3");
    model.component("comp1").physics("liion").feature("pce2").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "Arreh*i0_ref_pos");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(0);
    model.component("comp1").selection("sel4").label("\u663e\u5f0f 4");
    model.component("comp1").selection("sel4").set(1);

    model.component("comp1").physics("liion").feature("egnd1").selection().named("sel4");
    model.component("comp1").physics("liion").create("cdc1", "ChargeDischargeCycling", 0);
    model.component("comp1").physics("liion").feature("cdc1").selection().set(4);

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(0);
    model.component("comp1").selection("sel5").label("\u663e\u5f0f 5");
    model.component("comp1").selection("sel5").set(4);

    model.component("comp1").physics("liion").feature("cdc1").selection().named("sel5");
    model.component("comp1").physics("liion").feature("cdc1").set("DischargingCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("cdc1").set("Crate_dch", "-C_rate");
    model.component("comp1").physics("liion").feature("cdc1").set("Vmin", "Vlow");
    model.component("comp1").physics("liion").feature("cdc1").set("ChargingCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("cdc1").set("Crate_ch", "C_rate");
    model.component("comp1").physics("liion").feature("cdc1").set("Vmax", "Vhigh");
    model.component("comp1").physics("liion").feature("cdc1").set("item.CVCH", true);
    model.component("comp1").physics("liion").feature("cdc1").set("iupper", "I_taper");
    model.component("comp1").physics("liion").feature("cdc1").set("StartWith", "Charge_first");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "epss_pos", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "epss_pos", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "C_rate", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 3 5", 0);
    model.study("std1").feature("time").set("tlist", "range(0,100/C_rate,3600*6/C_rate)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1")
         .setIndex("stopcondarr", "comp1.liion.cdc1.phis0<(Vlow*(t>3600/C_rate))", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"liion.cdc1.phis0"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"liion.cdc1.Icell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u6d41"});
    model.result("pg2").label("\u7535\u6c60\u7535\u6d41 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
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
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 2"});
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg3")
         .label("\u7535\u6c60\u548c\u5e73\u5747\u7535\u6781\u7535\u6c60\u7684\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg3").set("ylabel", "SOC (1)");
    model.result("pg3").set("titletype", "none");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg6").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("xdataexpr", "liion.SOC_cell");
    model.result("pg1").feature("glob1").set("xdatadescr", "\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "C-rate=1, Charge", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "C-rate=3, Charge", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "C-rate=5, Charge", 2);
    model.result("pg1").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").feature("filt1").set("xdec", false);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("linestyle", "dashed");
    model.result("pg1").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg1").feature("glob2").setIndex("legends", "C-rate=1, Discharge", 0);
    model.result("pg1").feature("glob2").setIndex("legends", "C-rate=3, Discharge", 1);
    model.result("pg1").feature("glob2").setIndex("legends", "C-rate=5, Discharge", 2);
    model.result("pg1").run();
    model.result("pg1").feature("glob2").feature("filt1").set("xinc", false);
    model.result("pg1").feature("glob2").feature("filt1").set("xdec", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "negative(liion.iv_per2)*A_cell", 0);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "C-rate=1, Current", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "C-rate=3, Current", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "C-rate=5, Current", 2);
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("expr", new String[]{"liion.cdc1.phis0"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"\u7535\u6c60\u7535\u4f4d"});
    model.result("pg2").feature("glob2").set("unit", new String[]{"V"});
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").setIndex("legends", "C-rate=1, Cell voltage", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "C-rate=3, Cell voltage", 1);
    model.result("pg2").feature("glob2").setIndex("legends", "C-rate=5, Cell voltage", 2);
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("yseclabelactive", true);
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").set("legendposoutside", "bottom");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(2);
    model.result("pg7").feature("ptgr1").set("expr", "phis-phil");
    model.result("pg7").feature("ptgr1").set("xdataexpr", "liion.SOC_cell");
    model.result("pg7").feature("ptgr1").set("xdatadescr", "\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "C-rate=1, Electrode-Separator", 0);
    model.result("pg7").feature("ptgr1").setIndex("legends", "C-rate=3, Electrode-Separator", 1);
    model.result("pg7").feature("ptgr1").setIndex("legends", "C-rate=5, Electrode-Separator", 2);
    model.result("pg7").feature("ptgr1").create("filt1", "PointGraphFilter");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").feature("filt1").set("expr", "liion.cdc1.Icell>0");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").selection().set(1);
    model.result("pg7").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg7").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg7").feature("ptgr2").setIndex("legends", "C-rate=1, Current Collector-Electrode", 0);
    model.result("pg7").feature("ptgr2").setIndex("legends", "C-rate=3, Current Collector-Electrode", 1);
    model.result("pg7").feature("ptgr2").setIndex("legends", "C-rate=5, Current Collector-Electrode", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("expr", "liion.sbtot_pce1");
    model.result("pg8").feature("ptgr1").set("descr", "\u603b\u819c\u539a\u53d8\u5316");
    model.result("pg8").feature("ptgr1").set("unit", "nm");
    model.result("pg8").feature("ptgr1").set("xdata", "solution");
    model.result("pg8").feature("ptgr1").setIndex("legends", "C-rate=1, Thickness", 0);
    model.result("pg8").feature("ptgr1").setIndex("legends", "C-rate=3, Thickness", 1);
    model.result("pg8").feature("ptgr1").setIndex("legends", "C-rate=5, Thickness", 2);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").feature().remove("filt1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature().remove("ptgr2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "liion.cdc1.phis0", 0);
    model.result("pg8").feature("glob1").set("linestyle", "dashed");
    model.result("pg8").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg8").feature("glob1").set("legendmethod", "manual");
    model.result("pg8").feature("glob1").setIndex("legends", "C-rate=1, Cell voltage", 0);
    model.result("pg8").feature("glob1").setIndex("legends", "C-rate=3, Cell voltage", 1);
    model.result("pg8").feature("glob1").setIndex("legends", "C-rate=5, Cell voltage", 2);
    model.result("pg8").run();
    model.result("pg8").set("twoyaxes", true);
    model.result("pg8").set("yseclabelactive", true);
    model.result("pg8").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg8").set("legendlayout", "outside");
    model.result("pg8").set("legendposoutside", "bottom");
    model.result("pg8").run();
    model.result("pg2").run();
    model.result().duplicate("pg9", "pg2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "negative(liion.c_pce1_LiDead*F_const)*A_cell", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "mAh", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "C-rate=1, Loss", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "C-rate=3, Loss", 1);
    model.result("pg9").feature("glob1").setIndex("legends", "C-rate=5, Loss", 2);
    model.result("pg9").run();

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
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "epss_pos", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "epss_pos", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "T", 0);
    model.study("std2").feature("param").setIndex("plistarr", "298 283 268", 0);
    model.study("std2").feature("time").set("tlist", "range(0,100/C_rate,3600*6/C_rate)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol7").feature("t1").set("tout", "tsteps");
    model.sol("sol7").feature("t1").create("st1", "StopCondition");
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol7").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol7").feature("t1").feature("st1")
         .setIndex("stopcondarr", "comp1.liion.cdc1.phis0<(Vlow*(t>3600/C_rate))", 0);
    model.sol("sol7").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std2");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol9");
    model.batch("p2").run("compute");

    model.result("pg7").run();
    model.result().duplicate("pg10", "pg7");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset6");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").setIndex("legends", "T=298 K, Electrode-Separator", 0);
    model.result("pg10").feature("ptgr1").setIndex("legends", "T=283 K, Electrode-Separator", 1);
    model.result("pg10").feature("ptgr1").setIndex("legends", "T=268 K, Electrode-Separator", 2);
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").setIndex("legends", "T=298 K, Current Collector-Electrode", 0);
    model.result("pg10").feature("ptgr2").setIndex("legends", "T=283 K, Current Collector-Electrode", 1);
    model.result("pg10").feature("ptgr2").setIndex("legends", "T=268 K, Current Collector-Electrode", 2);
    model.result("pg10").run();
    model.result("pg1").run();
    model.result().duplicate("pg11", "pg1");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset6");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").setIndex("legends", "T=298 K, Charge", 0);
    model.result("pg11").feature("glob1").setIndex("legends", "T=283 K, Charge", 1);
    model.result("pg11").feature("glob1").setIndex("legends", "T=268 K, Charge", 2);
    model.result("pg11").run();
    model.result("pg11").feature("glob2").setIndex("legends", "T=298 K, Discharge", 0);
    model.result("pg11").feature("glob2").setIndex("legends", "T=283 K, Discharge", 1);
    model.result("pg11").feature("glob2").setIndex("legends", "T=268 K, Discharge", 2);
    model.result("pg11").run();
    model.result("pg8").run();
    model.result().duplicate("pg12", "pg8");
    model.result("pg12").run();
    model.result("pg12").set("data", "dset6");
    model.result("pg12").run();
    model.result("pg12").feature("ptgr1").setIndex("legends", "T=298 K, Thickness", 0);
    model.result("pg12").feature("ptgr1").setIndex("legends", "T=283 K, Thickness", 1);
    model.result("pg12").feature("ptgr1").setIndex("legends", "T=268 K, Thickness", 2);
    model.result("pg12").run();
    model.result("pg12").feature("glob1").setIndex("legends", "T=298 K, Cell voltage", 0);
    model.result("pg12").feature("glob1").setIndex("legends", "T=283 K, Cell voltage", 1);
    model.result("pg12").feature("glob1").setIndex("legends", "T=268 K, Cell voltage", 2);
    model.result("pg12").run();
    model.result("pg9").run();
    model.result().duplicate("pg13", "pg9");
    model.result("pg13").run();
    model.result("pg13").set("data", "dset6");
    model.result("pg13").run();
    model.result("pg13").feature("glob1").setIndex("legends", "T=298 K, Loss", 0);
    model.result("pg13").feature("glob1").setIndex("legends", "T=283 K, Loss", 1);
    model.result("pg13").feature("glob1").setIndex("legends", "T=268 K, Loss", 2);
    model.result("pg13").run();
    model.result("pg13").feature("glob2").setIndex("legends", "T=298 K, Cell voltage", 0);
    model.result("pg13").feature("glob2").setIndex("legends", "T=283 K, Cell voltage", 1);
    model.result("pg13").feature("glob2").setIndex("legends", "T=268 K, Cell voltage", 2);
    model.result("pg13").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result("pg7").run();

    model.title("\u6790\u9502\u548c\u8131\u9502");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u4f7f\u7528\u9502\u79bb\u5b50\u7535\u6c60\u63a5\u53e3\u6765\u7814\u7a76\u6790\u9502\u548c\u8131\u9502\uff0c\u4ee5\u6a21\u62df\u9884\u6d4b\u7535\u6c60\u5185\u90e8\u6790\u9502\u548c\u6b7b\u9502\u5f62\u6210\u7684\u65f6\u95f4\u548c\u4f4d\u7f6e\uff1b\u5e76\u5c55\u793a\u4e86\u6e29\u5ea6\u548c\u5145\u7535\u901f\u7387\u5bf9\u6574\u4e2a\u5145\u653e\u7535\u5faa\u73af\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("li_plating.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
