/*
 * li_battery_thermal_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class li_battery_thermal_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rp_neg", "2.50e-6[m]", "\u8d1f\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("rp_pos", "1.70e-6[m]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("sigmas_neg", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "3.8[S/m]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("epss_pos", "1-epsl_pos-0.170", "\u6b63\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_pos", "0.400", "\u6b63\u6781\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("brugl_pos", "2.98", "\u6b63\u6781\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("epss_neg", "1-epsl_neg-0.172", "\u8d1f\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_neg", "0.444", "\u8d1f\u6781\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_sep", "0.370", "\u9694\u819c\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("brugl_sep", "3.15", "\u9694\u819c\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param()
         .set("i0ref_neg", "0.96[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0ref_pos", "17.44[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("cs0_neg", "2205", "\u8d1f\u6781\u7684\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("cs0_pos", "20925", "\u6b63\u6781\u7684\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("cl_0", "1200[mol/m^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param().set("a", "7.5", "C \u56e0\u5b50");
    model.param().set("i_1C", "12[A/m^2]", "1C \u653e\u7535\u7535\u6d41");
    model.param().set("i_load", "i_1C*a", "\u5145\u7535/\u653e\u7535\u7535\u6d41");
    model.param().set("L_neg", "55e-6[m]", "\u8d1f\u6781\u957f\u5ea6");
    model.param().set("L_sep", "30e-6[m]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("L_pos", "55e-6[m]", "\u6b63\u6781\u957f\u5ea6");
    model.param().set("d_can", "0.25[mm]", "\u7535\u6c60\u7f50\u539a\u5ea6");
    model.param().set("r_batt", "9[mm]", "\u7535\u6c60\u534a\u5f84");
    model.param().set("h_batt", "65[mm]", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("r_mandrel", "2[mm]", "\u5fc3\u8f74\u534a\u5f84");
    model.param().set("L_neg_cc", "7[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_pos_cc", "10[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_batt", "L_neg+L_neg_cc+L_sep+L_pos+L_pos_cc", "\u7535\u6c60\u539a\u5ea6");
    model.param().set("kT_pos", "1.58[W/(m*K)]", "\u6b63\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_neg", "1.04[W/(m*K)]", "\u8d1f\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_pos_cc", "170[W/(m*K)]", "\u6b63\u6781\u96c6\u6d41\u4f53\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_neg_cc", "398[W/(m*K)]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("kT_sep", "0.344[W/(m*K)]", "\u9694\u819c\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("rho_pos", "2328.5[kg/m^3]", "\u6b63\u6781\u5bc6\u5ea6");
    model.param().set("rho_neg", "1347.33[kg/m^3]", "\u8d1f\u6781\u5bc6\u5ea6");
    model.param().set("rho_pos_cc", "2770[kg/m^3]", "\u6b63\u6781\u96c6\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("rho_neg_cc", "8933[kg/m^3]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("rho_sep", "1008.98[kg/m^3]", "\u9694\u819c\u5bc6\u5ea6");
    model.param().set("Cp_pos", "1269.21[J/(kg*K)]", "\u6b63\u6781\u70ed\u5bb9");
    model.param().set("Cp_neg", "1437.4[J/(kg*K)]", "\u8d1f\u6781\u70ed\u5bb9");
    model.param().set("Cp_pos_cc", "875[J/(kg*K)]", "\u6b63\u6781\u96c6\u6d41\u4f53\u70ed\u5bb9");
    model.param().set("Cp_neg_cc", "385[J/(kg*K)]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u70ed\u5bb9");
    model.param().set("Cp_sep", "1978.16[J/(kg*K)]", "\u9694\u819c\u70ed\u5bb9");
    model.param()
         .set("kT_batt_ang", "(kT_pos*L_pos+kT_neg*L_neg+kT_pos_cc*L_pos_cc+kT_neg_cc*L_neg_cc+kT_sep*L_sep)/L_batt", "\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570\uff0c\u89d2\u5ea6");
    model.param()
         .set("kT_batt_r", "L_batt/(L_pos/kT_pos+L_neg/kT_neg+L_pos_cc/kT_pos_cc+L_neg_cc/kT_neg_cc+L_sep/kT_sep)", "\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570\uff0c\u5f84\u5411");
    model.param()
         .set("rho_batt", "(rho_pos*L_pos+rho_neg*L_neg+rho_pos_cc*L_pos_cc+rho_neg_cc*L_neg_cc+rho_sep*L_sep)/L_batt", "\u7535\u6c60\u5bc6\u5ea6");
    model.param()
         .set("Cp_batt", "(Cp_pos*L_pos*rho_pos+Cp_neg*L_neg*rho_neg+Cp_pos_cc*L_pos_cc*rho_pos_cc+Cp_neg_cc*L_neg_cc*rho_neg_cc+Cp_sep*L_sep*rho_sep)/(L_batt*rho_batt)", "\u7535\u6c60\u70ed\u5bb9");
    model.param().set("cycle_time", "600[s]", "\u5faa\u73af\u65f6\u95f4");
    model.param().set("T_inlet", "298.15[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_init", "T_inlet", "\u521d\u59cb\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u8d1f\u6781");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection("sel2").label("\u6b63\u6781");

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

    return model;
  }

  public static Model run2(Model model) {
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat3").label("LMO, LiMn2O4 Spinel (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.1750", "4.2763"}, 
         {"0.1950", "4.1898"}, 
         {"0.2150", "4.1507"}, 
         {"0.2350", "4.133"}, 
         {"0.2550", "4.1248"}, 
         {"0.2750", "4.1209"}, 
         {"0.2950", "4.119"}, 
         {"0.3150", "4.1179"}, 
         {"0.3350", "4.1171"}, 
         {"0.3550", "4.1165"}, 
         {"0.3750", "4.116"}, 
         {"0.3950", "4.1153"}, 
         {"0.4150", "4.1145"}, 
         {"0.4350", "4.1135"}, 
         {"0.4550", "4.1121"}, 
         {"0.4750", "4.1099"}, 
         {"0.4950", "4.1066"}, 
         {"0.5150", "4.1014"}, 
         {"0.5350", "4.0934"}, 
         {"0.5550", "4.082"}, 
         {"0.5750", "4.067"}, 
         {"0.5950", "4.05"}, 
         {"0.6150", "4.0333"}, 
         {"0.6350", "4.0192"}, 
         {"0.6550", "4.0087"}, 
         {"0.6750", "4.0012"}, 
         {"0.6950", "3.996"}, 
         {"0.7150", "3.9923"}, 
         {"0.7350", "3.9893"}, 
         {"0.7550", "3.9867"}, 
         {"0.7750", "3.9841"}, 
         {"0.7950", "3.9813"}, 
         {"0.8150", "3.9783"}, 
         {"0.8350", "3.9747"}, 
         {"0.8550", "3.9705"}, 
         {"0.8750", "3.9652"}, 
         {"0.8950", "3.9585"}, 
         {"0.9150", "3.9493"}, 
         {"0.9350", "3.9361"}, 
         {"0.9550", "3.9144"}, 
         {"0.9750", "3.869"}, 
         {"0.9950", "3.5944"}, 
         {"", ""}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.15", "0.15e-3"}, 
         {"0.18", "0.25e-3"}, 
         {"0.20", "0.21e-3"}, 
         {"0.209", "0.19e-3"}, 
         {"0.26", "0.175e-3"}, 
         {"0.28", "0.166e-3"}, 
         {"0.30", "0.155e-3"}, 
         {"0.35", "0.11e-3"}, 
         {"0.394", "0.095e-3"}, 
         {"0.41", "0.05e-3"}, 
         {"0.437", "0.02e-3"}, 
         {"0.445", "0"}, 
         {"0.46", "-0.048e-3"}, 
         {"0.48", "-0.15e-3"}, 
         {"0.50", "-0.255e-3"}, 
         {"0.515", "-0.3e-3"}, 
         {"0.545", "-0.3e-3"}, 
         {"0.553", "-0.22e-3"}, 
         {"0.58", "-0.145e-3"}, 
         {"0.592", "-0.10e-3"}, 
         {"0.60", "0"}, 
         {"0.62", "0.08e-3"}, 
         {"0.64", "0.12e-3"}, 
         {"0.70", "0.124e-3"}, 
         {"0.72", "0.10e-3"}, 
         {"0.73", "0.05e-3"}, 
         {"0.76", "0"}, 
         {"0.78", "-0.057e-3"}, 
         {"0.81", "-0.08e-3"}, 
         {"0.86", "-0.10e-3"}, 
         {"0.91", "-0.16e-3"}, 
         {"0.96", "-0.22e-3"}, 
         {"0.98", "-0.30e-3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "194[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.26");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrochemical and Electronic Charge Transport Properties of Ni-Doped LiMn2O4 Spinel Obtained from Polyol-Mediated Synthesis, Shuo Yang et al, Materials 2018, 11(5), 806");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "4140[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "22860[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
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
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.9[V]");
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
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fDl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1").selection().named("sel1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs0_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("HeatMix", true);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").feature("pce1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().named("sel2");
    model.component("comp1").physics("liion").feature("pce2").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fl", "epsl_pos^brugl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fDl", "epsl_pos^brugl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("csinit", "cs0_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("HeatMix", true);
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("liion").feature("pce2")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(4);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "i_app");
    model.component("comp1").physics("liion").create("init2", "init", 1);
    model.component("comp1").physics("liion").feature("init2").selection().named("sel2");
    model.component("comp1").physics("liion").feature("init2").set("phil", "-mat2.def.Eeq(cs0_neg/mat2.def.csmax)");
    model.component("comp1").physics("liion").feature("init2").set("cl", "cl_0");
    model.component("comp1").physics("liion").feature("init2")
         .set("phis", "mat3.def.Eeq(cs0_pos/mat3.def.csmax)-mat2.def.Eeq(cs0_neg/mat2.def.csmax)");
    model.component("comp1").physics("liion").feature("init1").set("phil", "-mat2.def.Eeq(cs0_neg/mat2.def.csmax)");
    model.component("comp1").physics("liion").feature("init1").set("cl", "cl_0");

    model.component("comp1").common().create("minpt1", "CommonInputDef");
    model.component("comp1").common("minpt1").selection().all();
    model.component("comp1").common("minpt1").set("quantity", "temperature");

    model.func().create("wv1", "Wave");
    model.func("wv1").set("type", "square");
    model.func("wv1").set("period", "cycle_time");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("i_app", "i_load*(wv1(t/1[s]))*(t<1500)");
    model.component("comp1").variable("var1").descr("i_app", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords1", "L_neg+L_sep+L_neg", 0);
    model.component("comp1").probe("pdom1").set("bndsnap1", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "CellVoltageProbe");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "phis");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u7535\u52bf");
    model.component("comp1").probe("pdom1").feature("ppb1").set("window", "window1");
    model.component("comp1").probe("pdom1").feature("ppb1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "CellCurrentProbe");
    model.component("comp1").probe("var1").set("expr", "i_app/i_1C");
    model.component("comp1").probe("var1").set("window", "window1");

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u70ed\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u5305\u542b\u9502\u79bb\u5b50\u7535\u6c60\u7269\u7406\u573a\u63a5\u53e3\u3001\u51e0\u4f55\u548c\u7f51\u683c\u7684\u6a21\u677f MPH \u6587\u4ef6\uff0c\u7528\u4e8e\u201c\u5706\u67f1\u5f62\u9502\u79bb\u5b50\u7535\u6c60\u70ed\u5efa\u6a21 - \u4e09\u7ef4\u201d\u548c\u201c\u6db2\u51b7\u5f0f\u9502\u79bb\u5b50\u7535\u6c60\u7ec4\u201d\u6a21\u578b\u3002");

    model.label("li_battery_1d_for_thermal_models.mph");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("spf", "LaminarFlow", "geom2");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom2");

    model.component("comp2").multiphysics().create("nitf1", "NonIsothermalFlow", 3);

    model.param().set("h_connector", "3[mm]");
    model.param().descr("h_connector", "\u63a5\u5934\u9ad8\u5ea6");
    model.param().set("r_connector", "3[mm]");
    model.param().descr("r_connector", "\u63a5\u5934\u534a\u5f84");
    model.param().set("s_inlet", "2*r_batt");
    model.param().descr("s_inlet", "\u5165\u53e3\u6d41\u52a8\u533a\u57df\u957f\u5ea6");
    model.param().set("s_matrix", "3*r_batt");
    model.param().descr("s_matrix", "\u77e9\u9635\u4e2d\u7535\u6c60\u7684\u95f4\u8ddd");
    model.param().set("V_in", "0.1[m/s]");
    model.param().descr("V_in", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("t", "0");
    model.param().descr("t", "\u521d\u59cb\u5316\u7814\u7a76\u6b65\u4e2d\u7684\u65f6\u95f4\u53c2\u6570");

    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "r_batt");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "h_batt");
    model.component("comp2").geom("geom2").run("cyl1");

    model.component("comp2").view("view4").set("transparency", true);

    model.component("comp2").geom("geom2").create("cyl2", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl2").set("r", "r_mandrel");
    model.component("comp2").geom("geom2").feature("cyl2").set("h", "h_batt");
    model.component("comp2").geom("geom2").run("cyl2");
    model.component("comp2").geom("geom2").create("cyl3", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl3").set("r", "r_connector");
    model.component("comp2").geom("geom2").feature("cyl3").set("h", "h_connector");
    model.component("comp2").geom("geom2").feature("cyl3").set("pos", new String[]{"0", "0", "h_batt"});
    model.component("comp2").geom("geom2").run("cyl3");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("cyl1", "cyl2", "cyl3");
    model.component("comp2").geom("geom2").run("uni1");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"2*r_batt", "r_batt", "1"});
    model.component("comp2").geom("geom2").feature("blk1").setIndex("size", "h_batt+h_connector", 2);
    model.component("comp2").geom("geom2").feature("blk1").set("pos", new String[]{"-r_batt", "0", "0"});
    model.component("comp2").geom("geom2").run("blk1");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("blk1", "uni1");
    model.component("comp2").geom("geom2").run("int1");
    model.component("comp2").geom("geom2").create("blk2", "Block");
    model.component("comp2").geom("geom2").feature("blk2").set("size", new String[]{"s_inlet+s_matrix", "1", "1"});
    model.component("comp2").geom("geom2").feature("blk2").setIndex("size", "s_matrix/2", 1);
    model.component("comp2").geom("geom2").feature("blk2").setIndex("size", "h_batt-5[mm]", 2);
    model.component("comp2").geom("geom2").feature("blk2").set("pos", new String[]{"-s_inlet", "0", "0"});
    model.component("comp2").geom("geom2").run("blk2");
    model.component("comp2").geom("geom2").create("blk3", "Block");
    model.component("comp2").geom("geom2").feature("blk3").set("size", new String[]{"s_inlet+s_matrix", "1", "1"});
    model.component("comp2").geom("geom2").feature("blk3").setIndex("size", "s_matrix/2", 1);
    model.component("comp2").geom("geom2").feature("blk3").setIndex("size", "h_connector+5[mm]", 2);
    model.component("comp2").geom("geom2").feature("blk3").set("pos", new String[]{"-s_inlet", "0", "h_batt-5[mm]"});
    model.component("comp2").geom("geom2").run("blk3");
    model.component("comp2").geom("geom2").create("cyl4", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl4").set("r", "r_batt");
    model.component("comp2").geom("geom2").feature("cyl4").set("h", "h_batt");
    model.component("comp2").geom("geom2").feature("cyl4").set("pos", new String[]{"s_matrix", "s_matrix/2", "0"});
    model.component("comp2").geom("geom2").run("cyl4");
    model.component("comp2").geom("geom2").create("cyl5", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl5").set("r", "r_connector");
    model.component("comp2").geom("geom2").feature("cyl5").set("h", "h_connector");
    model.component("comp2").geom("geom2").feature("cyl5").set("pos", new String[]{"s_matrix", "s_matrix/2", "0"});
    model.component("comp2").geom("geom2").feature("cyl5").setIndex("pos", "h_batt", 2);
    model.component("comp2").geom("geom2").run("cyl5");
    model.component("comp2").geom("geom2").create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("dif1").selection("input").set("blk2", "blk3", "int1");
    model.component("comp2").geom("geom2").feature("dif1").selection("input2").set("cyl4", "cyl5");
    model.component("comp2").geom("geom2").run("dif1");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("mcf1", "MeshControlFaces");
    model.component("comp2").geom("geom2").feature("mcf1").selection("input").set("fin", 6, 15, 26);

    model.component("comp2").selection().create("sel3", "Explicit");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").selection("sel3").label("\u6d41\u52a8\u5ba4");
    model.component("comp2").selection("sel3").set(1);
    model.component("comp2").selection().create("sel4", "Explicit");
    model.component("comp2").selection("sel4").label("\u7535\u6c60\u6d3b\u6027\u6750\u6599");
    model.component("comp2").selection("sel4").set(2);
    model.component("comp2").selection().create("sel5", "Explicit");
    model.component("comp2").selection("sel5").label("\u7535\u6c60\u8fde\u63a5\u5934");
    model.component("comp2").selection("sel5").set(3);
    model.component("comp2").selection().create("sel6", "Explicit");
    model.component("comp2").selection("sel6").label("\u82af\u8f74");
    model.component("comp2").selection("sel6").set(4);
    model.component("comp2").selection().create("sel7", "Explicit");
    model.component("comp2").selection("sel7").label("\u5165\u53e3");
    model.component("comp2").selection("sel7").geom(2);
    model.component("comp2").selection("sel7").set(1);
    model.component("comp2").selection().create("sel8", "Explicit");
    model.component("comp2").selection("sel8").label("\u51fa\u53e3");
    model.component("comp2").selection("sel8").geom(2);
    model.component("comp2").selection("sel8").set(26);
    model.component("comp2").selection().create("sel9", "Explicit");
    model.component("comp2").selection("sel9").label("\u5bf9\u79f0\u5e73\u9762");
    model.component("comp2").selection("sel9").geom(2);
    model.component("comp2").selection("sel9").set(2, 5, 22);
    model.component("comp2").selection().create("sel10", "Explicit");
    model.component("comp2").selection("sel10").label("\u7f50");
    model.component("comp2").selection("sel10").geom(2);
    model.component("comp2").selection("sel10").set(7, 8, 9, 12, 16, 17, 20);

    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").selection().named("sel4");

    model.component("comp1").common("minpt1").set("value", "nojac(comp2.aveop1(comp2.T))");

    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().all();

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2")
         .set("Qh", "nojac(comp1.aveop2(comp1.liion.Qh))*(L_neg+L_sep+L_pos)/L_batt*((r_batt-d_can)^2-r_mandrel^2)*(h_batt-d_can*2)/((r_batt^2-r_mandrel^2)*h_batt)");
    model.component("comp2").variable("var2")
         .descr("Qh", "\u4e00\u7ef4\u7535\u6c60\u6a21\u578b\u7684\u5e73\u5747\u70ed\u6e90");
    model.component("comp2").variable("var2").set("r", "sqrt(x^2+y^2)");
    model.component("comp2").variable("var2").descr("r", "\u534a\u5f84");

    model.component("comp1").probe("pdom1").feature("ppb1").set("window", "window2");
    model.component("comp1").probe("pdom1").feature("ppb1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp2").probe().create("dom1", "Domain");
    model.component("comp2").probe("dom1").set("intsurface", true);
    model.component("comp2").probe("dom1").set("intvolume", true);
    model.component("comp2").probe("dom1").set("probename", "MeanT");
    model.component("comp2").probe("dom1").selection().named("sel4");
    model.component("comp2").probe("dom1").set("expr", "T-T_inlet");
    model.component("comp2").probe("dom1").set("window", "window1");
    model.component("comp2").probe().create("dom2", "Domain");
    model.component("comp2").probe("dom2").set("intsurface", true);
    model.component("comp2").probe("dom2").set("intvolume", true);
    model.component("comp2").probe("dom2").set("type", "maximum");
    model.component("comp2").probe("dom2").set("probename", "MaxT");
    model.component("comp2").probe("dom2").selection().named("sel4");
    model.component("comp2").probe("dom2").set("expr", "T-T_inlet");
    model.component("comp2").probe("dom2").set("window", "window1");
    model.component("comp2").probe().create("dom3", "Domain");
    model.component("comp2").probe("dom3").set("intsurface", true);
    model.component("comp2").probe("dom3").set("intvolume", true);
    model.component("comp2").probe("dom3").set("type", "minimum");
    model.component("comp2").probe("dom3").set("probename", "MinT");
    model.component("comp2").probe("dom3").selection().named("sel4");
    model.component("comp2").probe("dom3").set("expr", "T-T_inlet");
    model.component("comp2").probe("dom3").set("window", "window1");

    return model;
  }

  public static Model run3(Model model) {

    model.component("comp2").material().create("mat4", "Common");
    model.component("comp2").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat4").label("Steel AISI 4340");
    model.component("comp2").material("mat4").set("family", "steel");
    model.component("comp2").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat4").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat4").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat4").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp2").material("mat4").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp2").material().create("mat5", "Common");
    model.component("comp2").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat5").label("Nylon");
    model.component("comp2").material("mat5").set("family", "custom");
    model.component("comp2").material("mat5")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat5").set("diffuse", "custom");
    model.component("comp2").material("mat5")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp2").material("mat5").set("ambient", "custom");
    model.component("comp2").material("mat5")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp2").material("mat5").set("noise", true);
    model.component("comp2").material("mat5").set("lighting", "phong");
    model.component("comp2").material("mat5").set("shininess", 500);
    model.component("comp2").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat5").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp2").material("mat5").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp2").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat5").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp2").material("mat5").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp2").material().create("mat6", "Common");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat6").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat6").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat6").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat6").label("Air");
    model.component("comp2").material("mat6").set("family", "air");
    model.component("comp2").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat6").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat6").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat6").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat6").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat6").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat6").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat6").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat6").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat6").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat6").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat6").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat6").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat6").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat6").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat6").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat6").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat6").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat6").materialType("nonSolid");
    model.component("comp2").material().create("mat7", "Common");
    model.component("comp2").material("mat7").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat7").label("Steel AISI 4340.1");
    model.component("comp2").material("mat7").set("family", "steel");
    model.component("comp2").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat7").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat7").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat7").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat7").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp2").material("mat7").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp2").material("mat4").selection().named("sel5");
    model.component("comp2").material("mat5").selection().named("sel6");
    model.component("comp2").material("mat6").selection().named("sel3");
    model.component("comp2").material("mat7").selection().geom("geom2", 2);
    model.component("comp2").material("mat7").selection().named("sel10");

    model.component("comp2").coordSystem().create("sys2", "Cylindrical");

    model.component("comp2").physics("ht").create("solid2", "SolidHeatTransferModel", 3);
    model.component("comp2").physics("ht").feature("solid2").selection().named("sel4");
    model.component("comp2").physics("ht").feature("solid2").set("coordinateSystem", "sys2");
    model.component("comp2").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp2").physics("ht").feature("solid2")
         .set("k", new String[]{"kT_batt_r", "0", "0", "0", "kT_batt_ang", "0", "0", "0", "kT_batt_ang"});
    model.component("comp2").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp2").physics("ht").feature("solid2").set("rho", "rho_batt");
    model.component("comp2").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp2").physics("ht").feature("solid2").set("Cp", "Cp_batt");
    model.component("comp2").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp2").physics("ht").feature("hs1").selection().named("sel4");
    model.component("comp2").physics("ht").feature("hs1").set("Q0", "Qh");
    model.component("comp2").physics("spf").selection().named("sel3");
    model.component("comp2").physics("ht").feature("fluid1").selection().named("sel3");
    model.component("comp2").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht").feature("temp1").selection().named("sel7");
    model.component("comp2").physics("ht").feature("temp1").set("T0", "T_inlet");
    model.component("comp2").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp2").physics("ht").feature("ofl1").selection().named("sel8");
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp2").physics("spf").feature("out1").selection().named("sel8");
    model.component("comp2").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp2").physics("spf").feature("inl1").selection().named("sel7");
    model.component("comp2").physics("spf").feature("inl1").set("U0in", "V_in");
    model.component("comp2").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("spf").feature("sym1").selection().named("sel9");
    model.component("comp2").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_init");
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "T_init");

    model.component("comp2").mesh("mesh2").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("size1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("size1").selection().named("sel3");
    model.component("comp2").mesh("mesh2").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh2").feature("size1").set("hauto", 4);
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").selection().set(1, 2, 4);
    model.component("comp2").mesh("mesh2").feature("swe1").set("facemethod", "tri");
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 10);
    model.component("comp2").mesh("mesh2").run("swe1");
    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("ftet1").set("smoothcontrol", false);
    model.component("comp2").mesh("mesh2").run("ftet1");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().named("sel3");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection()
         .set(3, 4, 7, 9, 11, 19, 20, 23, 24, 25);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "2e-4");
    model.component("comp2").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp2").mesh("mesh2").run("bl1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/liion", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").setSolveFor("/physics/liion", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time")
         .set("tlist", "0 299.95 300 599.95 600 899.95 900 1199.95 1200 1499.95 1500 2100");
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("consistent", false);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdtech", "auto");
    model.sol("sol1").feature("t1").feature("se1").feature().move("ss2", 0);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp2").probe("dom1").genResult("none");
    model.component("comp2").probe("dom2").genResult("none");
    model.component("comp2").probe("dom3").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u7535\u6c60\u7535\u4f4d\u548c\u8d1f\u8f7d");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "\u7535\u6c60\u8d1f\u8f7d (1)");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").label("\u7535\u6c60\u7535\u4f4d");
    model.result("pg1").feature("tblp1").set("autoplotlabel", true);
    model.result("pg1").feature("tblp1").set("autoheaders", false);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg1").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg1").feature("tblp2").label("\u7535\u6c60\u8d1f\u8f7d\uff08\u5145\u653e\u7535\u500d\u7387\uff09");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6e29\u5ea6\u53d8\u5316\u548c\u8d1f\u8f7d");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\\DELTA T (K)");
    model.result("pg2").set("yseclabelactive", true);
    model.result("pg2").set("yseclabel", "\u7535\u6c60\u8d1f\u8f7d (1)");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{4, 5, 6});
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u5e73\u5747", 0);
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6700\u9ad8", 1);
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6700\u4f4e", 2);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").create("tblp2", "Table");
    model.result("pg2").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp2").set("linewidth", "preference");
    model.result("pg2").feature("tblp2").label("\u7535\u6c60\u8d1f\u8f7d\uff08\u5145\u653e\u7535\u500d\u7387\uff09");
    model.result("pg2").feature("tblp2").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg2").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg2").feature("tblp2").set("legend", true);
    model.result("pg2").feature("tblp2").set("autoplotlabel", true);
    model.result("pg2").feature("tblp2").set("autoheaders", false);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").label("\u6e29\u5ea6");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();

    model.component("comp2").selection().create("adj1", "Adjacent");
    model.component("comp2").selection("adj1").set("input", new String[]{"sel4", "sel5", "sel6"});
    model.component("comp2").selection("adj1").set("interior", true);
    model.component("comp2").selection("adj1").label("\u7535\u6c60\u8868\u9762");

    model.result().dataset().create("dset9", "Solution");
    model.result().dataset("dset9").set("comp", "comp2");
    model.result().dataset("dset9").selection().geom("geom2", 2);
    model.result().dataset("dset9").selection().named("adj1");

    model.component("comp2").view("view4").set("transparency", false);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\u548c\u6d41\u52a8");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("data", "none");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "dset9");
    model.result("pg3").feature("surf1").setIndex("looplevel", 11, 0);
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "Thermal");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("data", "dset9");
    model.result("pg3").feature("str1").setIndex("looplevel", 11, 0);
    model.result("pg3").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg3").feature("str1").set("posmethod", "selection");
    model.result("pg3").feature("str1").selection().named("sel7");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg3").feature("str1").feature("col1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg3").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").label("\u8d1f\u8f7d\u5faa\u73af");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6 vs. \u65f6\u95f4");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/liion", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").setSolveFor("/physics/liion", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time")
         .set("tlist", "0 299.95 300 599.95 600 899.95 900 1199.95 1200 1499.95 1500 2100");
    model.study("std2").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol4").feature("t1").set("consistent", false);
    model.sol("sol4").feature("t1").feature("se1").feature("ss3").set("subdtech", "auto");
    model.sol("sol4").feature("t1").feature("se1").feature().move("ss3", 1);
    model.sol("sol4").feature("t1").feature("se1").feature().move("ss3", 0);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp2").probe("dom1").genResult("none");
    model.component("comp2").probe("dom2").genResult("none");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp2").probe("dom3").genResult("none");

    model.sol("sol4").runAll();

    model.result().dataset().create("join1", "Join");
    model.result().dataset("join1").set("data", "dset9");
    model.result().dataset("join1").set("data2", "dset11");
    model.result().dataset().duplicate("join2", "join1");
    model.result().dataset("join2").set("data", "dset2");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u6e29\u5ea6\u548c\u6d41\u52a8\uff08\u53cc\u5411\u8026\u5408\u65b9\u6cd5\u4e0e\u5355\u5411\u8026\u5408\u65b9\u6cd5\u4e4b\u95f4\u7684\u6bd4\u8f83\uff09");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "join1");
    model.result("pg4").run();
    model.result("pg4").feature("str1").set("data", "join2");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().dataset("dset7").set("solution", "sol1");
    model.result().dataset("dset8").set("solution", "sol1");

    model.study().remove("std2");

    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{4, 5, 6});
    model.result("pg3").run();

    model.title("\u5706\u67f1\u5f62\u9502\u79bb\u5b50\u7535\u6c60\u70ed\u5efa\u6a21 - \u4e09\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u98ce\u51b7\u5706\u67f1\u7535\u6c60\u4e09\u7ef4\u6a21\u578b\u4e2d\u7684\u70ed\u91cf\u5206\u5e03\u3002\u7535\u6c60\u653e\u7f6e\u5728\u7535\u6c60\u7ec4\u77e9\u9635\u4e2d\u3002\u70ed\u6a21\u578b\u4e0e\u4e00\u7ef4\u7535\u6c60\u6a21\u578b\u8fdb\u884c\u8026\u5408\uff0c\u540e\u8005\u7528\u4e8e\u5728\u6d3b\u6027\u7535\u6c60\u6750\u6599\u4e2d\u751f\u6210\u70ed\u6e90\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("li_battery_thermal_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
