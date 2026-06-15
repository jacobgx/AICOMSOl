/*
 * li_battery_pack_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class li_battery_pack_3d {

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

    model.param().remove("rp_neg");
    model.param().remove("rp_pos");
    model.param().remove("sigmas_neg");
    model.param().remove("sigmas_pos");
    model.param().remove("epss_pos");
    model.param().remove("epsl_pos");
    model.param().remove("brugl_pos");
    model.param().remove("epss_neg");
    model.param().remove("epsl_neg");
    model.param().remove("epsl_sep");
    model.param().remove("brugl_sep");
    model.param().remove("i0ref_neg");
    model.param().remove("i0ref_pos");
    model.param().remove("cs0_neg");
    model.param().remove("cs0_pos");
    model.param().remove("cl_0");
    model.param().remove("a");
    model.param().remove("i_1C");
    model.param().remove("i_load");
    model.param().remove("L_neg");
    model.param().remove("L_sep");
    model.param().remove("L_pos");
    model.param().remove("d_can");
    model.param().remove("r_batt");
    model.param().remove("h_batt");
    model.param().remove("r_mandrel");
    model.param().remove("L_neg_cc");
    model.param().remove("L_pos_cc");
    model.param().remove("L_batt");
    model.param().remove("kT_pos");
    model.param().remove("kT_neg");
    model.param().remove("kT_pos_cc");
    model.param().remove("kT_neg_cc");
    model.param().remove("kT_sep");
    model.param().remove("rho_pos");
    model.param().remove("rho_neg");
    model.param().remove("rho_pos_cc");
    model.param().remove("rho_neg_cc");
    model.param().remove("rho_sep");
    model.param().remove("Cp_pos");
    model.param().remove("Cp_neg");
    model.param().remove("Cp_pos_cc");
    model.param().remove("Cp_neg_cc");
    model.param().remove("Cp_sep");
    model.param().remove("kT_batt_ang");
    model.param().remove("kT_batt_r");
    model.param().remove("rho_batt");
    model.param().remove("Cp_batt");
    model.param().remove("cycle_time");
    model.param().remove("T_inlet");
    model.param().remove("T_init");

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
    model.param().set("cs0_neg", "22055", "\u8d1f\u6781\u7684\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("cs0_pos", "4001", "\u6b63\u6781\u7684\u521d\u59cb\u8377\u7535\u72b6\u6001");
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
         .set("kT_batt_il", "(kT_pos*L_pos+kT_neg*L_neg+kT_pos_cc*L_pos_cc+kT_neg_cc*L_neg_cc+kT_sep*L_sep)/L_batt", "\u5c42\u95f4\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("kT_batt_tl", "L_batt/(L_pos/kT_pos+L_neg/kT_neg+L_pos_cc/kT_pos_cc+L_neg_cc/kT_neg_cc+L_sep/kT_sep)", "\u7a7f\u5c42\u7535\u6c60\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("rho_batt", "(rho_pos*L_pos+rho_neg*L_neg+rho_pos_cc*L_pos_cc+rho_neg_cc*L_neg_cc+rho_sep*L_sep)/L_batt", "\u7535\u6c60\u5bc6\u5ea6");
    model.param()
         .set("Cp_batt", "(Cp_pos*L_pos*rho_pos+Cp_neg*L_neg*rho_neg+Cp_pos_cc*L_pos_cc*rho_pos_cc+Cp_neg_cc*L_neg_cc*rho_neg_cc+Cp_sep*L_sep*rho_sep)/(L_batt*rho_batt)", "\u7535\u6c60\u70ed\u5bb9");
    model.param().set("cycle_time", "600[s]", "\u5faa\u73af\u65f6\u95f4");
    model.param().set("T_inlet", "310[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_init", "T_inlet", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("N_fins_model", "3", "\u6a21\u578b\u4e2d\u7684\u51b7\u5374\u7fc5\u7247\u6570");
    model.param().set("N_fins_pack", "50", "\u7535\u6c60\u7ec4\u4e2d\u7684\u51b7\u5374\u7fc5\u7247\u603b\u6570");
    model.param().set("p_out", "1e5[Pa]", "\u51fa\u53e3\u6d41\u4f53\u538b\u529b");
    model.param().set("fin_flow", "0.5e-6[m^3/s]", "\u6bcf\u4e2a\u7fc5\u7247\u7684\u51b7\u5374\u6d41\u91cf");

    model.component("comp2").geom("geom2").insertFile("li_battery_pack_3d_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").run("difsel4");
    model.component("comp2").geom("geom2").run("difsel4");

    model.component("comp2").view("view4").set("transparency", false);

    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("Vol", "0.2*10*10[cm^3]");
    model.component("comp2").variable("var2").descr("Vol", "\u7535\u6c60\u4f53\u79ef");
    model.component("comp2").variable("var2").set("Qh", "comp1.aveop1(comp1.liion.Qh)*(L_neg+L_sep+L_pos)/L_batt");
    model.component("comp2").variable("var2").descr("Qh", "\u7535\u6c60\u6a21\u578b\u4e2d\u7684\u70ed\u6e90");

    model.component("comp2").physics().create("spf", "LaminarFlow", "geom2");
    model.component("comp2").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom2");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/liion", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.func().create("step1", "Step");

    model.component("comp1").variable("var1").set("i_app", "-i_load*step1(t/1[s])");

    model.component("comp1").common("minpt1").set("value", "T_inlet");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().all();

    model.component("comp2").material().create("mat4", "Common");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat4").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp2").material("mat4").label("Water, liquid");
    model.component("comp2").material("mat4").set("family", "water");
    model.component("comp2").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat4").propertyGroup("def").func("eta").label("Piecewise");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp2").material("mat4").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat4").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat4").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat4").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat4").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat4").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat4").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat4").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp2").material("mat4").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp2").material("mat4").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp2").material("mat4").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp2").material("mat4").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat4").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp2").material("mat4").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat4").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat4").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat4").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp2").material("mat4").propertyGroup("def").func("cs")
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
    model.component("comp2").material("mat4").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp2").material("mat4").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp2").material("mat4").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat4").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp2").material("mat4").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp2").material("mat4").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp2").material("mat4").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp2").material("mat4").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp2").material("mat4").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat4").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp2").material("mat4").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat4").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp2").material("mat4").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat4").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat4").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp2").material("mat4").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat4").propertyGroup("def").set("density", "rho(T)");
    model.component("comp2").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat4").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp2").material().create("mat5", "Common");
    model.component("comp2").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat5").label("Aluminum");
    model.component("comp2").material("mat5").set("family", "aluminum");
    model.component("comp2").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat5").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat5").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp2").material("mat4").selection().named("geom2_unisel1");
    model.component("comp2").material("mat5").selection().named("geom2_difsel1");
    model.component("comp2").material("mat5").propertyGroup("def").set("dynamicviscosity", new String[]{"0"});

    model.component("comp2").physics("spf").selection().named("geom2_unisel1");
    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp2").physics("spf").feature("inl1").selection().named("geom2_sel1");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp2").physics("spf").feature("inl1").set("V0fdf", "N_fins_model*fin_flow");
    model.component("comp2").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp2").physics("spf").feature("inl2").selection().named("geom2_sel3");
    model.component("comp2").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("spf").feature("inl2").set("FullyDevelopedFlowOption", "V0");
    model.component("comp2").physics("spf").feature("inl2").set("V0fdf", "(N_fins_pack-N_fins_model)*fin_flow");
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp2").physics("spf").feature("out1").selection().named("geom2_sel2");
    model.component("comp2").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp2").physics("spf").create("init2", "init", 3);
    model.component("comp2").physics("spf").feature("init2").selection().named("geom2_blk4_dom");
    model.component("comp2").physics("spf").feature("init2")
         .set("u_init", new String[]{"0", "(N_fins_pack-N_fins_model)*fin_flow/(8[mm]*16[mm])", "0"});
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp2").physics("ht").feature("fluid1").selection().named("geom2_unisel1");
    model.component("comp2").physics("ht").create("bl1", "BatteryLayers", 3);
    model.component("comp2").physics("ht").feature("bl1").selection().named("geom2_arr1_dom");
    model.component("comp2").physics("ht").feature("bl1").set("ThroughLayerDirection", "y_axis");
    model.component("comp2").physics("ht").feature("bl1").set("ThroughLayerConductivity", "kT_batt_tl");
    model.component("comp2").physics("ht").feature("bl1").set("InLayerConductivity", "kT_batt_il");
    model.component("comp2").physics("ht").feature("bl1").set("Density", "rho_batt");
    model.component("comp2").physics("ht").feature("bl1").set("HeatCapacity", "Cp_batt");
    model.component("comp2").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp2").physics("ht").feature("hs1").selection().named("geom2_arr2_dom");
    model.component("comp2").physics("ht").feature("hs1").set("Q0", "Qh");
    model.component("comp2").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht").feature("temp1").selection().named("geom2_sel1");
    model.component("comp2").physics("ht").feature("temp1").set("T0", "T_inlet");
    model.component("comp2").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp2").physics("ht").feature("ofl1").selection().named("geom2_sel2");
    model.component("comp2").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp2").physics("ht").feature("hf1").selection().named("geom2_difsel2");
    model.component("comp2").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht").feature("hf1").set("h", 1);
    model.component("comp2").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("ht").feature("sym1").selection().set(122);

    model.component("comp2").multiphysics().create("nitf1", "NonIsothermalFlow", 3);

    model.component("comp2").mesh("mesh2").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "0.009[m]");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", "0.00025[m]");
    model.component("comp2").mesh("mesh2").feature("size1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("size1").selection().named("geom2_difsel3");
    model.component("comp2").mesh("mesh2").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size1").set("hnarrowactive", true);
    model.component("comp2").mesh("mesh2").feature("size1").set("hnarrow", 0.2);
    model.component("comp2").mesh("mesh2").feature().duplicate("size2", "size1");
    model.component("comp2").mesh("mesh2").feature("size2").selection().named("geom2_blk5_dom");
    model.component("comp2").mesh("mesh2").feature("size2").set("hnarrow", 0.5);
    model.component("comp2").mesh("mesh2").feature("size2").set("hnarrowactive", false);
    model.component("comp2").mesh("mesh2").feature("size2").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("size2").set("hmax", 1.9);
    model.component("comp2").mesh("mesh2").feature().duplicate("size3", "size2");
    model.component("comp2").mesh("mesh2").feature("size3").selection().named("geom2_blk4_dom");
    model.component("comp2").mesh("mesh2").feature().duplicate("size4", "size3");
    model.component("comp2").mesh("mesh2").feature("size4").selection().geom("geom2", 1);
    model.component("comp2").mesh("mesh2").feature("size4").selection().named("geom2_adjsel5");
    model.component("comp2").mesh("mesh2").feature("size4").set("hmax", 0.25);
    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().named("geom2_unisel1");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().named("geom2_difsel3");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().named("geom2_adjsel1");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", 0.075);
    model.component("comp2").mesh("mesh2").create("ftet2", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("ftet2").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftet2").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftet2").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftet2").feature("size1").set("hmax", 6);
    model.component("comp2").mesh("mesh2").run();

    model.study("std1").feature("stat").setSolveFor("/physics/liion", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").set("probesel", "none");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("time").set("tlist", "range(0,60,60)");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 10);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/liion", true);
    model.study("std3").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("stat").set("probesel", "none");
    model.study("std3").feature("stat").setSolveFor("/physics/liion", false);
    model.study("std3").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std2");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("dset8", "Solution");
    model.result().dataset("dset8").set("solution", "sol3");
    model.result().dataset("dset8").set("comp", "comp2");
    model.result().dataset("dset8").selection().geom("geom2", 3);
    model.result().dataset("dset8").selection().named("geom2_difsel3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset8");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset8");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg3").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg3").run();
    model.result().dataset().create("dset9", "Solution");
    model.result().dataset("dset9").set("solution", "sol3");
    model.result().dataset("dset9").set("comp", "comp2");
    model.result().dataset("dset9").selection().geom("geom2", 3);
    model.result().dataset("dset9").selection().named("geom2_difsel3");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset9");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().dataset("cpl1").set("quicky", "3[mm]");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").run();
    model.result().dataset().create("dset10", "Solution");
    model.result().dataset("dset10").set("solution", "sol3");
    model.result().dataset("dset10").set("comp", "comp2");
    model.result().dataset("dset10").selection().geom("geom2", 3);
    model.result().dataset("dset10").selection().named("geom2_arr2_dom");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "dset10");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg5").run();
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").set("data", "dset10");
    model.result().dataset("cpl2").set("quickplane", "xz");
    model.result().dataset("cpl2").set("quicky", "4[mm]");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("data", "cpl2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "T-T_inlet");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 3);
    model.result("pg6").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg6").feature("surf1").create("hght1", "Height");
    model.result("pg6").run();
    model.result().dataset().create("cpl3", "CutPlane");
    model.result().dataset("cpl3").set("data", "dset10");
    model.result().dataset("cpl3").set("quickplane", "xz");
    model.result().dataset("cpl3").set("quicky", "6[mm]");
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("data", "cpl3");
    model.result("pg6").feature("surf2").set("expr", "T-T_inlet");
    model.result("pg6").feature("surf2").set("rangecoloractive", true);
    model.result("pg6").feature("surf2").set("rangecolormax", 3);
    model.result("pg6").feature("surf2").set("colortable", "ThermalDark");
    model.result("pg6").feature("surf2").set("colorlegend", false);
    model.result("pg6").feature("surf2").create("hght1", "Height");
    model.result("pg6").run();
    model.result("pg6").run();

    model.study("std1").label("\u7814\u7a76 1\uff08\u6d41\u52a8\uff09");
    model.study("std2").label("\u7814\u7a76 2\uff08\u7535\u6c60\uff09");
    model.study("std3").label("\u7814\u7a76 3\uff08\u6e29\u5ea6\uff09");

    model.result("pg2").run();
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b");
    model.result("pg3").run();
    model.result("pg3").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").run();
    model.result("pg4").label("\u677f\u901a\u9053\u901f\u5ea6");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6c60\u6e29\u5ea6");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u6c60\u8868\u9762\u6e29\u5ea6");

    model.component("comp2").geom("geom2").run("difsel4");

    model.component("comp2").view("view4").set("transparency", false);

    model.result("pg5").run();

    model.title("\u6db2\u51b7\u5f0f\u9502\u79bb\u5b50\u7535\u6c60\u7ec4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6db2\u51b7\u5f0f\u7535\u6c60\u7ec4\u7684\u6e29\u5ea6\u5206\u5e03\u3002\u5176\u4e2d\u91c7\u7528\u4e09\u7ef4\u6d41\u4f53\u6d41\u52a8\u548c\u6e29\u5ea6\u6a21\u578b\uff0c\u800c\u4f7f\u7528\u4e00\u7ef4\u7535\u6c60\u6a21\u578b\u6765\u8ba1\u7b97\u70ed\u6e90\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("li_battery_pack_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
