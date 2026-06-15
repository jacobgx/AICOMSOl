/*
 * li_battery_impedance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_impedance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.study().create("std1");
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("ftplistmethod", "manual");
    model.study("std1").feature("frlin").set("solnum", "auto");
    model.study("std1").feature("frlin").set("notsolnum", "auto");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_neg", "115e-6[m]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_pos", "35e-6[m]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_sep", "50e-6[m]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("sigmas_neg", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "91[S/m]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("epscs_pos", "0.11", "\u6b63\u6781\u7684\u7535\u5b50\u5bfc\u4f53\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_pos", "0.29", "\u6b63\u6781\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_pos", "0.31", "\u6b63\u6781\u7684\u6d3b\u6027\u6750\u6599\u4f53\u79ef\u5206\u6570");
    model.param().set("brugl_pos", "2.928", "\u6b63\u6781\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("epscs_neg", "0.0423", "\u8d1f\u6781\u7684\u7535\u5b50\u5bfc\u4f53\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epsl_neg", "1-epss_neg-epscs_neg-0.048", "\u8d1f\u6781\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_neg", "0.25", "\u8d1f\u6781\u7684\u6d3b\u6027\u6750\u6599\u4f53\u79ef\u5206\u6570");
    model.param().set("brugl_neg", "3.50", "\u8d1f\u6781\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("epsl_sep", "0.37", "\u9694\u819c\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("brugl_sep", "3.15", "\u9694\u819c\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("cl_init", "1200[mol/m^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param()
         .set("soc0_neg", "0.326", "\u521d\u59cb\u8377\u7535\u72b6\u6001\uff0c\u8d1f\u6781\u76f8\u5bf9\u4e8e Li+/Li(s) \u7684\u6807\u51c6\u7535\u4f4d\u4e3a 1.55V");
    model.param()
         .set("soc0_pos", "0.588", "\u521d\u59cb\u8377\u7535\u72b6\u6001\uff0c\u6b63\u6781\u76f8\u5bf9\u4e8e Li+/Li(s) \u7684\u6807\u51c6\u7535\u4f4d\u4e3a 3.85V");
    model.param().set("rp_neg", "5e-7[m]", "\u8d1f\u6781\u7684\u6d3b\u6027\u6750\u6599\u9897\u7c92\u534a\u5f84");
    model.param()
         .set("rp_pos", "2.4911e-7[m]", "\u6b63\u6781\u7684\u6d3b\u6027\u6750\u6599\u9897\u7c92\u534a\u5f84");
    model.param().set("as_pos", "1.9248e6[1/m]", "\u6b63\u6781\u6750\u6599\u7684\u6bd4\u8868\u9762\u79ef");
    model.param().set("i0_pos", "1[A/m^2]", "\u6b63\u6781\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("cdlvol_cs_pos", "2.577e5[F/m^3]", "\u6b63\u6781\u7535\u5b50\u5bfc\u4f53\u7684\u4f53\u79ef\u6bd4\u7535\u5bb9");
    model.param().set("cdl_pos", "0.2393[F/m^2]", "\u6b63\u6781\u6750\u6599\u7684\u53cc\u7535\u5c42\u7535\u5bb9");
    model.param().set("Rfilm_pos", "2.848e-3[m^2/S]", "\u6b63\u6781\u819c\u963b");
    model.param()
         .set("i0ref_neg", "1.08e3[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("cdl_neg", "cdl_pos", "\u6b63\u6781\u6750\u6599\u7684\u53cc\u7535\u5c42\u7535\u5bb9");
    model.param().set("Rfilm_neg", "1e-5", "\u8d1f\u6781\u819c\u963b");
    model.param()
         .set("R_curr", "1.1e-4[m^2/S]", "\u6bcf\u4e2a\u96c6\u6d41\u4f53\u7684\u96c6\u7535\u6781\u7535\u963b");
    model.param().set("A_cell", "32e-4[m^2]", "\u96c6\u6d41\u4f53\u9762\u79ef");
    model.param().set("E_pert", "0.01[V]", "\u7535\u52bf\u6270\u52a8\u5927\u5c0f");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("Z_ground", "liion.Zvsgrnd_pot1", "\u963b\u6297 vs \u63a5\u5730");
    model.component("comp1").variable("var1")
         .set("Z_ref_NCA", "(lindev(liion.phisbnd)-lindev(liion.phisref_refel1))/lindev(liion.nIsg_pot1)", "NCA \u6b63\u6781\u963b\u6297 vs \u53c2\u8003");
    model.component("comp1").variable("var1")
         .set("Z_ref_LTO", "Z_ground-Z_ref_NCA", "LTO \u8d1f\u6781\u963b\u6297 vs \u53c2\u8003");
    model.component("comp1").variable("var1")
         .set("E_cell_init", "mat3.def.Eeq(soc0_pos)-mat2.def.Eeq(soc0_neg)", "\u521d\u59cb\u7535\u6c60\u7535\u538b");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").label("\u95f4\u9694 - \u8d1f\u6781");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_neg", 1);
    model.component("comp1").geom("geom1").feature("i1").set("selresult", true);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("i2", "Interval");
    model.component("comp1").geom("geom1").feature("i2").label("\u95f4\u9694 - \u9694\u819c");
    model.component("comp1").geom("geom1").feature("i2").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i2").set("left", "L_neg");
    model.component("comp1").geom("geom1").feature("i2").setIndex("len", "L_sep", 0);
    model.component("comp1").geom("geom1").feature("i2").set("selresult", true);
    model.component("comp1").geom("geom1").run("i2");
    model.component("comp1").geom("geom1").create("i3", "Interval");
    model.component("comp1").geom("geom1").feature("i3").label("\u95f4\u9694 - \u6b63\u6781");
    model.component("comp1").geom("geom1").feature("i3").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i3").set("left", "L_neg+L_sep");
    model.component("comp1").geom("geom1").feature("i3").setIndex("len", "L_pos", 0);
    model.component("comp1").geom("geom1").feature("i3").set("selresult", true);
    model.component("comp1").geom("geom1").run("i3");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").label("\u70b9 - \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L_neg+L_sep/2", 0);
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat2").label("LTO, Li4Ti5O12 (Negative, Li-ion Battery)");
    model.component("comp1").material("mat2").comments("\n");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "2.228201418"}, 
         {"0.01", "1.834270406"}, 
         {"0.02", "1.769694648"}, 
         {"0.03", "1.718942051"}, 
         {"0.04", "1.665890342"}, 
         {"0.05", "1.62099317"}, 
         {"0.06", "1.590316013"}, 
         {"0.07", "1.572303079"}, 
         {"0.08", "1.562627805"}, 
         {"0.09", "1.557650415"}, 
         {"0.1", "1.555118153"}, 
         {"0.11", "1.553809207"}, 
         {"0.12", "1.553100548"}, 
         {"0.13", "1.55268369"}, 
         {"0.14", "1.552408035"}, 
         {"0.15", "1.552200555"}, 
         {"0.16", "1.552025958"}, 
         {"0.17", "1.551867213"}, 
         {"0.18", "1.551716108"}, 
         {"0.19", "1.551568684"}, 
         {"0.2", "1.551423034"}, 
         {"0.21", "1.551278239"}, 
         {"0.22", "1.551133856"}, 
         {"0.23", "1.550989672"}, 
         {"0.24", "1.550845583"}, 
         {"0.25", "1.55070154"}, 
         {"0.26", "1.550557519"}, 
         {"0.27", "1.550413509"}, 
         {"0.28", "1.550269504"}, 
         {"0.29", "1.550125502"}, 
         {"0.3", "1.549981501"}, 
         {"0.31", "1.5498375"}, 
         {"0.32", "1.5496935"}, 
         {"0.33", "1.5495495"}, 
         {"0.34", "1.5494055"}, 
         {"0.35", "1.5492615"}, 
         {"0.36", "1.5491175"}, 
         {"0.37", "1.5489735"}, 
         {"0.38", "1.5488295"}, 
         {"0.39", "1.5486855"}, 
         {"0.4", "1.5485415"}, 
         {"0.41", "1.5483975"}, 
         {"0.42", "1.5482535"}, 
         {"0.43", "1.5481095"}, 
         {"0.44", "1.5479655"}, 
         {"0.45", "1.5478215"}, 
         {"0.46", "1.5476775"}, 
         {"0.47", "1.5475335"}, 
         {"0.48", "1.5473895"}, 
         {"0.49", "1.5472455"}, 
         {"0.5", "1.5471015"}, 
         {"0.51", "1.5469575"}, 
         {"0.52", "1.5468135"}, 
         {"0.53", "1.5466695"}, 
         {"0.54", "1.5465255"}, 
         {"0.55", "1.5463815"}, 
         {"0.56", "1.5462375"}, 
         {"0.57", "1.5460935"}, 
         {"0.58", "1.5459495"}, 
         {"0.59", "1.5458055"}, 
         {"0.6", "1.5456615"}, 
         {"0.61", "1.5455175"}, 
         {"0.62", "1.5453735"}, 
         {"0.63", "1.5452295"}, 
         {"0.64", "1.5450855"}, 
         {"0.65", "1.5449415"}, 
         {"0.66", "1.5447975"}, 
         {"0.67", "1.5446535"}, 
         {"0.68", "1.5445095"}, 
         {"0.69", "1.5443655"}, 
         {"0.7", "1.5442215"}, 
         {"0.71", "1.5440775"}, 
         {"0.72", "1.5439335"}, 
         {"0.73", "1.5437895"}, 
         {"0.74", "1.543645501"}, 
         {"0.75", "1.543501502"}, 
         {"0.76", "1.543357511"}, 
         {"0.77", "1.543213545"}, 
         {"0.78", "1.543069643"}, 
         {"0.79", "1.54290683"}, 
         {"0.8", "1.540448721"}, 
         {"0.81", "1.539545288"}, 
         {"0.82", "1.539411818"}, 
         {"0.83", "1.539247179"}, 
         {"0.84", "1.538618467"}, 
         {"0.85", "1.534813267"}, 
         {"0.86", "1.520453134"}, 
         {"0.87", "1.504450253"}, 
         {"0.88", "1.493585271"}, 
         {"0.89", "1.472914822"}, 
         {"0.9", "1.447481846"}, 
         {"0.91", "1.421065131"}, 
         {"0.92", "1.394494058"}, 
         {"0.93", "1.367903211"}, 
         {"0.94", "1.341310809"}, 
         {"0.95", "1.314718596"}, 
         {"0.96", "1.288126526"}, 
         {"0.97", "1.261534507"}, 
         {"0.98", "1.234942502"}, 
         {"0.99", "1.2083505"}, 
         {"1", "1.1817585"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"6.8e-15[m^2/s]", "0", "0", "0", "6.8e-15[m^2/s]", "0", "0", "0", "6.8e-15[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "P. Albertus, J. Couts, and V. Srinivasan, \"II. A combined model for determining capacity usage and plug-in hybrid electric vehicles\", J. Power Sources, vol. 183, p. 771, 2008");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-7[S/cm]", "0", "0", "0", "1e-7[S/cm]", "0", "0", "0", "1e-7[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrical conductivity and rate-capability of Li4Ti5O12 as a function of heat-treatment atmosphere, J. Wolfenstine, U. Lee, J.L. Allen,\nJournal of Power Sources 154 (2006) 287\u2013289");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3400[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "22852[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "P. Albertus, J. Couts, and V. Srinivasan, \"II. A combined model for determining capacity usage and plug-in hybrid electric vehicles\", J. Power Sources, vol. 183, p. 771, 2008");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-1[J/mol/K]/F_const");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "P. Albertus, J. Couts, and V. Srinivasan, \"II. A combined model for determining capacity usage and plug-in hybrid electric vehicles\", J. Power Sources, vol. 183, p. 771, 2008");
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
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "1.8[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "1.2[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat2").propertyGroup("ic").set("dvol", "0");
    model.component("comp1").material("mat2").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-1[J/mol/K]/F_const");
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
    model.component("comp1").material("mat3").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp1").material("mat3").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("NCA, LiNi0.8Co0.15Al0.05O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.249", "4.2921"}, 
         {"0.2677", "4.2502"}, 
         {"0.2852", "4.2173"}, 
         {"0.3054", "4.1901"}, 
         {"0.3261", "4.1662"}, 
         {"0.3432", "4.1424"}, 
         {"0.3598", "4.1175"}, 
         {"0.3769", "4.0914"}, 
         {"0.4041", "4.054"}, 
         {"0.4247", "4.0268"}, 
         {"0.4489", "4.0007"}, 
         {"0.4729", "3.9723"}, 
         {"0.4947", "3.9519"}, 
         {"0.5178", "3.9293"}, 
         {"0.5354", "3.9089"}, 
         {"0.5554", "3.885"}, 
         {"0.5782", "3.8612"}, 
         {"0.5998", "3.8363"}, 
         {"0.6255", "3.8125"}, 
         {"0.6617", "3.7819"}, 
         {"0.6985", "3.7547"}, 
         {"0.7332", "3.7343"}, 
         {"0.7689", "3.7117"}, 
         {"0.8066", "3.689"}, 
         {"0.8247", "3.6766"}, 
         {"0.8424", "3.6641"}, 
         {"0.8595", "3.6528"}, 
         {"0.8776", "3.6369"}, 
         {"0.8952", "3.6221"}, 
         {"0.9133", "3.6063"}, 
         {"0.9308", "3.587"}, 
         {"0.9465", "3.5678"}, 
         {"0.9621", "3.5451"}, 
         {"0.9762", "3.5178"}, 
         {"0.9878", "3.4827"}, 
         {"0.9944", "3.4452"}, 
         {"0.9984", "3.3192"}, 
         {"1.0000", "3.1989"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.3", "0"}, {"0.45", "0"}, {"0.75", "-0.07e-3"}, {"0.85", "-0.08e-3"}, {"0.95", "-0.04e-3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1.5e-15[m^2/s]", "0", "0", "0", "1.5e-15[m^2/s]", "0", "0", "0", "1.5e-15[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "S. Brown, N. Mellgren, M. Vynnycky, and G. Lindbergh, \"Impedance as a Tool for Investigating Aging in Lithium-Ion Porous Electrodes. II. Positive Electrode Examination\", J. Electrochemical Society, vol. 155, p. A320, 2008");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-2[S/m]", "0", "0", "0", "1e-2[S/m]", "0", "0", "0", "1e-2[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "S. Brown, N. Mellgren, M. Vynnycky, and G. Lindbergh, \"Impedance as a Tool for Investigating Aging in Lithium-Ion Porous Electrodes. II. Positive Electrode Examination\", J. Electrochemical Society, vol. 155, p. A320, 2008");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "4740[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "48000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "Asymmetry of Discharge/Charge Curves of Lithium-Ion Battery\nIntercalation Electrodes, Florian Hall, Sabine Wussler, Hilmi Buqa, and Wolfgang G. Bessler, J. Phys. Chem. C 2016, 120, 23407-23414");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "Determination of the Reversible and Irreversible Heats of a LiNi0.8Co0.15Al0.05O2 Natural Graphite Cell Using Electrochemical-Calorimetric Technique, Hui Yang and Jai Prakash, J. Electrochem. Soc. 2004 volume 151, issue 8, A1222-A1229");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
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
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.29[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.5[V]");
    model.component("comp1").material("mat3").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0.8878314072059823", "-0.03605514316012792"}, 
         {"0.8579197824609109", "-0.10392364793213194"}, 
         {"0.8280081577158396", "-0.20572640509013818"}, 
         {"0.7980965329707681", "-0.324496288441146"}, 
         {"0.769544527532291", "-0.4093319194061511"}, 
         {"0.7396329027872195", "-0.5111346765641573"}, 
         {"0.7083616587355539", "-0.5450689289501596"}, 
         {"0.6798096532970768", "-0.5620360551431607"}, 
         {"0.6485384092454112", "-0.5959703075291629"}, 
         {"0.619986403806934", "-0.6299045599151651"}, 
         {"0.5900747790618626", "-0.6638388123011669"}, 
         {"0.5601631543167913", "-0.7317073170731714"}, 
         {"0.5288919102651257", "-0.7995758218451754"}, 
         {"0.49898028552005425", "-0.884411452810181"}, 
         {"0.4704282800815771", "-0.9692470837751861"}, 
         {"0.44187627464309986", "-1.0540827147401917"}, 
         {"0.4119646498980285", "-1.2067868504772008"}, 
         {"0.38341264445955126", "-1.3594909862142108"}, 
         {"0.34942216179469743", "-1.6309650053022278"}, 
         {"0.3235893949694084", "-1.9872746553552503"}, 
         {"0.29231815091774294", "-2.513255567338283"}, 
         {"0.2624065261726717", "-3.3107104984093327"}, 
         {"0.2324949014276002", "-4.057264050901379"}, 
         {"0.20394289598912296", "-4.820784729586427"}, 
         {"0.16859279401767502", "-5.720042417815483"}});
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
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importedname", "NCA.txt");
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
         .setPropertyInfo("electricconductivity", "Characterization of Electronic and Ionic Transport in Li1-x Ni0. 8Co0.15Al0.05O2 (NCA),\nRuhul Amin et al 2015 J. Electrochem. Soc. 162 A1163");
    model.component("comp1").material("mat3").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp1").material("mat3").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("concentration");
    model.component("comp1").material("mat2").selection().named("geom1_i1_dom");
    model.component("comp1").material("mat3").selection().named("geom1_i3_dom");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(5);

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fDl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().named("geom1_i1_dom");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("FilmResistanceType", "SurfaceResistance");
    model.component("comp1").physics("liion").feature("pce1").set("Rfilm", "Rfilm_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("fl", "epsl_neg^brugl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("fDl", "epsl_neg^brugl_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1")
         .set("csinit", "mat2.def.csmax*soc0_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").feature("pce1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("liion").feature("pce1").feature("pdl1").set("Cdl", "cdl_neg");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().named("geom1_i3_dom");
    model.component("comp1").physics("liion").feature("pce2").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("FilmResistanceType", "SurfaceResistance");
    model.component("comp1").physics("liion").feature("pce2").set("Rfilm", "Rfilm_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fl", "epsl_pos^brugl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fDl", "epsl_pos^brugl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1")
         .set("csinit", "mat3.def.csmax*soc0_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("per1")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0", "i0_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("Av", "as_pos");
    model.component("comp1").physics("liion").feature("pce2")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("liion").feature("pce2").feature("pdl1").set("Cdl", "cdl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pdl1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("pdl1").set("av_dl", "as_pos");
    model.component("comp1").physics("liion").feature("init1").set("phil", "-mat2.def.Eeq(soc0_neg)");
    model.component("comp1").physics("liion").feature("init1").set("cl", "cl_init");
    model.component("comp1").physics("liion").create("init2", "init", 1);
    model.component("comp1").physics("liion").feature("init2").selection().named("geom1_i3_dom");
    model.component("comp1").physics("liion").feature("init2").set("phil", "-mat2.def.Eeq(soc0_neg)");
    model.component("comp1").physics("liion").feature("init2").set("cl", "cl_init");
    model.component("comp1").physics("liion").feature("init2")
         .set("phis", "mat3.def.Eeq(soc0_pos)-mat2.def.Eeq(soc0_neg)");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("liion").feature("pot1").selection().set(5);
    model.component("comp1").physics("liion").feature("pot1").set("phisbnd", "E_cell_init");
    model.component("comp1").physics("liion").feature("pot1").set("IncludeContactResistance", true);
    model.component("comp1").physics("liion").feature("pot1").set("Rc", "R_curr");
    model.component("comp1").physics("liion").feature("pot1").create("hp1", "HarmonicPerturbation", 0);
    model.component("comp1").physics("liion").feature("pot1").feature("hp1").set("deltaphis", "E_pert");
    model.component("comp1").physics("liion").create("addm1", "IntercalatingMaterial", 1);
    model.component("comp1").physics("liion").feature("addm1").selection().named("geom1_i3_dom");
    model.component("comp1").physics("liion").feature("addm1")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").active(false);
    model.component("comp1").physics("liion").feature("addm1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("liion").feature("addm1").feature("pdl1").set("Cdl", "1[F/m^2]");
    model.component("comp1").physics("liion").feature("addm1").feature("pdl1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("addm1").feature("pdl1")
         .set("av_dl", "cdlvol_cs_pos/1[F/m^2]");
    model.component("comp1").physics("liion").create("refel1", "ReferenceElectrode", 0);
    model.component("comp1").physics("liion").feature("refel1").selection().named("geom1_pt1_bnd");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1e-5");
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "1e-6");
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "5E-7");

    model.study("std1").feature("frlin").set("plist", "10^{range(-2,0.2,3)}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("nyq1", "Nyquist");
    model.result("pg1").feature("nyq1").set("unit", new String[]{""});
    model.result("pg1").feature("nyq1").set("expr", new String[]{"conj(liion.Zvsgrnd_pot1) "});
    model.result("pg1").feature("nyq1").set("descr", new String[]{""});
    model.result("pg1").label("\u5bf9\u5730\u963b\u6297\uff0c\u5948\u594e\u65af\u7279 (liion)");
    model.result("pg1").feature("nyq1").setIndex("descr", "\u5bf9\u5730\u963b\u6297", 0);
    model.result("pg1").feature("nyq1").set("differential", "off");
    model.result("pg1").feature("nyq1").set("autodescr", "off");
    model.result("pg1").set("preserveaspect", "on");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"real(conj(liion.Zvsgrnd_pot1)) "});
    model.result("pg2").feature("glob1").set("descr", new String[]{""});
    model.result("pg2").label("\u5bf9\u5730\u963b\u6297\uff0c\u5b9e\u90e8 (liion)");
    model.result("pg2").feature("glob1").setIndex("descr", "Impedance_vs_ground_real_part", 0);
    model.result("pg2").feature("glob1").set("differential", "off");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("autodescr", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").set("xlog", "on");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"imag(conj(liion.Zvsgrnd_pot1)) "});
    model.result("pg3").feature("glob1").set("descr", new String[]{""});
    model.result("pg3").label("\u5bf9\u5730\u963b\u6297\uff0c\u865a\u90e8 (liion)");
    model.result("pg3").feature("glob1").setIndex("descr", "Impedance_vs_ground_imaginary_part", 0);
    model.result("pg3").feature("glob1").set("differential", "off");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "freq");
    model.result("pg3").feature("glob1").set("autodescr", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").set("xlog", "on");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u672a\u4f18\u5316");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u5b9e\u9a8c\u963b\u6297\u8868");
    model.result().table("tbl1").importData("li_battery_impedance_Zexp.csv");
    model.result("pg1").run();
    model.result("pg1").label("\u5948\u594e\u65af\u7279\u56fe");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("nyq1").label("\u7535\u6c60\u963b\u6297 vs. \u63a5\u5730");
    model.result("pg1").feature("nyq1").set("autoplotlabel", true);
    model.result("pg1").feature("nyq1").set("autosolution", false);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").label("NCA \u7535\u6781 vs. \u53c2\u6bd4\u7535\u6781\u7684\u963b\u6297");
    model.result("pg1").feature("ptgr1").selection().set(5);
    model.result("pg1").feature("ptgr1").set("expr", "-imag(Z_ref_NCA)");
    model.result("pg1").feature("ptgr1").set("titletype", "none");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "real(Z_ref_NCA)");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg1").feature("ptgr1").set("autopoint", false);
    model.result("pg1").feature("ptgr1").set("autosolution", false);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").label("LTO \u7535\u6781 vs. \u53c2\u6bd4\u7535\u6781\u7684\u963b\u6297");
    model.result("pg1").feature("ptgr2").set("expr", "-imag(Z_ref_LTO)");
    model.result("pg1").feature("ptgr2").set("xdataexpr", "real(Z_ref_LTO)");
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1")
         .label("\u5b9e\u9a8c\u5f97\u5230\u7684 NCA \u7535\u6781 vs. \u53c2\u6bd4\u7535\u6781\u7684\u963b\u6297");
    model.result("pg1").feature("tblp1").set("xaxisdata", 2);
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("autoplotlabel", true);
    model.result("pg1").feature("tblp1").set("autoheaders", false);
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param")
         .set("plistarr", new String[]{"1 5 1 1 1 1", "0.2393 0.2393 0.5 0.2393 0.2393 0.2393", "2.848e-3 2.848e-3 2.848e-3 1e-5 2.848e-3 2.848e-3", "2.557e5 2.557e5 2.557e5 2.557e5 1e4 2.557e5", "2.4911e-7 2.4911e-7 2.4911e-7 2.4911e-7 2.4911e-7 4e-7"});
    model.study("std1").feature("param")
         .set("pname", new String[]{"i0_pos", "cdl_pos", "Rfilm_pos", "cdlvol_cs_pos", "rp_pos"});
    model.study("std1").feature("param").set("punit", new String[]{"m", "m", "m", "m", "m"});
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.sol("sol3").label("\u53c2\u6570\u5316\u626b\u63cf");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5948\u594e\u65af\u7279\u56fe\uff0c\u53c2\u6570\u5316\u626b\u63cf");
    model.result("pg2").run();
    model.result("pg2").feature().remove("nyq1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("ptgr2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("data", "dset3");
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1")
         .setIndex("legends", "NCA \u7535\u6781 vs. \u53c2\u6bd4\u7535\u6781\u7684\u963b\u6297\u539f\u59cb\u8bbe\u7f6e", 0);
    model.result("pg2").feature("ptgr1").setIndex("legends", "i0_pos=5 Am<sup>-2</sup>", 1);
    model.result("pg2").feature("ptgr1").setIndex("legends", "cdl_pos=0.50 Fm<sup>-2</sup>", 2);
    model.result("pg2").feature("ptgr1")
         .setIndex("legends", "Rfilm_pos=1<sup>.</sup>10<sup>-5</sup> \u03a9m<sup>2</sup>", 3);
    model.result("pg2").feature("ptgr1")
         .setIndex("legends", "cdlvol_cs_pos=1<sup>.</sup>10<sup>4</sup> Fm<sup>-3</sup>", 4);
    model.result("pg2").feature("ptgr1").setIndex("legends", "rp_pos=4<sup>.</sup>10<sup>-7</sup> m", 5);
    model.result("pg2").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("frlin", "Frequencylinearized");
    model.study("std2").feature("frlin").set("plotgroup", "Default");
    model.study("std2").feature("frlin").set("ftplistmethod", "manual");
    model.study("std2").feature("frlin").set("solnum", "auto");
    model.study("std2").feature("frlin").set("notsolnum", "auto");
    model.study("std2").feature("frlin").set("outputmap", new String[]{});
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").setSolveFor("/physics/liion", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").set("source", "resultTable");
    model.study("std2").feature("lsqo").setEntry("columnType", "col1", "freq");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col2", "comp1.intop1(real(Z_ref_NCA))");
    model.study("std2").feature("lsqo").setEntry("variableName", "col2", "Real_impedance");
    model.study("std2").feature("lsqo").setEntry("scaleMethod", "col2", "manual");
    model.study("std2").feature("lsqo").setEntry("scaleValue", "col2", "1e-3");
    model.study("std2").feature("lsqo").setEntry("modelExpression", "col3", "-comp1.intop1(imag(Z_ref_NCA))");
    model.study("std2").feature("lsqo").setEntry("variableName", "col3", "Imaginary_impedance");
    model.study("std2").feature("lsqo").setEntry("scaleMethod", "col3", "manual");
    model.study("std2").feature("lsqo").setEntry("scaleValue", "col3", "1e-3");
    model.study("std2").feature("lsqo").setIndex("pname", "L_neg", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "115e-6[m]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "L_neg", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.study("std2").feature("lsqo").setIndex("initval", "115e-6[m]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "L_pos", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "35e-6[m]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "L_pos", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "35e-6[m]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "L_sep", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "50e-6[m]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "L_sep", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "50e-6[m]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "sigmas_neg", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "100[S/m]", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "sigmas_neg", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "100[S/m]", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "i0_pos", 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 1, 0);
    model.study("std2").feature("lsqo").setIndex("ubound", 5, 0);
    model.study("std2").feature("lsqo").setIndex("pname", "Rfilm_pos", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "1e-4[m^2/S]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", "1e-3", 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "1e-6", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "5e-3", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "cdl_pos", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "0.5[F/m^2]", 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "0.10", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", 1, 2);
    model.study("std2").feature("lsqo").setIndex("pname", "cdlvol_cs_pos", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "1e5[F/m^3]", 3);
    model.study("std2").feature("lsqo").setIndex("scale", "1e6", 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "1e5", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "3e5", 3);
    model.study("std2").feature("lsqo").set("optsolver", "ipopt");
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("lsqo").set("plot", true);
    model.study("std2").feature("lsqo").set("plotgroup", "Default");
    model.study("std2").showAutoSequences("all");

    model.sol("sol10").feature("o1").set("gradientipopt", "numeric");
    model.sol("sol10").feature("o1").set("diffint", "5e-4");

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u5948\u594e\u65af\u7279\u56fe\u4f18\u5316");
    model.result("pg3").set("data", "none");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("data", "dset4");
    model.result("pg3").feature("ptgr1").set("linemarker", "square");
    model.result("pg3").feature("ptgr1").set("legendmethod", "automatic");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("linemarker", "square");
    model.result("pg3").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol10").runAll();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.sol("sol10").label("\u5df2\u4f18\u5316");

    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u963b\u6297\u5efa\u6a21");

    model
         .description("\u8fd9\u4e2a\u9502\u79bb\u5b50\u7535\u6c60\u793a\u4f8b\u63cf\u8ff0\u5e26\u53c2\u6bd4\u7535\u6781\u7684 LTO/NCA \u7535\u6c60\u5355\u5143\u7684\u963b\u6297\u3002\u8be5\u6a21\u578b\u5305\u542b\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u7814\u7a76\u6b65\u9aa4\uff0c\u4ee5\u4f7f\u4eff\u771f\u4e0e\u5b9e\u9a8c\u6d4b\u91cf\u6570\u636e\u76f8\u543b\u5408\u3002");

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

    model.label("li_battery_impedance.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
