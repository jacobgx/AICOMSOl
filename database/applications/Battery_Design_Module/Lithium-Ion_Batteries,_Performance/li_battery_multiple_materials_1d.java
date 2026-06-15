/*
 * li_battery_multiple_materials_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_multiple_materials_1d {

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
    model.param().set("rp_pos_NCA", "2.5e-6[m]", "NCA \u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("rp_pos_LMO", "1.7e-6[m]", "LMO \u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("rp_neg", "2.5e-6[m]", "\u8d1f\u6781\u6d3b\u6027\u6750\u6599\u7684\u9897\u7c92\u534a\u5f84");
    model.param()
         .set("i0ref_pos_NCA", "7.32[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cNCA \u6b63\u6781");
    model.param()
         .set("i0ref_pos_LMO", "17.44[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cLMO \u6b63\u6781");
    model.param()
         .set("i0ref_neg", "0.96[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6d3b\u6027\u6750\u6599\u8d1f\u6781");
    model.param().set("brugl_pos", "2.98", "\u6b63\u6781\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("brugl_sep", "3.15", "\u9694\u819c\u7684\u8fc2\u66f2\u5ea6 Bruggeman \u7cfb\u6570");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param().set("L_neg", "50[um]", "\u8d1f\u6781\u957f\u5ea6");
    model.param().set("L_sep", "50e-6[m]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("sigmas_neg", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "91[S/m]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("epsl_sep", "0.37", "\u9694\u819c\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_neg", "0.444", "\u8d1f\u6781\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_pos", "0.40", "\u6b63\u6781\u7684\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_filler", "0.17", "\u586b\u6599\u7535\u6781\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epss_pos", "(1-epsl_pos-eps_filler)", "\u6b63\u6781\u6d3b\u6027\u6750\u6599\u6df7\u5408\u7269\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_neg", "(1-epsl_neg-eps_filler)", "\u8d1f\u6781\u4f53\u79ef\u5206\u6570");
    model.param().set("fr_pos_NCA", "0.33", "NCA/LMO \u6df7\u5408\u7269\u4e2d NCA \u7684\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epss_pos_NCA", "epss_pos*fr_pos_NCA", "NCA \u6b63\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epss_pos_LMO", "epss_pos*(1-fr_pos_NCA)", "LMO \u6b63\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("SOC_cell0", "1", "\u521d\u59cb\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.param().set("E_100_SOC", "4.1[V]", "100% SOC \u65f6\u7684\u7535\u6c60\u7535\u538b");
    model.param().set("E_0_SOC", "3.1[V]", "0% SOC \u65f6\u7684\u7535\u6c60\u7535\u538b");
    model.param().set("csmax_neg", "31507[mol/m^3]", "\u77f3\u58a8\u627f\u8f7d\u5bb9\u91cf");
    model.param().set("csmax_NCA", "48000[mol/m^3]", "NCA \u627f\u8f7d\u5bb9\u91cf");
    model.param().set("csmax_LMO", "22860[mol/m^3]", "LMO \u627f\u8f7d\u5bb9\u91cf");
    model.param()
         .set("L_pos", "0.8*epss_neg*csmax_neg/((1-0.3)*epss_pos_NCA*csmax_NCA+(1-0.19)*epss_pos_LMO*csmax_LMO)*L_neg", "\u6b63\u6781\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat1").label("LiPF6 in 1:1 EC:DEC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("importedname", "DL_ECDEC.txt");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("funcnametable", new String[][]{{"DL_int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "DL_int1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c2/1[mol/m^3],T2/1[K])", "0", "0", "0", "DL_int1(c2/1[mol/m^3],T2/1[K])", "0", "0", "0", "DL_int1(c2/1[mol/m^3],T2/1[K])"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochem. Soc., vol. 154, p. A1146, 2007\n");
    model.component("comp1").material("mat1").propertyGroup("def").set("c2", "min(1500,max(c,500))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("c2", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T2", "min(313.15,max(T,283.15))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"1.147[mS/cm]*exp(520[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^3-22.38[mS/cm]*exp(1010[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^1.5+29.15[mS/cm]*exp(1270[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])", "0", "0", "0", "1.147[mS/cm]*exp(520[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^3-22.38[mS/cm]*exp(1010[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^1.5+29.15[mS/cm]*exp(1270[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])", "0", "0", "0", "1.147[mS/cm]*exp(520[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^3-22.38[mS/cm]*exp(1010[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])^1.5+29.15[mS/cm]*exp(1270[J/mol]/R_const*(1/298[K]-1/T3))*(c3/1000[mol/m^3])"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochem. Soc., vol. 154, p. A1146, 2007\n");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("c3", "min(1500,max(c,eps))");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("c3", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(313.15,max(T,283.15))");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("importedname", "transpNm_ECDEC.txt");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("funcnametable", new String[][]{{"transpNm_int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "transpNm_int1"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("importedname", "actdep_ECDEC.txt");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("funcnametable", new String[][]{{"actdep_int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2").set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("funcnames", new String[]{"col1", "int2", "col2", "int2", "col3", "actdep_int1"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("int2")
         .set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c4/1[mol/m^3],T4/1[K])");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochem. Soc., vol. 154, p. A1146, 2007\n");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c4/1[mol/m^3],T4/1[K])");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochem. Soc., vol. 154, p. A1146, 2007\n");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").set("c4", "min(1500,max(c,500))");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").descr("c4", "");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("T4", "min(313.15,max(T,283.15))");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1000[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteSaltConcentration")
         .setPropertyInfo("cElsalt", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochem. Soc., vol. 154, p. A1146, 2007\n");
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat4").label("LMO, LiMn2O4 Spinel (Positive, Li-ion Battery)");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
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
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2")
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
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "194[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.26");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrochemical and Electronic Charge Transport Properties of Ni-Doped LiMn2O4 Spinel Obtained from Polyol-Mediated Synthesis, Shuo Yang et al, Materials 2018, 11(5), 806");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "4140[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat4").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("csmax", "22860[mol/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat4").propertyGroup("OperationalSOC").set("E_min", "3.9[V]");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat4").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").feature("sep1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("fDl", "epsl_sep^brugl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(1);
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1")
         .set("ParticleConcentrationType", "BakerVerbrugge");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Distribution", "Linear");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Nel", 20);
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat2");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().set(3);
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos_NCA");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fl", "epsl_pos^brugl_pos");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("liion").feature("pce2").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fDl", "epsl_pos^brugl_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos_NCA");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1")
         .set("ParticleConcentrationType", "BakerVerbrugge");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0ref_pos_NCA");
    model.component("comp1").physics("liion").create("addm1", "IntercalatingMaterial", 1);
    model.component("comp1").physics("liion").feature("addm1").selection().set(3);
    model.component("comp1").physics("liion").feature("addm1").set("epssadd", "epss_pos_LMO");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("ParticleMaterial", "mat4");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1").set("rp", "rp_pos_LMO");
    model.component("comp1").physics("liion").feature("addm1").feature("pin1")
         .set("ParticleConcentrationType", "BakerVerbrugge");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").set("MaterialOption", "mat4");
    model.component("comp1").physics("liion").feature("addm1").feature("per1").set("i0_ref", "i0ref_pos_LMO");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", "SOC_cell0");
    model.component("comp1").physics("liion").feature("socicd1").set("CellVoltageInputType", "userdef");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_0SOC", "E_0_SOC");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_100SOC", "E_100_SOC");
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().set(1);
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().set(3);
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp1").physics("liion").feature("ec1").selection().set(4);
    model.component("comp1").physics("liion").feature("ec1").set("TotalCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("ec1").set("Crate", -1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "EndTerminal");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.study("std1").feature("time").set("tlist", "0 3600");
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
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.EndTerminal(comp1.phis)<3.0", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6052\u5b9a 1C \u7535\u6d41\u653e\u7535");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "phis");
    model.result("pg1").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u538b (V)");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 3600);
    model.result("pg1").set("ymin", "3.0");
    model.result("pg1").set("ymax", 4.2);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8868\u9762\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "liion.cs_surface");
    model.result("pg2").feature("ptgr1").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u8868\u9762");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "NCA", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "liion.cs_surface_addm1");
    model.result("pg2").feature("ptgr2")
         .set("descr", "\u5d4c\u5165\u9897\u7c92\u8868\u9762\u6d53\u5ea6\uff0c\u9644\u52a0\u591a\u5b54\u7535\u6781\u6750\u6599 1");
    model.result("pg2").feature("ptgr2").setIndex("legends", "LMO", 0);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u8868\u9762\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 3600);
    model.result("pg2").set("ymin", 4000);
    model.result("pg2").run();
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").label("\u7814\u7a76 1/\u89e3 1: xdim \u8d1f\u6781");
    model.result().dataset("dset3").set("comp", "liion_pce1_pin1_xdim");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u9897\u7c92\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "range(0,200,2000) range(2030,30,2200) range(2300,200,3300)", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "atxd1(25e-6,liion.cs_pce1)");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u5f52\u4e00\u5316\u9897\u7c92\u5c3a\u5bf8");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u9897\u7c92\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").run();

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
    model.study("std2").feature("time").set("tlist", "0 3600");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "rp_pos_NCA", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "rp_pos_NCA", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "fr_pos_NCA", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.05 0.25 0.55 0.75", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tout", "tsteps");
    model.sol("sol3").feature("t1").set("tstepsstore", 3);
    model.sol("sol3").feature("t1").create("st1", "StopCondition");
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.EndTerminal(comp1.phis)<3.0", 0);
    model.sol("sol3").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u538b\u66f2\u7ebf\uff08\u53c2\u6570\u5316\uff09");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("data", "dset6");
    model.result("pg4").feature("ptgr1").selection().set(4);
    model.result("pg4").feature("ptgr1").set("expr", "phis");
    model.result("pg4").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("ptgr1")
         .set("legendpattern", "eval(fr_pos_NCA*100)/eval((1-fr_pos_NCA)*100) NCA/LMO \u4f53\u79ef\u6bd4");
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u538b (V)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4e0d\u540c NCA/LMO \u4f53\u79ef\u6bd4\u7684\u7535\u538b\u66f2\u7ebf");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", 3600);
    model.result("pg4").set("ymin", "3.0");
    model.result("pg4").set("ymax", 4.2);
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").run();

    model.title("\u591a\u79cd\u63d2\u5c42\u7535\u6781\u6750\u6599\u7684\u9502\u79bb\u5b50\u7535\u6c60");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u63a5\u53e3\u4e2d\u7684\u201c\u989d\u5916\u63d2\u5c42\u6750\u6599\u201d\u7279\u5f81\u3002\u8be5\u6a21\u578b\u63cf\u8ff0\u8fd9\u6837\u4e00\u79cd\u9502\u79bb\u5b50\u7535\u6c60\uff1a\u6b63\u6781\u5177\u6709\u4e24\u79cd\u4e0d\u540c\u7684\u63d2\u5c42\u6750\u6599\uff0c\u800c\u8d1f\u6781\u53ea\u6709\u4e00\u79cd\u63d2\u5c42\u6750\u6599\u3002\u7814\u7a76\u4e86\u6b63\u6781\u4e2d\u4e24\u79cd\u63d2\u5c42\u6750\u6599\u5728\u4e0d\u540c\u6df7\u5408\u5206\u6570\u4e0b\u7684\u7535\u6c60\u653e\u7535\u6027\u80fd\u3002");

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

    model.label("li_battery_multiple_materials_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
