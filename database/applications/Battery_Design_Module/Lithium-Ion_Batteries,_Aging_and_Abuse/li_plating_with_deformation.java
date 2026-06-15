/*
 * li_plating_with_deformation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_plating_with_deformation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Aging_and_Abuse");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "liion");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "liion");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

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
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W_cell", "50[um]", "\u8ba1\u7b97\u7684\u7535\u6c60\u5bbd\u5ea6");
    model.param().set("H_prot", "4[um]", "\u7a81\u8d77\u9ad8\u5ea6");
    model.param().set("W_prot", "4[um]", "\u7a81\u8d77\u5bbd\u5ea6");
    model.param().set("H_sep", "50[um]", "\u9694\u819c\u9ad8\u5ea6");
    model.param().set("H_pos", "100[um]", "\u6b63\u6781\u9ad8\u5ea6");
    model.param().set("rp_pos", "1[um]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("T", "293.15[K]", "\u6e29\u5ea6");
    model.param().set("i0", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("t_fwd", "0.95", "\u6b63\u5411\u7535\u6d41\u5360\u7a7a\u6bd4");
    model.param().set("t_rev", "1-t_fwd", "\u53cd\u5411\u7535\u6d41\u5360\u7a7a\u6bd4");
    model.param().set("T_cycle", "3[min]", "\u5faa\u73af\u65f6\u95f4");
    model.param().set("C_rate_avg", "1", "\u5e73\u5747\u500d\u7387");
    model.param().set("C_rate_rev", "15", "\u53cd\u5411\u500d\u7387");
    model.param().set("i_avg", "C_rate_avg*I_1C", "\u65f6\u5747\u7535\u9540\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i_rev", "-C_rate_rev*I_1C", "\u53cd\u5411\uff08\u6eb6\u89e3\uff09\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i_fwd", "(i_avg-t_rev*i_rev)/t_fwd", "\u6b63\u5411\uff08\u7535\u9540\uff09\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i_app_fwd", "i_avg", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6\uff08\u4ec5\u6b63\u5411\u5faa\u73af\uff09");
    model.param().set("sigmas_pos", "5[S/m]", "\u6b63\u6781\u7535\u5bfc\u7387");
    model.param().set("csmax_pos", "47664[mol/m^3]", "\u6b63\u6781\u6700\u5927\u6d53\u5ea6");
    model.param().set("socminpos", "0.258", "\u6b63\u6781\u6700\u5c0f SOC");
    model.param().set("socmaxpos", "0.917", "\u6b63\u6781\u6700\u5927 SOC");
    model.param().set("epss_pos", "0.4", "\u6b63\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("I_1C", "H_pos*csmax_pos*(socmaxpos-socminpos)*epss_pos/1[h]*F_const", "1C \u7535\u6d41\u5bc6\u5ea6");
    model.param().set("cs_init_pos", "socmaxpos*csmax_pos", "\u6b63\u6781\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("rho_Li", "0.53[g*cm^-3]", "\u9502\u91d1\u5c5e\u5bc6\u5ea6");
    model.param().set("M_Li", "6.94[g/mol]", "\u9502\u91d1\u5c5e\u5206\u5b50\u91cf");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H_prot", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W_prot/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W_cell/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W_cell/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H_sep", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W_cell/2", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H_sep", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W_cell/2", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W_prot/2", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 6, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_cell", "H_pos"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-W_cell/2", "H_sep"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("pol1", 1);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "H_prot/10");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("pointinsketch").set("fil1", 3, 6);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "W_prot/2");
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material("mat2").label("NMC 622, LiNi0.6Mn0.2Co0.2O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.258", "4.283"}, 
         {"0.300", "4.201"}, 
         {"0.344", "4.120"}, 
         {"0.381", "4.059"}, 
         {"0.400", "4.025"}, 
         {"0.416", "3.998"}, 
         {"0.454", "3.940"}, 
         {"0.498", "3.875"}, 
         {"0.534", "3.834"}, 
         {"0.556", "3.811"}, 
         {"0.581", "3.790"}, 
         {"0.598", "3.780"}, 
         {"0.624", "3.763"}, 
         {"0.655", "3.753"}, 
         {"0.685", "3.736"}, 
         {"0.710", "3.726"}, 
         {"0.740", "3.712"}, 
         {"0.772", "3.695"}, 
         {"0.799", "3.678"}, 
         {"0.840", "3.651"}, 
         {"0.871", "3.631"}, 
         {"0.898", "3.607"}, 
         {"0.917", "3.580"}, 
         {"1", "3.0"}, 
         {"", ""}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"2e-13[m^2/s]", "0", "0", "0", "2e-13[m^2/s]", "0", "0", "0", "2e-13[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "Chaouachi et al, \"Experimental and theoretical investigation of Li-ion battery active materials properties: Application to a graphite/Ni0.6Mn0.2Co0.2O2 system\", Electrochimica Acta 366 (2021) 137428");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "47664[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "def.Eeq(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "Chaouachi et al, \"Experimental and theoretical investigation of Li-ion battery active materials properties: Application to a graphite/Ni0.6Mn0.2Co0.2O2 system\", Electrochimica Acta 366 (2021) 137428");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "Data unavailable.");
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
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "4.25[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "3.6[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0.911", "-0.025"}, 
         {"0.879", "-0.134"}, 
         {"0.851", "-0.200"}, 
         {"0.823", "-0.287"}, 
         {"0.795", "-0.397"}, 
         {"0.764", "-0.419"}, 
         {"0.736", "-0.528"}, 
         {"0.699", "-0.616"}, 
         {"0.671", "-0.703"}, 
         {"0.641", "-0.725"}, 
         {"0.615", "-0.813"}, 
         {"0.583", "-0.878"}, 
         {"0.553", "-0.966"}, 
         {"0.525", "-1.075"}, 
         {"0.494", "-1.163"}, 
         {"0.466", "-1.250"}, 
         {"0.433", "-1.360"}, 
         {"0.405", "-1.491"}, 
         {"0.371", "-1.688"}, 
         {"0.345", "-1.973"}, 
         {"0.313", "-2.367"}, 
         {"0.282", "-2.936"}, 
         {"0.257", "-3.570"}, 
         {"0.224", "-4.161"}, 
         {"0.186", "-4.818"}});
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
         .set("Eeq", "def.Eeq(doc)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").selection().set(2);
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
    model.component("comp1").material("mat3").selection().geom("geom1", 1);
    model.component("comp1").material("mat3").selection().set(2, 6, 7, 8, 11, 12, 13);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u9502\u7535\u6781\u8868\u9762");
    model.component("comp1").selection("sel1").set(2, 6, 7, 8, 11, 12, 13);

    model.component("comp1").material("mat3").selection().named("sel1");

    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("liion").feature("pce1").selection().set(2);
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs_init_pos");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("liion").feature("es1").selection().named("sel1");
    model.component("comp1").physics("liion").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("rhos", 8960, 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("liion").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("Species", "Li", 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("rhos", "rho_Li", 0, 0);
    model.component("comp1").physics("liion").feature("es1").setIndex("Ms", "M_Li", 0, 0);
    model.component("comp1").physics("liion").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("liion").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("liion").create("ec1", "ElectrodeCurrent", 1);
    model.component("comp1").physics("liion").feature("ec1")
         .label("\u7535\u6781\u7535\u6d41\uff1a\u6b63\u5411\u5faa\u73af");
    model.component("comp1").physics("liion").feature("ec1").selection().set(5);
    model.component("comp1").physics("liion").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("liion").feature("ec1").set("Ias", "i_app_fwd");

    model.component("comp1").common("free1").selection().set(1);

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");

    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotential", 1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u6b63\u5411\u5faa\u73af");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.05/C_rate_avg,0.75/C_rate_avg)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"liion.phis0_ec1"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"liion.soc_average_pce1"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 16, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"liion.Ilx", "liion.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 16, 0);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (liion)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"liion.Ilx", "liion.Ily"});
    model.result("pg4").feature("arws1").set("arrowbase", "center");
    model.result("pg4").feature("arws1").set("color", "gray");
    model.result("pg4").feature("arws1").create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "root.comp1.liion.IlMag");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"abs(liion.itot)"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result("pg4").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 16, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phis"});
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"liion.Isx", "liion.Isy"});
    model.result("pg5").feature("arws1").set("arrowbase", "center");
    model.result("pg5").feature("arws1").set("color", "gray");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"liion.phisext"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg5").feature("line1").set("inheritplot", "surf1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 16, 0);
    model.result("pg6").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (liion)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"liion.Isx", "liion.Isy"});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("color", "gray");
    model.result("pg6").feature("arws1").create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("expr", "root.comp1.liion.IsMag");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"abs(liion.itot)"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("inheritplot", "arws1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 16, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cl"});
    model.result("pg7").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"liion.Nposx", "liion.Nposy"});
    model.result("pg7").feature("arws1").set("color", "red");
    model.result("pg7").create("arws2", "ArrowSurface");
    model.result("pg7").feature("arws2").set("expr", new String[]{"liion.Nnegx", "liion.Nnegy"});
    model.result("pg7").feature("arws2").set("color", "black");
    model.result("pg7").feature("arws2").set("inheritplot", "arws1");
    model.result("pg7").feature("arws2").set("inheritcolor", "off");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 16, 0);
    model.result("pg8").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("meshdomain", "surface");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg8").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg8").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg8").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg8").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u6781\u5f62\u72b6\uff1a\u6b63\u5411\u5faa\u73af");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u7535\u6781\u5f62\u72b6\uff1a\u6b63\u5411\u5faa\u73af");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().named("sel1");
    model.result("pg9").feature("lngr1").set("expr", "y");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").run();
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("ymin", "-5.5e-7");
    model.result("pg9").set("ymax", "1.7e-5");
    model.result("pg9").run();

    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/ev", true);
    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").create("es1", "EventSequence", -1);
    model.component("comp1").physics("ev").feature("es1").set("loop", true);
    model.component("comp1").physics("ev").feature("es1").feature("sm1").set("stateName", "state_fwd");
    model.component("comp1").physics("ev").feature("es1").feature("sm1").set("endConditionOptions", "duration");
    model.component("comp1").physics("ev").feature("es1").feature("sm1").set("duration", "T_cycle*t_fwd");
    model.component("comp1").physics("ev").feature("es1").create("sm2", "SequenceMember", -1);
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("stateName", "state_rev");
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("endConditionOptions", "duration");
    model.component("comp1").physics("ev").feature("es1").feature("sm2").set("duration", "T_cycle*t_rev");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("i_app", "state_fwd*i_fwd+state_rev*i_rev");
    model.component("comp1").variable("var1")
         .descr("i_app", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6\uff08\u6b63\u5411\u548c\u53cd\u5411\u5faa\u73af\uff09");

    model.component("comp1").physics("liion").feature().duplicate("ec2", "ec1");
    model.component("comp1").physics("liion").feature("ec2")
         .label("\u7535\u6781\u7535\u6d41\uff1a\u6b63\u5411\u548c\u53cd\u5411\u5faa\u73af");
    model.component("comp1").physics("liion").feature("ec2").set("Ias", "i_app");

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
    model.study("std2").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std2").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/desdg1", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u6b63\u5411\u548c\u53cd\u5411\u5faa\u73af");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "W_cell", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "W_cell", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "t_fwd", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.95 0.9 0.85", 0);
    model.study("std2").feature("cdi").set("useadvanceddisable", true);
    model.study("std2").feature("cdi").set("disabledphysics", new String[]{"liion/ec1"});
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "range(0,0.05/C_rate_avg,0.75/C_rate_avg)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"liion/ec1"});
    model.study("std2").feature("time").set("autoremesh", true);
    model.study("std1").feature("cdi").set("useadvanceddisable", true);
    model.study("std1").feature("cdi").set("disabledphysics", new String[]{"liion/ec2"});
    model.study("std1").feature("time").setSolveFor("/physics/ev", false);
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"liion/ec2"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol5").study("std2");
    model.sol("sol3").feature("t1").feature("arDef").set("tadapsol", "sol5");
    model.sol().create("sol6");
    model.sol("sol6").study("std2");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u7535\u6781\u5f62\u72b6\uff1a\u6b63\u5411\u548c\u53cd\u5411\u5faa\u73af");
    model.result("pg10").set("data", "dset6");
    model.result("pg10").setIndex("looplevelinput", "manual", 1);
    model.result("pg10").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg10").set("title", "\u7535\u6781\u5f62\u72b6\uff1at_fwd = eval(t_fwd)");
    model.result("pg10").setIndex("looplevelinput", "interp", 0);
    model.result("pg10").setIndex("interp", "range(0,0.05,0.75)", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7535\u6781\u5f62\u72b6\u6bd4\u8f83");
    model.result("pg11").set("data", "none");
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "\u7535\u6781\u5f62\u72b6\u6bd4\u8f83");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").set("data", "dset1");
    model.result("pg11").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg11").feature("lngr1").selection().named("sel1");
    model.result("pg11").feature("lngr1").set("expr", "y");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "x");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg11").feature("lngr1").set("legendpattern", "t_fwd = 1");
    model.result("pg11").feature().duplicate("lngr2", "lngr1");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").set("data", "dset6");
    model.result("pg11").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg11").feature("lngr2").set("legendpattern", "t_fwd = eval(t_fwd)");
    model.result("pg11").run();
    model.result("pg11").set("axislimits", true);
    model.result("pg11").set("ymin", "-5.5e-7");
    model.result("pg11").set("ymax", "1.7e-5");
    model.result("pg11").set("legendpos", "lowerright");
    model.result("pg11").run();

    model.title("\u6790\u9502\u53d8\u5f62");

    model
         .description("\u672c\u6559\u7a0b\u5bf9\u9502\u79bb\u5b50\u7535\u6c60\u4e2d\u7684\u6790\u9502\u548c\u679d\u6676\u751f\u957f\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u91c7\u7528\u53cd\u5411\u8109\u51b2\u6c89\u79ef\u6765\u51cf\u5c0f\u91d1\u5c5e\u9502\u6c89\u79ef\u8fc7\u7a0b\u4e2d\u7684\u5c0f\u7a81\u8d77\u3002\u901a\u8fc7\u5339\u914d\u5de5\u827a\u53c2\u6570\uff0c\u5305\u62ec\u6b63\u5411\u548c\u53cd\u5411\u8109\u51b2\u7684\u957f\u5ea6\uff08\u5360\u7a7a\u6bd4\uff09\uff0c\u53ef\u4ee5\u907f\u514d\u4ea7\u751f\u5c16\u9510\u7684\u9502\u679d\u6676\u3002");

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

    model.label("li_plating_with_deformation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
