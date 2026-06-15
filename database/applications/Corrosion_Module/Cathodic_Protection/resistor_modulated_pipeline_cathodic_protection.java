/*
 * resistor_modulated_pipeline_cathodic_protection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:42 by COMSOL 6.3.0.290. */
public class resistor_modulated_pipeline_cathodic_protection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cdpipe", "CurrentDistributionPipe", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cdpipe", true);

    model.component("comp1").geom("geom1")
         .insertFile("resistor_modulated_pipeline_cathodic_protection_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_pipe", "0.5[m]", "\u7ba1\u534a\u5f84");
    model.param().set("r_pipe_smaller", "0.2[m]", "\u8f83\u5c0f\u7ba1\u534a\u5f84");
    model.param().set("sigma_l", "3[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("Eeq_Zn", "-1.03[V]", "\u9633\u6781\u5e73\u8861\u7535\u4f4d vs. SCE");
    model.param().set("R_cp", "100[ohm]", "\u8026\u5408\u7535\u963b");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1")
         .label("UNS S31254 (stainless steel) in chlorinated seawater solution (Cathodic)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"0.8", "0"}, 
         {"0.65", "-0.01"}, 
         {"0.6", "-0.03"}, 
         {"0.575", "-0.1"}, 
         {"0.1", "-0.3"}, 
         {"-0.05", "-1"}, 
         {"-0.2", "-3"}, 
         {"-0.3", "-10"}, 
         {"-0.5", "-30"}, 
         {"-0.6", "-100"}, 
         {"-0.8", "-300"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"mA/m^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "G. E. Nustad, T. Solem, R. Johnsen, H. Osvoll, P. O. Gartland, M. Brameld, and G. Clapp, \u201cResistor controlled cathodic protection for stainless steels in chlorinated seawater: A review after 8 years in service,\u201d NACE Corrosion, Paper number 03082, 2003.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c SCE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "G. E. Nustad, T. Solem, R. Johnsen, H. Osvoll, P. O. Gartland, M. Brameld, and G. Clapp, \u201cResistor controlled cathodic protection for stainless steels in chlorinated seawater: A review after 8 years in service,\u201d NACE Corrosion, Paper number 03082, 2003.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "0.8 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SCE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");

    model.component("comp1").physics("cdpipe").selection().named("geom1_unisel1");
    model.component("comp1").physics("cdpipe").prop("PhysicsVsMaterialsReferenceElectrodePotential")
         .set("PhysicsVsMaterialsReferenceElectrodePotential", "SCE");
    model.component("comp1").physics("cdpipe").feature("ice1").set("redge", "r_pipe");
    model.component("comp1").physics("cdpipe").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cdpipe").feature("ice1")
         .set("sigmal", new String[]{"sigma_l", "0", "0", "0", "sigma_l", "0", "0", "0", "sigma_l"});
    model.component("comp1").physics("cdpipe").create("ice2", "Electrolyte", 1);
    model.component("comp1").physics("cdpipe").feature("ice2").selection().named("geom1_pi3_csel1_edg");
    model.component("comp1").physics("cdpipe").feature("ice2").set("redge", "r_pipe_smaller");
    model.component("comp1").physics("cdpipe").feature("ice2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cdpipe").feature("ice2")
         .set("sigmal", new String[]{"sigma_l", "0", "0", "0", "sigma_l", "0", "0", "0", "sigma_l"});
    model.component("comp1").physics("cdpipe").create("pes1", "PipeElectrodeSurface", 1);
    model.component("comp1").physics("cdpipe").feature("pes1").selection().named("geom1_unisel1");
    model.component("comp1").physics("cdpipe").feature("pes1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cdpipe").create("ppsa1", "PipePointSacrificialAnode", 0);
    model.component("comp1").physics("cdpipe").feature("ppsa1").selection().named("geom1_sel1");
    model.component("comp1").physics("cdpipe").feature("ppsa1").set("Eeq", "Eeq_Zn");
    model.component("comp1").physics("cdpipe").feature("ppsa1").set("R", "R_cp");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "r_pipe", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "r_pipe", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R_cp", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.5 1 2", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"phil"});
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg1").feature("line1").set("tuberadiusscale", "1");
    model.result("pg1").feature("line1").set("radiusexpr", "root.comp1.cdpipe.redge");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cdpipe)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"cdpipe.tIlMag"});
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"cdpipe.tIlx", "cdpipe.tIly", "cdpipe.tIlz"});
    model.result("pg2").feature("arwl1").set("arrowbase", "center");
    model.result("pg2").feature("arwl1").set("arrowtype", "cone");
    model.result("pg2").feature("arwl1").set("arrowcount", 50);
    model.result("pg2").feature("arwl1").set("color", "black");
    model.result("pg2").feature("arwl1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arwl1").create("col", "Color");
    model.result("pg2").feature("arwl1").feature("col").set("expr", "cdpipe.tIlMag");
    model.result("pg2").feature("arwl1").set("inheritplot", "line1");
    model.result("pg2").label("\u5207\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cdpipe)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cdpipe.itot"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg3").feature("line1").set("tuberadiusscale", "1");
    model.result("pg3").feature("line1").set("radiusexpr", "root.comp1.cdpipe.redge");
    model.result("pg3").label("\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6 (cdpipe)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"cdpipe.Evsref"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg4").feature("line1").set("tuberadiusscale", "1");
    model.result("pg4").feature("line1").set("radiusexpr", "root.comp1.cdpipe.redge");
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cdpipe)");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("legendactive", true);
    model.result("pg4").set("legendprecision", 4);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u4e0d\u6ee1\u8db3\u9632\u62a4\u6807\u51c6\u7684\u7ba1\u533a\u57df");
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("line1").feature("filt1").set("expr", "cdpipe.Evsref>-0.1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").run();

    model.title("\u7535\u963b\u8c03\u8282\u7ba1\u9053\u9634\u6781\u4fdd\u62a4");

    model
         .description("\u6c2f\u5316\u6d77\u6c34\u7cfb\u7edf\u4e2d\u4f7f\u7528\u7684\u9ad8\u5408\u91d1\u4e0d\u9508\u94a2\u5728\u67d0\u4e9b\u5de5\u4f5c\u6761\u4ef6\u4e0b\u53ef\u80fd\u5bb9\u6613\u8150\u8680\u3002\u4f20\u7edf\u7684\u9634\u6781\u4fdd\u62a4\u6280\u672f\u4e0d\u592a\u9002\u7528\u4e8e\u4e0d\u9508\u94a2\uff0c\u539f\u56e0\u662f\u65e0\u6cd5\u627f\u53d7\u7684\u9633\u6781\u6d88\u8017\u7387\uff0c\u5e76\u4e14\u5728\u9ad8\u8d1f\u7535\u4f4d\u4e0b\u5bb9\u6613\u53d1\u751f\u6c22\u8106\u3002\n\n\u672c\u6a21\u578b\u4f7f\u7528\u201c\u7535\u6d41\u5206\u5e03\uff0c\u7ba1\u201d\u63a5\u53e3\u6f14\u793a\u57fa\u4e8e\u7535\u963b\u8c03\u8282\u9634\u6781\u4fdd\u62a4\u7684\u8150\u8680\u9632\u62a4\u66ff\u4ee3\u6280\u672f\u3002\n\n\u672c\u4f8b\u7814\u7a76\u4e0d\u540c\u7535\u963b\u503c\u5bf9\u4ea7\u751f\u7684\u8150\u8680\u9632\u62a4\u7b49\u7ea7\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("resistor_modulated_pipeline_cathodic_protection.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
