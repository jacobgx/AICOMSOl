/*
 * li_battery_sigr_hysteresis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_sigr_hysteresis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_sep", "25[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("sigma_s", "10[S/m]", "\u7535\u6781\u7535\u5bfc\u7387");
    model.param().set("eps_binder_el", "0.1", "\u7535\u6781\u9ecf\u5408\u5242\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_el", "0.6", "\u7535\u6781\u76f8\u4f53\u79ef\u5206\u6570 (Gr+Si)");
    model.param()
         .set("epsl_el", "1-eps_binder_el-epss_el", "\u591a\u5b54\u7535\u6781\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("Si_f", "1[%]", "\u7535\u6781\u6df7\u5408\u7269\u4e2d Si \u7684\u5206\u6570");
    model.param().set("epss_Gr", "epss_el*(1-Si_f)", "\u7535\u6781\u7684\u77f3\u58a8\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_Si", "epss_el*Si_f", "\u7535\u6781\u7684\u7845\u4f53\u79ef\u5206\u6570");
    model.param().set("rp_Gr", "5[um]", "\u7535\u6781\u4e2d\u7684\u77f3\u58a8\u9897\u7c92\u534a\u5f84");
    model.param().set("rp_Si", "1[um]", "\u7535\u6781\u4e2d\u7684\u7845\u9897\u7c92\u534a\u5f84");
    model.param().set("cs_Gr_max", "31507[mol/m^3]", "\u77f3\u58a8\u4e2d\u7684\u6700\u5927\u9502\u6d53\u5ea6");
    model.param()
         .set("E_max_el", "0.95[V]", "\u7528\u4e8e\u5b9a\u4e49 0% \u7535\u6781 SOC \u7684\u7535\u6781\u7535\u4f4d");
    model.param()
         .set("E_min_el", "0.075[V]", "\u7528\u4e8e\u5b9a\u4e49 100% \u7535\u6781 SOC \u7684\u7535\u6781\u7535\u4f4d");
    model.param()
         .set("x_Gr_max", "comp1.mat1.def.Eeq_inv(E_min_el)", "100% SOC \u65f6\u7684\u77f3\u58a8\u63d2\u5c42\u6c34\u5e73");
    model.param()
         .set("x_Gr_min", "comp1.mat1.def.Eeq_inv(E_max_el)", "0% SOC \u65f6\u7684\u77f3\u58a8\u63d2\u5c42\u6c34\u5e73");
    model.param()
         .set("x_Si_max", "comp1.Eeq_Si_lower_inv(E_min_el)", "100% SOC \u65f6\u7684\u7845\u63d2\u5c42\u6c34\u5e73");
    model.param()
         .set("x_Si_min", "comp1.Eeq_Si_upper_inv(E_max_el)", "0% SOC \u65f6\u7684\u7845\u63d2\u5c42\u6c34\u5e73");
    model.param().set("cs_Si_max", "278000[mol/m^3]", "\u7845\u4e2d\u7684\u6700\u5927\u9502\u6d53\u5ea6");
    model.param().set("Q_el", "20[Ah/m^2]", "\u7535\u6781\u5bb9\u91cf");
    model.param()
         .set("L_el", "Q_el/(epss_Gr*(x_Gr_max-x_Gr_min)*cs_Gr_max+epss_Si*(x_Si_max-x_Si_min)*cs_Si_max)/F_const", "\u7535\u6781\u539a\u5ea6");
    model.param().set("x_Gr_init", "x_Gr_min", "\u77f3\u58a8\u4e2d\u7684\u521d\u59cb\u63d2\u5c42\u6c34\u5e73");
    model.param().set("x_Si_init", "x_Si_min", "\u7845\u4e2d\u7684\u521d\u59cb\u63d2\u5c42\u6c34\u5e73");
    model.param().set("S_init", "1", "\u5185\u5b58\u53d8\u91cf\u521d\u59cb\u503c");
    model.param().set("E_init_el", "E_max_el", "\u521d\u59cb\u7535\u6781\u7535\u4f4d");
    model.param().set("epsl_sep", "0.4", "\u9694\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("cs_Gr_init", "x_Gr_init*cs_Gr_max", "\u77f3\u58a8\u4e2d\u7684\u521d\u59cb\u9502\u6d53\u5ea6\u6c34\u5e73");
    model.param()
         .set("cs_Si_init", "x_Si_init*cs_Si_max", "\u7845\u4e2d\u7684\u521d\u59cb\u9502\u6d53\u5ea6\u6c34\u5e73");
    model.param()
         .set("i0_ref_Gr", "100[A/m^2]", "\u77f3\u58a8\u63d2\u5c42\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff08\u9502\u5316 50% \u65f6\uff09");
    model.param()
         .set("i0_ref_Si", "100[A/m^2]", "\u77f3\u58a8\u63d2\u5c42\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff08\u9502\u5316 50% \u65f6\uff09");
    model.param()
         .set("i0_ref_Li", "100[A/m^2]", "\u9502\u91d1\u5c5e\u6c27\u5316\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("K_S", "100", "\u5185\u5b58\u53ef\u53d8\u901f\u7387\u5e38\u6570");
    model.param().set("C_rate", "1", "\u9502\u5316/\u8131\u9502\u901f\u7387");
    model.param().set("i_1C", "Q_el/1[h]", "1C \u5bf9\u5e94\u7684\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("E_switch", "E_min_el", "\u9502\u5316\u5230\u8131\u9502\u7684\u5207\u6362\u7535\u4f4d");
    model.param().set("E_end", "E_max_el", "\u4eff\u771f\u7ec8\u6b62\u7535\u4f4d");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat1").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat1").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat1").label("Graphite, LixC6 MCMB (Negative, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "Eeq");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
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
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("funcname", "dEeqdT");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"0", "3.0e-4"}, 
         {"0.17", "0"}, 
         {"0.24", "-6e-5"}, 
         {"0.28", "-1.6e-4"}, 
         {"0.5", "-1.6e-4"}, 
         {"0.54", "-9e-5"}, 
         {"0.71", "-9e-5"}, 
         {"0.85", "-1.0e-4"}, 
         {"1.0", "-1.2e-4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "E_int(c/csmax)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "nu_int(c/csmax)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"100[S/m]", "0", "0", "0", "100[S/m]", "0", "0", "0", "100[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "V. Srinivasan, and J. Newman, \u201cDesign and Optimization of a Natural Graphite/Iron Phosphate Lithium Ion Cell,\u201d J. Electrochem. Soc., vol. 151, p. 1530, 2004.");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "K. Kumaresan, G. Sikha, and R. E. White, \u201cThermal Model for a Li-Ion Cell,\u201d J. Electrochem. Soc., vol. 155, p. A164, 2008.");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "S. Chen, C. Wan, and Y. Wang, J. Power Sources, 140, 111 (2005).");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "750[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat1").propertyGroup("def").set("csmax", "31507[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_ref", "318[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "D. P Karthikeyan, G. Sikha, and R. E. White, \u201cThermodynamic model development for lithium intercalation electrodes,\u201d J. Power Sources, vol. 185, p. 1398, 2008.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
    model.component("comp1").material("mat1").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1")
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
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat1").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat1").propertyGroup("ic")
         .setPropertyInfo("dvol", "S. Schweidler, L. de Biasi, A. Schiele, P. Hartmann, T. Brezesinski and J. Janek, \"Volume Changes of Graphite Anodes Revisited: A Combined Operando X-Ray Diffraction and In Situ Pressure Analysis Study\", J. Phys. Chem. C, 2018, 122, 8829\u20138835");
    model.component("comp1").material("mat1").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat2").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat2").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").addInput("concentration");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").label("Lithium Metal, Li (Negative, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.34");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "0.534[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "3.58[kJ/kg/K]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("temperature");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u63d2\u503c - Eeq Si \u4e0a\u9650");
    model.component("comp1").func("int1").set("funcname", "Eeq_Si_upper");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0.0004433324856174675", "0.9912126584246127"}, 
         {"0.008533291176652524", "0.9548868880914604"}, 
         {"0.02202783504119", "0.9068414899989796"}, 
         {"0.036424905504354815", "0.8587955631855859"}, 
         {"0.05352638343792315", "0.807233113579298"}, 
         {"0.07244137410335542", "0.7650427708782637"}, 
         {"0.09406500200649588", "0.7240224875578752"}, 
         {"0.11388992136333863", "0.6900331349396228"}, 
         {"0.13822430138783953", "0.6525262020866501"}, 
         {"0.16526837609187478", "0.6173609741577081"}, 
         {"0.19411856143499084", "0.5833663343303248"}, 
         {"0.22567949889946748", "0.5528850449703575"}, 
         {"0.25634108209079515", "0.5259192209614579"}, 
         {"0.293325638681645", "0.504807923623092"}, 
         {"0.3285061995170664", "0.484869329269937"}, 
         {"0.3681983359037982", "0.4637564457688318"}, 
         {"0.4060843616514492", "0.4414729741661676"}, 
         {"0.4403613384464171", "0.42036326299054094"}, 
         {"0.4746340854740803", "0.39456696964137405"}, 
         {"0.5098083016585446", "0.36759850202790934"}, 
         {"0.5576147178983133", "0.33710769569150645"}, 
         {"0.6009159032377283", "0.31482105176336384"}, 
         {"0.6406122693917647", "0.29839475043579844"}, 
         {"0.6848212685389379", "0.2819658055036678"}, 
         {"0.7263226878902291", "0.2655384467342762"}, 
         {"0.765114412561986", "0.24676938304085405"}, 
         {"0.8165245905453069", "0.20924658856048883"}, 
         {"0.8390518024889009", "0.16939742206257236"}, 
         {"0.8498345367902516", "0.11666702795929096"}, 
         {"0.8533864838843223", "0.05222440818946428"}});
    model.component("comp1").func("int1").set("extrap", "linear");
    model.component("comp1").func("int1").setIndex("fununit", "V", 0);
    model.component("comp1").func("int1").set("defineinv", true);
    model.component("comp1").func("int1").set("funcinvname", "Eeq_Si_upper_inv");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u63d2\u503c - Eeq Si \u4e0b\u9650");
    model.component("comp1").func("int2").set("funcname", "Eeq_Si_lower");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"0.0004465048110959477", "0.9947275950547677"}, 
         {"0.005634314410235611", "0.7428206309015168"}, 
         {"0.018166057492054646", "0.627991965557005"}, 
         {"0.03343604618271433", "0.5471394348079184"}, 
         {"0.04602700600679829", "0.4979229198929659"}, 
         {"0.06132977539406893", "0.45339140098881403"}, 
         {"0.07843442565311574", "0.40534388801268095"}, 
         {"0.10816070154920517", "0.34205758087976"}, 
         {"0.13520689113689272", "0.3092356440375881"}, 
         {"0.185718772288891", "0.2763999604516758"}, 
         {"0.25067636622805745", "0.2494140450480795"}, 
         {"0.2921820153466533", "0.2376732684522278"}, 
         {"0.34000323577198815", "0.22358549972321473"}, 
         {"0.37880130509470183", "0.2118463092901025"}, 
         {"0.40496823180393765", "0.20480110312331334"}, 
         {"0.4455671245566015", "0.18837427307483479"}, 
         {"0.48526243326881174", "0.1707763262038845"}, 
         {"0.5258581536959971", "0.15083455952525116"}, 
         {"0.5655524049663813", "0.13206496711091587"}, 
         {"0.6061523551608713", "0.11680978260582242"}, 
         {"0.6512670532321504", "0.1038952455829335"}, 
         {"0.6981868045006839", "0.09097965111821849"}, 
         {"0.7442061440542426", "0.08040787646118652"}, 
         {"0.7911269527646024", "0.0686639275398565"}, 
         {"0.8344376550804529", "0.056922093502178917"}, 
         {"0.8524839572856949", "0.05222493691037733"}});
    model.component("comp1").func("int2").set("extrap", "linear");
    model.component("comp1").func("int2").setIndex("fununit", "V", 0);
    model.component("comp1").func("int2").set("defineinv", true);
    model.component("comp1").func("int2").set("funcinvname", "Eeq_Si_lower_inv");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_el", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat3").selection().geom("geom1", 0);
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 - \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").cpl("intop1").set("opname", "intop_ref");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 - \u96c6\u6d41\u4f53");
    model.component("comp1").cpl("intop2").set("opname", "intop_cc");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - \u7535\u6781");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(1);

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("x_Si", "liion.cs_surface_addm1/cs_Si_max", "\u7845\u9897\u7c92\u8868\u9762\u7684\u63d2\u5c42\u6c34\u5e73");
    model.component("comp1").variable("var1")
         .set("U_avg", "(Eeq_Si_upper(x_Si)+Eeq_Si_lower(x_Si))/2", "\u5e73\u5747\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("U_offset", "(Eeq_Si_upper(x_Si)-Eeq_Si_lower(x_Si))/2", "\u4e0a\u4e0b\u5e73\u8861\u7535\u4f4d\u4e4b\u95f4\u7684\u504f\u79fb");
    model.component("comp1").variable("var1").set("Eeq_Si", "U_avg+U_offset*S", "\u7845\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("dx_Sidt", "liion.iv_addm1_per1/(F_const*epss_Si*cs_Si_max)", "\u7845\u63d2\u5c42\u7387");
    model.component("comp1").variable("var1")
         .set("dSdt", "-K_S*abs(dx_Sidt)*(S-sign(dx_Sidt))", "\u5185\u5b58\u53d8\u91cf\u7684\u65f6\u95f4\u5bfc\u6570");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf-\u5168\u5c40");
    model.component("comp1").variable("var2").set("i_app", "i_1C*C_rate*CurrentDirection");
    model.component("comp1").variable("var2").descr("i_app", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("E_vs_ref", "intop_cc(phis)-intop_ref(phil)");
    model.component("comp1").variable("var2")
         .descr("E_vs_ref", "\u7535\u6781\u7535\u4f4d vs. \u53c2\u6bd4\u7535\u4f4d");
    model.component("comp1").variable("var2").set("SOC", "Cap/Q_el");
    model.component("comp1").variable("var2").descr("SOC", "\u7535\u6781 SOC");

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").label("\u591a\u5b54\u7535\u6781 1 - \u77f3\u58a8");
    model.component("comp1").physics("liion").feature("pce1").selection().set(1);
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat2");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_Gr");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_el");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs_Gr_init");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_Gr");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmin_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmax_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat1");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_Gr");
    model.component("comp1").physics("liion").create("addm1", "IntercalatingMaterial", 1);
    model.component("comp1").physics("liion").feature("addm1")
         .label("\u9644\u52a0\u591a\u5b54\u7535\u6781\u6750\u6599 1 - \u7845");
    model.component("comp1").physics("liion").feature("addm1").selection().set(1);
    model.component("comp1").physics("liion").feature("addm1").set("epssadd", "epss_Si");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("csinit", "cs_Si_init");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("cEeqref_mat", "userdef");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("cEeqref", "cs_Si_max");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1")
         .set("ParticleConcentrationType", "NoSpatialGradients");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("rp", "rp_Si");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("socmin_mat", "userdef");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("socmax_mat", "userdef");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").set("Eeq", "Eeq_Si");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").set("i0_ref", "i0_ref_Si");
    model.component("comp1").physics("liion").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("liion").feature("es1")
         .label("\u7535\u6781\u8868\u9762 1 - \u9502\u91d1\u5c5e");
    model.component("comp1").physics("liion").feature("es1").selection().set(3);
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(1);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "i_app");
    model.component("comp1").physics("liion").feature("init1").set("phis", "E_init_el");
    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/c", true);

    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("c")
         .label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b - \u8bb0\u5fc6\u53d8\u91cf");
    model.component("comp1").physics("c").selection().set(1);
    model.component("comp1").physics("c").field("dimensionless").field("S");
    model.component("comp1").physics("c").field("dimensionless").component(1, "S");
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", "dSdt", 0);
    model.component("comp1").physics("c").feature("init1").set("S", "S_init");
    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").label("\u4e8b\u4ef6 - \u5145\u653e\u7535\u63a7\u5236");
    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "CurrentDirection", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", -1, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimDescr", "\u7535\u6d41\u65b9\u5411", 0, 0);
    model.component("comp1").physics("ev").create("is1", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "switch", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", "-(E_vs_ref-E_switch)", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "end", 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", "E_vs_ref-E_end", 1, 0);
    model.component("comp1").physics("ev").create("impl1", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl1").set("condition", "switch>0");
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitName", "CurrentDirection", 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", 1, 0, 0);
    model.component("comp1").physics("ev").create("impl2", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl2").set("condition", "end>0");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge")
         .label("\u5168\u5c40\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b - \u7535\u8377\u79ef\u5206");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "Cap", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "d(Cap,t)+i_app", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u7d2f\u79ef\u7535\u8377", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "C/m^2", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "A/m^2", 0, 0);
    model.component("comp1").physics().create("ge2", "GlobalEquations", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ge2", true);

    model.component("comp1").physics("ge2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge2")
         .label("\u5168\u5c40\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b - \u80fd\u91cf\u79ef\u5206");
    model.component("comp1").physics("ge2").feature("ge1").setIndex("name", "E_lith", 0, 0);
    model.component("comp1").physics("ge2").feature("ge1")
         .setIndex("equation", "d(E_lith,t)-if(CurrentDirection==-1, i_app*(E_vs_ref-E_max_el),0)", 0, 0);
    model.component("comp1").physics("ge2").feature("ge1")
         .setIndex("description", "\u7d2f\u79ef\u9502\u5316\u80fd\u91cf", 0, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("name", "E_delith", 1, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge2").feature("ge1")
         .setIndex("equation", "d(E_delith,t)-if(CurrentDirection==1, -i_app*(E_vs_ref-E_max_el),0)", 1, 0);
    model.component("comp1").physics("ge2").feature("ge1")
         .setIndex("description", "\u7d2f\u79ef\u8131\u9502\u80fd\u91cf", 1, 0);
    model.component("comp1").physics("ge2").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge2").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge2").feature("ge1").setIndex("CustomDependentVariableUnit", "J/m^2", 0, 0);
    model.component("comp1").physics("ge2").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge2").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge2").feature("ge1").setIndex("CustomSourceTermUnit", "W/m^2", 0, 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L_sep", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L_sep", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "C_rate", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 1", 0);
    model.study("std1").feature("param").setIndex("pname", "L_sep", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "L_sep", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "Si_f", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 1 4", 1);
    model.study("std1").feature("param").setIndex("punit", "%", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.01/C_rate,2.1/C_rate)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1")
         .set("eventstopName", new String[]{"comp1.ev.impl1.event", "comp1.ev.impl2.event"});
    model.sol("sol1").feature("t1").feature("st1").set("eventstopActive", new String[]{"off", "off"});
    model.sol("sol1").feature("t1").feature("st1").setIndex("eventstopActive", true, 1);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").selection().all();
    model.result("pg1").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"liion.soc_average_pce1"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1 - \u77f3\u58a8"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg5").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", "S");
    model.result("pg6").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b - \u8bb0\u5fc6\u53d8\u91cf");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"Cap"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7d2f\u79ef\u7535\u8377"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("expr", new String[]{"Cap"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u7d2f\u79ef\u7535\u8377"});
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("expr", new String[]{"E_lith", "E_delith"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u7d2f\u79ef\u9502\u5316\u80fd\u91cf", "\u7d2f\u79ef\u8131\u9502\u80fd\u91cf"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"E_lith", "E_delith"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u7d2f\u79ef\u9502\u5316\u80fd\u91cf", "\u7d2f\u79ef\u8131\u9502\u80fd\u91cf"});
    model.result("pg1").run();
    model.result("pg1").label("0.1 C \u7535\u6781\u7535\u538b vs. SOC");
    model.result("pg1").setIndex("looplevelinput", "manual", 2);
    model.result("pg1").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u4f4d vs. \u53c2\u6bd4\u7535\u6781\u7535\u4f4d (V)");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").set("expr", "E_vs_ref");
    model.result("pg1").feature("ptgr1").set("descr", "\u7535\u6781\u7535\u4f4d vs. \u53c2\u6bd4\u7535\u4f4d");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "inner");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "SOC");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u7535\u6781 SOC");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("autopoint", false);
    model.result("pg1").feature("ptgr1").set("autosolution", false);
    model.result("pg1").feature("ptgr1").set("legendprefix", "eval(Si_f*100) % Si in Gr");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg9", "pg1");
    model.result("pg9").run();
    model.result("pg9").label("1 C \u7535\u6781\u7535\u538b vs. SOC");
    model.result("pg9").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg9").run();
    model.result("pg2").run();
    model.result("pg2").label("0.1 C \u5e73\u5747\u6750\u6599\u9502\u5316\u6c34\u5e73");
    model.result("pg2").setIndex("looplevelinput", "manual", 2);
    model.result("pg2").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u9502\u5316\u5ea6 (1)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"liion.soc_average_pce1", "liion.soc_average_addm1"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1 - \u77f3\u58a8", "\u5e73\u5747 SOC\uff0c\u9644\u52a0\u591a\u5b54\u7535\u6781\u6750\u6599 1 - \u7845"});
    model.result("pg2").feature("glob1").set("legendmethod", "manual");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg2").feature("glob1").setIndex("legends", "Gr", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "Si", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg10", "pg2");
    model.result("pg10").run();
    model.result("pg10").label("1 C \u5e73\u5747\u6750\u6599\u9502\u5316\u6c34\u5e73");
    model.result("pg10").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u6ede\u540e\u8bb0\u5fc6\u53d8\u91cf");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevelinput", "manual", 2);
    model.result("pg11").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg11").setIndex("looplevelinput", "manual", 1);
    model.result("pg11").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("legendpos", "uppermiddle");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(1, 2);
    model.result("pg11").feature("ptgr1").set("expr", "S");
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u96c6\u6d41\u4f53 - \u7535\u6781", 0);
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u9694\u819c - \u7535\u6781", 1);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u8131\u9502\u80fd\u91cf\u5bc6\u5ea6");
    model.result("pg12").set("data", "none");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u6df7\u5408\u7535\u6781\u4e2d\u7684\u7845\u4f53\u79ef\u5206\u6570 (%)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "E<sub>delith</sub> (J/m<sup>3</sup>)");
    model.result("pg12").set("legendpos", "upperleft");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").set("data", "dset2");
    model.result("pg12").feature("glob1").setIndex("looplevelinput", "manual", 2);
    model.result("pg12").feature("glob1").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg12").feature("glob1").setIndex("looplevelinput", "last", 0);
    model.result("pg12").feature("glob1").set("expr", new String[]{});
    model.result("pg12").feature("glob1").set("descr", new String[]{});
    model.result("pg12").feature("glob1").setIndex("expr", "E_delith/L_el", 0);
    model.result("pg12").feature("glob1").set("legendmethod", "manual");
    model.result("pg12").feature("glob1").setIndex("legends", "0.1C", 0);
    model.result("pg12").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").feature("glob1").set("xdataexpr", "Si_f");
    model.result("pg12").feature("glob1")
         .set("xdatadescr", "\u7535\u6781\u6df7\u5408\u7269\u4e2d Si \u7684\u5206\u6570");
    model.result("pg12").feature("glob1").set("xdataunit", "%");
    model.result("pg12").feature().duplicate("glob2", "glob1");
    model.result("pg12").run();
    model.result("pg12").feature("glob2").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg12").feature("glob2").setIndex("legends", "1C", 0);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u9502\u5316/\u8131\u9502\u80fd\u91cf\u6548\u7387");
    model.result("pg13").set("ylabel", "E<sub>delith</sub>/E<sub>lith</sub> (1)");
    model.result("pg13").set("legendpos", "upperright");
    model.result("pg13").run();
    model.result("pg13").feature("glob1").setIndex("expr", "E_delith/E_lith", 0);
    model.result("pg13").run();
    model.result("pg13").feature("glob2").setIndex("expr", "E_delith/E_lith", 0);
    model.result("pg13").run();

    model.component("comp1").func("int1").createPlot("pg14");

    model.result("pg14").run();
    model.result("pg14").label("\u5e73\u8861\u7535\u4f4d vs. \u9502\u5316");
    model.result("pg14").set("data", "none");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("xlabel", "\u9502\u5316\u5ea6 (1)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u7535\u4f4d vs. Li/Li<sup>+</sup> (V)");
    model.result("pg14").set("axislimits", true);
    model.result("pg14").set("xmin", 0);
    model.result("pg14").set("xmax", 0.9);
    model.result("pg14").set("ymin", 0);
    model.result("pg14").set("ymax", 1);
    model.result("pg14").run();
    model.result("pg14").feature("plot1").set("data", "int1_ds1");
    model.result("pg14").feature("plot1").set("descractive", false);
    model.result("pg14").feature("plot1").set("display", "line");
    model.result("pg14").feature("plot1").set("extrapolation", "none");
    model.result("pg14").feature("plot1").set("legend", true);
    model.result("pg14").feature("plot1").set("legendmethod", "manual");
    model.result("pg14").feature("plot1").setIndex("legends", "E<sub>eq, Si, upper</sup>", 0);

    model.component("comp1").func("int2").createPlot("pg15");

    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg14").feature().copy("plot2", "pg15/plot1");
    model.result("pg15").feature().remove("plot1");
    model.result("pg14").feature("plot2").set("outerinput", "all");
    model.result("pg14").feature("plot2").set("innerinput", "all");
    model.result("pg14").feature("plot2").set("titletype", "custom");
    model.result("pg14").feature("plot2").set("data", "int2_ds1");
    model.result("pg14").feature("plot2").set("descractive", false);
    model.result("pg14").feature("plot2").set("display", "line");
    model.result("pg14").feature("plot2").set("extrapolation", "none");
    model.result("pg14").feature("plot2").set("legend", true);
    model.result("pg14").feature("plot2").set("legendmethod", "manual");
    model.result("pg14").feature("plot2").setIndex("legends", "E<sub>eq, Si, lower</sup>", 0);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").propertyGroup("def").func("int3").createPlot("pg16");

    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg14").feature().copy("plot3", "pg16/plot1");
    model.result("pg16").feature().remove("plot1");
    model.result("pg14").feature("plot3").set("outerinput", "all");
    model.result("pg14").feature("plot3").set("innerinput", "all");
    model.result("pg14").feature("plot3").set("titletype", "custom");
    model.result("pg14").feature("plot3").set("data", "int3_ds1");
    model.result("pg14").feature("plot3").set("descractive", false);
    model.result("pg14").feature("plot3").set("legend", true);
    model.result("pg14").feature("plot3").set("legendmethod", "manual");
    model.result("pg14").feature("plot3").setIndex("legends", "E<sub>eq, Gr</sup>", 0);
    model.result("pg14").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result().remove("pg15");
    model.result().remove("pg16");
    model.result("pg9").run();
    model.result().move("pg9", 1);

    model
         .title("\u7845-\u77f3\u58a8\u6df7\u5408\u7535\u6781\u7684\u70ed\u529b\u5b66\u7535\u538b\u6ede\u540e\u73b0\u8c61");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u7845 (Si) \u4f5c\u4e3a\u201c\u9644\u52a0\u7535\u6781\u6750\u6599\u201d\u6dfb\u52a0\u5230\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u63a5\u53e3\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u9644\u52a0\u7684\u201c\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b\u201d\u63a5\u53e3\u6765\u5b9a\u4e49\u7528\u4e8e\u5904\u7406\u7535\u538b\u6ede\u540e\u7684\u5185\u5b58\u53d8\u91cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("li_battery_sigr_hysteresis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
