/*
 * particle_size_distribution.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class particle_size_distribution {

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
    model.param().set("L_pos", "75[um]", "NMC \u7535\u6781\u539a\u5ea6");
    model.param().set("sigmas_pos", "10[S/m]", "\u7535\u6781\u7535\u5bfc\u7387");
    model.param().set("epsl_sep", "0.4", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u9694\u819c");
    model.param().set("epsl_pos", "0.4", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("epss_pos", "0.5", "\u7535\u6781\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("cs_init", "10000[mol/m^3]", "\u5d4c\u5165\u9502\u7684\u521d\u59cb\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.param().set("Rp_min", "2[um]", "\u76f4\u65b9\u56fe\u4e2d\u7684\u6700\u5c0f\u9897\u7c92\u534a\u5f84");
    model.param().set("Rp_max", "14[um]", "\u76f4\u65b9\u56fe\u4e2d\u7684\u6700\u5927\u9897\u7c92\u534a\u5f84");
    model.param()
         .set("i0_ref_v", "1e5[A/m^3]", "\u9502\u5316 50% \u65f6\u7684\u4f53\u79ef\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Ds", "1e-14[m^2/s]", "NMC \u4e2d\u7684\u9502\u79bb\u5b50\u6269\u6563\u7cfb\u6570");
    model.param().set("T", "25[degC]", "\u6e29\u5ea6");
    model.param().set("csmax", "49000[mol/m^3]", "NMC \u4e2d\u7684\u6700\u5927\u9502\u6d53\u5ea6");
    model.param().set("t_pulse", "30[min]", "\u8109\u51b2\u65f6\u95f4");
    model.param().set("t", "0[s]", "\u865a\u62df\u65f6\u95f4\u53c2\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u63d2\u503c - \u9897\u7c92\u534a\u5f84\u76f4\u65b9\u56fe");
    model.func("int1").set("funcname", "f_hist");
    model.func("int1")
         .set("table", new String[][]{{"2.5", "5"}, 
         {"3.5", "28"}, 
         {"4.5", "34"}, 
         {"5.5", "19"}, 
         {"6.5", "14"}, 
         {"7.5", "11"}, 
         {"8.5", "3"}, 
         {"9.5", "1"}, 
         {"10.5", "2"}, 
         {"11.5", "1"}, 
         {"12.5", "0"}, 
         {"13.5", "1"}});
    model.func("int1").set("interp", "neighbor");
    model.func("int1").setIndex("fununit", 1, 0);
    model.func("int1").setIndex("argunit", "um", 0);
    model.func("int1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "R<sub>p</sub> (um)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "f<sub>hist</sub> (1)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 2);
    model.result("pg1").set("xmax", 14);
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("linewidth", 2);
    model.result("pg1").run();
    model.result().dataset().remove("int1_ds1");

    model.common().create("state1", "StateVariables", "");
    model.common("state1")
         .label("\u72b6\u6001\u53d8\u91cf - \u57fa\u4e8e\u76f4\u65b9\u56fe\u7684\u9897\u7c92\u5ea6\u91cf");
    model.common("state1").setIndex("state", "vol_particles", 0);
    model.common("state1").setIndex("initialValue", "", 0);
    model.common("state1").setIndex("updateExpression", "", 0);
    model.common("state1").setIndex("description", "", 0);
    model.common("state1")
         .setIndex("initialValue", "integrate(f_hist(Rp_arg)*4*pi*Rp_arg^3/3,Rp_arg,Rp_min,Rp_max)", 0);
    model.common("state1").setIndex("description", "\u6240\u6709\u9897\u7c92\u7684\u4f53\u79ef", 0);
    model.common("state1").setIndex("state", "area_particles", 1);
    model.common("state1").setIndex("initialValue", "", 1);
    model.common("state1").setIndex("updateExpression", "", 1);
    model.common("state1").setIndex("description", "", 1);
    model.common("state1")
         .setIndex("initialValue", "integrate(f_hist(Rp_arg)*4*pi*Rp_arg^2,Rp_arg,Rp_min,Rp_max)", 1);
    model.common("state1").setIndex("description", "\u6240\u6709\u9897\u7c92\u7684\u9762\u79ef", 1);
    model.common("state1").setIndex("state", "mass_averaged_Rp_squared", 2);
    model.common("state1").setIndex("initialValue", "", 2);
    model.common("state1").setIndex("updateExpression", "", 2);
    model.common("state1").setIndex("description", "", 2);
    model.common("state1")
         .setIndex("initialValue", "integrate(f_hist(Rp_arg)*Rp_arg^2*4*pi*Rp_arg^3/3,Rp_arg,Rp_min,Rp_max)/integrate(f_hist(Rp_arg)*4*pi*Rp_arg^3/3,Rp_arg,Rp_min,Rp_max)", 2);
    model.common("state1")
         .setIndex("description", "\u8d28\u91cf\u52a0\u6743\u5e73\u5747\u7684\u9897\u7c92\u534a\u5f84\u5e73\u65b9", 2);
    model.common("state1").set("update", "onlyInitialization");

    model.func().create("step1", "Step");
    model.func("step1").set("from", 1);
    model.func("step1").set("to", 0);

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1")
         .set("Rp_no_distr", "sqrt(mass_averaged_Rp_squared)[m]", "\u975e\u5206\u5e03\u5f0f\u6a21\u578b\u4e2d\u90e8\u7f72\u7684\u9897\u7c92\u534a\u5f84");
    model.variable("var1")
         .set("Av_no_distr", "epss_pos*(4*pi*Rp_no_distr^2)/(4*pi*Rp_no_distr^3/3)", "\u975e\u5206\u5e03\u5f0f\u6a21\u578b\u4e2d\u7684\u6bd4\u8868\u9762\u79ef");
    model.variable("var1")
         .set("Av_distr", "epss_pos*area_particles[m^2]/vol_particles[m^3]", "\u5206\u5e03\u5f0f\u6a21\u578b\u4e2d\u7684\u6bd4\u8868\u9762\u79ef");
    model.variable("var1")
         .set("i0_ref_no_distr", "i0_ref_v/Av_no_distr", "\u975e\u5206\u5e03\u5f0f\u6a21\u578b\u4e2d\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.variable("var1")
         .set("i0_ref_distr", "i0_ref_v/Av_distr", "\u5206\u5e03\u5f0f\u6a21\u578b\u4e2d\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.variable("var1")
         .set("i_app", "-10[A/m^2]*step1((t-t_pulse)/1[s])", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Lithium Metal, Li (Negative, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "0.34");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "0.534[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "3.58[kJ/kg/K]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
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
    model.component("comp1").material("mat2").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp1").material("mat2").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("NMC 111, LiNi0.33Mn0.33Co0.33O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "0.25");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "78[GPa]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "Jing Ying Ko et al, J. Electrochem. Soc., 166, A2939");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "49000[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
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
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1")
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
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat2").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat2").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
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
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
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
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("importedname", "NMC_333.txt");
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("importeddim", "2D");
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1")
         .set("funcnametable", new String[][]{{"log_sigmas", "1"}});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "log_sigmas"});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("fununit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1")
         .set("argunit", new String[]{"1", "1/K"});
    model.component("comp1").material("mat2").propertyGroup("pg1").func("int1").set("sourcetype", "model");
    model.component("comp1").material("mat2").propertyGroup("pg1")
         .set("electricconductivity", new String[]{"10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("pg1")
         .setPropertyInfo("electricconductivity", "Ruhul Amin and Yet-Ming Chiang 2016 J. Electrochem. Soc. 163 A1512");
    model.component("comp1").material("mat2").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp1").material("mat2").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp1").material("mat2").propertyGroup("pg1").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("pg1").addInput("concentration");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat3").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp1").material("mat1").selection().geom("geom1", 0);
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat3").selection().set(1);

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("liion").feature("es1").label("\u7535\u6781\u8868\u9762 - \u9502\u91d1\u5c5e");
    model.component("comp1").physics("liion").feature("es1").selection().set(1);
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1")
         .label("\u591a\u5b54\u7535\u6781 - \u65e0\u7c92\u5f84\u5206\u5e03");
    model.component("comp1").physics("liion").feature("pce1").selection().set(2);
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs_init");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "Rp_no_distr");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_no_distr");
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Av", "Av_no_distr");
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(3);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "i_app");
    model.component("comp1").physics("liion").feature("init1").set("phis", "4[V]");

    model.study("std1").label("\u7814\u7a76 1 - \u65e0\u7c92\u5f84\u5206\u5e03");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,3)");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").set("probename", "E_cell_no_distr");
    model.component("comp1").probe("point1").selection().set(3);
    model.component("comp1").probe("point1").set("expr", "phis");
    model.component("comp1").probe("point1").set("descr", "\u7535\u52bf");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "\u7535\u6c60\u7535\u538b");

    model.result().table().create("tbl1", "Table");

    model.component("comp1").probe("point1").set("table", "tbl1");
    model.component("comp1").probe("point1").set("window", "window2");
    model.component("comp1").probe("point1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe().create("point2", "Point");
    model.component("comp1").probe("point2").set("probename", "cs_surface_no_distr");
    model.component("comp1").probe("point2").selection().set(2);
    model.component("comp1").probe("point2").set("expr", "liion.cs_surface");
    model.component("comp1").probe("point2").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u8868\u9762");
    model.component("comp1").probe("point2").set("descractive", true);
    model.component("comp1").probe("point2").set("descr", "\u6d53\u5ea6\uff0c\u8868\u9762");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("point2").set("table", "tbl2");
    model.component("comp1").probe("point2").set("window", "window2");
    model.component("comp1").probe().duplicate("point3", "point2");
    model.component("comp1").probe("point3").set("probename", "cs_center_no_distr");
    model.component("comp1").probe("point3").set("expr", "liion.cs_center");
    model.component("comp1").probe("point3").set("descr", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").label("\u63a2\u9488\u56fe - \u7814\u7a76 1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 3);
    model.result("pg1").set("ymin", 3.94);
    model.result("pg1").set("ymax", 4.07);
    model.result("pg1").set("yminsec", 10000);
    model.result("pg1").set("ymaxsec", 18000);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u7535\u6c60\u7535\u538b", 0);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("linestyle", "dashed");
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u6d53\u5ea6\uff0c\u8868\u9762", 0);
    model.result("pg1").feature("tblp2").setIndex("legends", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3", 1);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();

    model.component().create("xdim1", "ExtraDim");

    model.component("xdim1").geom().create("geom2", 2);

    model.mesh().create("mesh2", "geom2");

    model.extraDim().create("pa1", "PointsToAttach");
    model.extraDim("pa1").model("xdim1");

    model.component("xdim1").spatialCoord(new String[]{"xs", "y1", "z1"});
    model.component("xdim1").spatialCoord(new String[]{"xs", "ys", "z1"});

    model.geom("geom2").create("sq1", "Square");
    model.geom("geom2").run("fin");

    model.mesh("mesh2").create("map1", "Map");
    model.mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 4);
    model.mesh("mesh2").feature("map1").feature("dis1").set("numelem", 12);
    model.mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.mesh("mesh2").feature("map1").feature("dis2").selection().set(2, 3);
    model.mesh("mesh2").feature("map1").feature("dis2").set("type", "predefined");
    model.mesh("mesh2").feature("map1").feature("dis2").set("elemcount", 10);
    model.mesh("mesh2").feature("map1").feature("dis2").set("elemratio", 10);
    model.mesh("mesh2").run();

    model.extraDim().create("xdintop2", "Integration");
    model.extraDim("xdintop2").model("xdim1");
    model.extraDim("xdintop2").label("\u5bf9\u989d\u5916\u7ef4\u5ea6\u79ef\u5206 - xdint_surf");
    model.extraDim("xdintop2").set("opname", "xdint_surf");
    model.extraDim("xdintop2").selection().geom("geom2", 1);
    model.extraDim("xdintop2").selection().set(4);
    model.extraDim().create("xdintop3", "Integration");
    model.extraDim("xdintop3").model("xdim1");
    model.extraDim("xdintop3").label("\u5bf9\u989d\u5916\u7ef4\u5ea6\u79ef\u5206- xdint_surf_Rmax");
    model.extraDim("xdintop3").set("opname", "xdint_surf_Rmax");
    model.extraDim("xdintop3").selection().geom("geom2", 0);
    model.extraDim("xdintop3").selection().set(4);
    model.extraDim().create("xdintop4", "Integration");
    model.extraDim("xdintop4").model("xdim1");
    model.extraDim("xdintop4").label("\u5bf9\u989d\u5916\u7ef4\u5ea6\u79ef\u5206 - xdint_surf_Rmin");
    model.extraDim("xdintop4").set("opname", "xdint_surf_Rmin");
    model.extraDim("xdintop4").selection().geom("geom2", 0);
    model.extraDim("xdintop4").selection().set(3);
    model.extraDim().create("xdintop5", "Integration");
    model.extraDim("xdintop5").model("xdim1");
    model.extraDim("xdintop5").label("\u5bf9\u989d\u5916\u7ef4\u5ea6\u79ef\u5206 - xdint_center_Rmax");
    model.extraDim("xdintop5").set("opname", "xdint_center_Rmax");
    model.extraDim("xdintop5").selection().geom("geom2", 0);
    model.extraDim("xdintop5").selection().set(2);
    model.extraDim().create("xdintop6", "Integration");
    model.extraDim("xdintop6").model("xdim1");
    model.extraDim("xdintop6").label("\u5bf9\u989d\u5916\u7ef4\u5ea6\u79ef\u5206 - xdint_center_Rmin");
    model.extraDim("xdintop6").set("opname", "xdint_center_Rmin");
    model.extraDim("xdintop6").selection().geom("geom2", 0);
    model.extraDim("xdintop6").selection().set(1);
    model.extraDim().create("ad1", "AttachDimensions");
    model.component("comp1").extraDim("ad1").model("comp1");
    model.component("comp1").extraDim("ad1").selection().geom("geom1", 1);
    model.component("comp1").extraDim("ad1").selection().geom("geom1", 1);
    model.component("comp1").extraDim("ad1").selection().set(2);
    model.component("comp1").extraDim("ad1").set("extradim", new String[]{"xdim1"});

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2")
         .label("\u53d8\u91cf - \u989d\u5916\u7ef4\u5ea6\u4e2d\u7684\u9897\u7c92\u57df");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(2);
    model.component("comp1").variable("var2").selection().extraDim("ad1");
    model.component("comp1").variable("var2").set("Rp", "Rp_min+(Rp_max-Rp_min)*ys[1/m]");
    model.component("comp1").variable("var2").descr("Rp", "\u9897\u7c92\u534a\u5f84");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3")
         .label("\u53d8\u91cf - \u989d\u5916\u7ef4\u5ea6\u4e2d\u7684\u9897\u7c92\u8868\u9762");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().set(2);
    model.component("comp1").variable("var3").selection().extraDim("ad1");
    model.component("comp1").variable("var3").selection().extraDimSel("geom2").geom("geom2", 1);
    model.component("comp1").variable("var3").selection().extraDimSel("geom2").set(4);

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("cs_surface", "cs[mol/m^3]", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u8868\u9762");
    model.component("comp1").variable("var3")
         .set("i0", "i0_ref_distr*(2*(csmax-cs_surface)/csmax)^0.5*(2*cs_surface/csmax)^0.5*(cl/1[M])^0.5", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var3")
         .set("Eeq", "mat2.def.Eeq(cs_surface/csmax)", "\u5e73\u8861\u7535\u4f4d");
    model.component("comp1").variable("var3").set("eta", "phis-phil-Eeq", "\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var3")
         .set("iloc", "i0*(exp(eta*0.5*F_const/(R_const*T))-exp(-eta*0.5*F_const/(R_const*T)))", "\u9897\u7c92\u8868\u9762\u7684\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").selection().geom("geom1", 1);
    model.component("comp1").variable("var4").selection().set(2);
    model.component("comp1").variable("var4").label("\u53d8\u91cf - \u591a\u5b54\u7535\u6781\u57df");
    model.component("comp1").variable("var4")
         .set("iloc_distr", "root.xdim1.xdint_surf(iloc*f_hist(Rp)*4*pi*Rp^2)*(Rp_max-Rp_min)[m^-2]/(area_particles[m^2])");
    model.component("comp1").variable("var4")
         .descr("iloc_distr", "\u9897\u7c92\u5e73\u5747\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").physics("liion").create("weak1", "WeakContribution", 1);
    model.component("comp1").physics("liion").feature("weak1")
         .label("\u5f31\u8d21\u732e - \u989d\u5916\u7ef4\u5ea6\u4e2d\u7684\u57df\u65b9\u7a0b");
    model.component("comp1").physics("liion").feature("weak1").selection().set(2);
    model.component("comp1").physics("liion").feature("weak1").selection().extraDim("ad1");
    model.component("comp1").physics("liion").feature("weak1").selection().extraDimSel("geom2").all();
    model.component("comp1").physics("liion").feature("weak1")
         .set("weakExpression", "xs^2*(-Rp^2*test(cs)*d(cs,TIME)-d(cs,xs)*Ds*test(d(cs,xs)[m^2]))");
    model.component("comp1").physics("liion").feature("weak1").create("aux1", "AuxiliaryField", 1);
    model.component("comp1").physics("liion").feature("weak1").feature("aux1")
         .label("\u8f85\u52a9\u56e0\u53d8\u91cf - cs");
    model.component("comp1").physics("liion").feature("weak1").feature("aux1").selection().extraDim("ad1");
    model.component("comp1").physics("liion").feature("weak1").feature("aux1").selection().extraDimSel("geom2")
         .all();
    model.component("comp1").physics("liion").feature("weak1").feature("aux1").set("fieldVariableName", "cs");
    model.component("comp1").physics("liion").feature("weak1").feature("aux1").set("initialValue", "cs_init");
    model.component("comp1").physics("liion").create("weak2", "WeakContribution", 1);
    model.component("comp1").physics("liion").feature("weak2")
         .label("\u5f31\u8d21\u732e - \u989d\u5916\u7ef4\u5ea6\u4e2d\u7684\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("liion").feature("weak2").selection().set(2);
    model.component("comp1").physics("liion").feature("weak2").selection().extraDim("ad1");
    model.component("comp1").physics("liion").feature("weak2").selection().extraDimSel("geom2").geom("geom2", 1);
    model.component("comp1").physics("liion").feature("weak2").selection().extraDimSel("geom2").set(4);
    model.component("comp1").physics("liion").feature("weak2")
         .set("weakExpression", "xs^2*(-iloc/F_const)*test(cs)*Rp");
    model.component("comp1").physics("liion").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("liion").feature("pce2")
         .label("\u591a\u5b54\u7535\u6781 - \u6709\u7c92\u5f84\u5206\u5e03");
    model.component("comp1").physics("liion").feature("pce2")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("ilocmat", "iloc_distr");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("Av", "Av_distr");

    model.component("comp1").probe().create("point4", "Point");
    model.component("comp1").probe("point4").set("probename", "E_cell_distr");
    model.component("comp1").probe("point4").selection().set(3);
    model.component("comp1").probe("point4").set("expr", "phis");
    model.component("comp1").probe("point4").set("descr", "\u7535\u52bf");
    model.component("comp1").probe("point4").set("descractive", true);
    model.component("comp1").probe("point4").set("descr", "\u7535\u6c60\u7535\u538b");

    model.result().table().create("tbl3", "Table");

    model.component("comp1").probe("point4").set("table", "tbl3");
    model.component("comp1").probe("point4").set("window", "window3");
    model.component("comp1").probe("point4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.component("comp1").probe().create("point5", "Point");
    model.component("comp1").probe("point5").set("probename", "cs_surface_Rmax");
    model.component("comp1").probe("point5").selection().set(2);
    model.component("comp1").probe("point5").set("expr", "root.xdim1.xdint_surf_Rmax(cs)");
    model.component("comp1").probe("point5").set("descractive", true);
    model.component("comp1").probe("point5")
         .set("descr", "\u6d53\u5ea6\uff0c\u8868\u9762\uff0c\u6700\u5927\u9897\u7c92");

    model.result().table().create("tbl4", "Table");

    model.component("comp1").probe("point5").set("table", "tbl4");
    model.component("comp1").probe("point5").set("window", "window3");
    model.component("comp1").probe().duplicate("point6", "point5");
    model.component("comp1").probe("point6").set("probename", "cs_center_Rmax");
    model.component("comp1").probe("point6").set("expr", "root.xdim1.xdint_center_Rmax(cs)");
    model.component("comp1").probe("point6")
         .set("descr", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3\uff0c\u6700\u5927\u9897\u7c92");
    model.component("comp1").probe().duplicate("point7", "point6");
    model.component("comp1").probe("point7").set("probename", "cs_surface_Rmin");
    model.component("comp1").probe("point7").set("expr", "root.xdim1.xdint_surf_Rmin(cs)");
    model.component("comp1").probe("point7")
         .set("descr", "\u6d53\u5ea6\uff0c\u8868\u9762\uff0c\u6700\u5c0f\u9897\u7c92");
    model.component("comp1").probe().duplicate("point8", "point7");
    model.component("comp1").probe("point8").set("probename", "cs_center_Rmin");
    model.component("comp1").probe("point8").set("expr", "root.xdim1.xdint_center_Rmin(cs)");
    model.component("comp1").probe("point8")
         .set("descr", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3\uff0c\u6700\u5c0f\u9897\u7c92");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").label("\u7814\u7a76 2 - \u6709\u7c92\u5f84\u5206\u5e03");
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "range(0,0.5,3)");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time")
         .set("probes", new String[]{"point4", "point5", "point6", "point7", "point8"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"liion/pce1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("point4").genResult("none");
    model.component("comp1").probe("point5").genResult("none");
    model.component("comp1").probe("point6").genResult("none");
    model.component("comp1").probe("point7").genResult("none");
    model.component("comp1").probe("point8").genResult("none");

    model.sol("sol2").runAll();

    model.result("pg2").set("window", "window3");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u63a2\u9488\u56fe - \u7814\u7a76 2");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg2").set("yseclabelactive", true);
    model.result("pg2").set("yseclabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 3);
    model.result("pg2").set("ymin", 3.94);
    model.result("pg2").set("ymax", 4.07);
    model.result("pg2").set("yminsec", 10000);
    model.result("pg2").set("ymaxsec", 18000);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("window", "window3");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u7535\u6c60\u7535\u538b", 0);
    model.result("pg2").set("window", "window3");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("linestyle", "dashed");
    model.result("pg2").feature("tblp2").set("legendmethod", "manual");
    model.result("pg2").feature("tblp2")
         .setIndex("legends", "\u6d53\u5ea6\uff0c\u8868\u9762\uff0cR<sub>p</sub>=R<sub>p,max</sub>", 0);
    model.result("pg2").feature("tblp2")
         .setIndex("legends", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3\uff0cR<sub>p</sub>=R<sub>p,max</sub>", 1);
    model.result("pg2").feature("tblp2")
         .setIndex("legends", "\u6d53\u5ea6\uff0c\u8868\u9762\uff0cR<sub>p</sub>=R<sub>p,min</sub>", 2);
    model.result("pg2").feature("tblp2")
         .setIndex("legends", "\u6d53\u5ea6\uff0c\u4e2d\u5fc3\uff0cR<sub>p</sub>=R<sub>p,min</sub>", 3);
    model.result("pg2").set("window", "window3");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u4f4d\u6bd4\u8f83");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("tblp1", "pg1/tblp1");
    model.result("pg3").run();
    model.result("pg2").set("window", "window3");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("tblp2", "pg2/tblp1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").setIndex("legends", "\u65e0\u7c92\u5f84\u5206\u5e03", 0);
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").setIndex("legends", "\u6709\u7c92\u5f84\u5206\u5e03", 0);
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset3");
    model.result("pg3").feature("glob1").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").feature("glob1").setIndex("interp", "range(0,0.01,3)", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "i_app", 0);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg3").set("yseclabelactive", true);
    model.result("pg3").set("yseclabel", "\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").label("\u7814\u7a76 2 - \u6709\u7c92\u5f84\u5206\u5e03/\u89e3 - xdim");
    model.result().dataset("dset4").set("comp", "xdim1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u9694\u819c\u9644\u8fd1\u9897\u7c92\u7684\u6d53\u5ea6\u5206\u5e03");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "comp1.atxd1(L_sep+L_pos/1000,cs)");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1")
         .set("descr", "\u989d\u5916\u7ef4\u5ea6\u4e2d\u7684\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u96c6\u6d41\u4f53\u9644\u8fd1\u9897\u7c92\u7684\u6d53\u5ea6\u5206\u5e03");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "comp1.atxd1(L_sep+L_pos*0.999,cs)");
    model.result("pg5").run();

    model.study("std1").feature("time").set("probesel", "manual");
    model.study("std1").feature("time").set("probes", new String[]{"point1", "point2", "point3"});
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time")
         .set("disabledphysics", new String[]{"liion/weak1", "liion/weak2", "liion/pce2"});

    model.title("\u5177\u6709\u7c92\u5f84\u5206\u5e03\u7684\u7535\u6c60\u7535\u6781");

    model
         .description("\u7535\u6c60\u7535\u6781\u5728\u7c92\u5f84\u65b9\u9762\u5177\u6709\u8f83\u5927\u7684\u5f02\u8d28\u6027\uff0c\u6709\u65f6\u53ef\u80fd\u65e0\u6cd5\u901a\u8fc7\u4ec5\u4f7f\u7528\u4e00\u79cd\u7c92\u5f84\u7684\u5747\u8d28\u6a21\u578b\u6765\u5145\u5206\u63cf\u8ff0\u3002\n\n\u4f5c\u4e3a\u6dfb\u52a0\u201c\u9644\u52a0\u591a\u5b54\u7535\u6781\u6750\u6599\u201d\u8282\u70b9\u7684\u591a\u4e2a\u5b9e\u4f8b\u7684\u66ff\u4ee3\u65b9\u6cd5\uff0c\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u90e8\u7f72\u7528\u6237\u5b9a\u4e49\u7684\u201c\u989d\u5916\u7ef4\u5ea6\u201d\u6765\u5b9a\u4e49\u4e00\u7cfb\u5217\u7c92\u5f84\u7684\u5d4c\u5165\u9502\u7684\u56fa\u76f8\u6269\u6563\u3002\n\n\u6a21\u578b\u4e2d\u4f7f\u7528\u76f4\u65b9\u56fe\u4f5c\u4e3a\u8f93\u5165\uff0c\u4ee5\u5b9a\u4e49\u7535\u6781\u4e2d\u7684\u7c92\u5f84\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("particle_size_distribution.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
