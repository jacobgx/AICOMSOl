/*
 * cu_trench_deposition_ls.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:59 by COMSOL 6.3.0.290. */
public class cu_trench_deposition_ls {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cCu");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cCu", "cSO4"});
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ls", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1.6e-5", "3e-5"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-0.8e-5", "1e-5"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.4e-5", "1e-5"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-0.2e-5", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 3, 4, 5, 6);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "1e-6");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Cinit", "500[mol/(m^3)]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("T0", "298[K]", "\u7cfb\u7edf\u6e29\u5ea6");
    model.param().set("i0", "150[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_rel", "0[V]", "\u76f8\u5bf9\u5e73\u8861\u7535\u4f4d");
    model.param().set("phis_anode", "0.135[V]", "\u9633\u6781\u7535\u4f4d");
    model.param().set("phis_cathode", "-0.135[V]", "\u9634\u6781\u7535\u4f4d");
    model.param().set("alpha_c", "0.5[1]", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("alpha_a", "1.5[1]", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("z_net", "2[1]", "\u7269\u8d28\u51c0\u7535\u8377");
    model.param().set("z_Cu", "z_net[1]", "\u7535\u8377\uff0c\u7269\u8d28 Cu");
    model.param().set("z_SO4", "-z_net[1]", "\u7535\u8377\uff0c\u7269\u8d28 SO4");
    model.param().set("D_Cu", "2e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 Cu");
    model.param().set("D_SO4", "D_Cu", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 SO4");
    model.param().set("MCu", "0.06355[kg/mol]", "\u94dc\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("rhoCu", "8960[kg/m^3]", "\u94dc\u7684\u5bc6\u5ea6");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("epsl", "max(min(phils,1),if(y<3e-5[m],1e-9,1))", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("Vn", "-tcd.iloc_per1*MCu/2/F_const/rhoCu", "\u6c89\u79ef\u901f\u5ea6");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Cu", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_SO4", 1);
    model.component("comp1").physics("tcd").create("hcpce1", "HighlyConductivePorousElectrode", 2);
    model.component("comp1").physics("tcd").feature("hcpce1").selection().all();
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCu", new String[]{"D_Cu", "0", "0", "0", "D_Cu", "0", "0", "0", "D_Cu"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cSO4", new String[]{"D_SO4", "0", "0", "0", "D_SO4", "0", "0", "0", "D_SO4"});
    model.component("comp1").physics("tcd").feature("hcpce1").set("epsl", "epsl");
    model.component("comp1").physics("tcd").feature("hcpce1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("tcd").feature("hcpce1").set("fDl", "epsl");
    model.component("comp1").physics("tcd").feature("hcpce1").set("phisext0", "phis_cathode");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("Eeq", "Eeq_rel");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1")
         .set("ElectrodeKinetics", "ConcentrationDependentKinetics");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("i0", "i0");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("alphaa", "alpha_a");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("alphac", "alpha_c");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("CO", "cCu/Cinit");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("Av", "ls.delta");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(3);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "phis_anode");
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "cdep_anode", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "Cinit", 1);
    model.component("comp1").physics("ls").prop("ShapeProperty").set("order_levelsetvariable", 2);
    model.component("comp1").physics("ls").feature("lsm1").set("gamma", "max(Vn,eps)");
    model.component("comp1").physics("ls").feature("lsm1").set("epsilon_ls", "ls.hmax/4");
    model.component("comp1").physics("ls").feature("lsm1")
         .set("u", new String[]{"Vn*ls.intnormx", "Vn*ls.intnormy", "0"});
    model.component("comp1").physics("ls").feature("init1").set("FluidInDomain", "Fluid2phils");
    model.component("comp1").physics("ls").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("ls").feature("inl1").selection().set(2, 4, 5, 6, 7, 9, 10, 11, 12);
    model.component("comp1").physics("ls").create("out1", "Outlet", 1);
    model.component("comp1").physics("ls").feature("out1").selection().set(1, 3, 8);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 5);
    model.component("comp1").mesh("mesh1").create("ref1", "Refine");
    model.component("comp1").mesh("mesh1").feature("ref1").set("boxcoord", true);
    model.component("comp1").mesh("mesh1").feature("ref1").set("xmin", "-1E-5");
    model.component("comp1").mesh("mesh1").feature("ref1").set("xmax", "1E-5");
    model.component("comp1").mesh("mesh1").feature("ref1").set("ymin", "-8E-7");
    model.component("comp1").mesh("mesh1").feature("ref1").set("ymax", "2E-5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.5,20)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").set("scalemethod", "init");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("E");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "phils>0.5");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "phils");
    model.result("pg1").feature("surf2").set("colortable", "GrayScale");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("filt1").set("expr", "phils<0.5");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6 (tcd)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "cCu");
    model.result("pg2").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCu");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 29, 0);
    model.result("pg2").run();

    model.title("\u4f7f\u7528\u6c34\u5e73\u96c6\u65b9\u6cd5\u6a21\u62df\u6c9f\u69fd\u4e2d\u9540\u94dc");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u4f7f\u7528\u201c\u6c34\u5e73\u96c6\u201d\u63a5\u53e3\u6a21\u62df\u9634\u6781\u8868\u9762\u53d1\u751f\u7684\u7535\u9540\u94dc\u3002\u8be5\u6a21\u578b\u6355\u83b7\u7531\u4e8e\u6c89\u79ef\u901f\u7387\u4e0d\u5747\u5300\u800c\u5f62\u6210\u7684\u7a7a\u8154\uff0c\u7531\u4e8e\u8003\u8651\u4e86\u6d53\u5ea6\u76f8\u5173\u7684\u7535\u6781\u52a8\u529b\u5b66\u4ee5\u53ca\u6269\u6563\u548c\u8fc1\u79fb\u5f15\u8d77\u7684\u79bb\u5b50\u8fc1\u79fb\uff0c\u56e0\u6b64\u8fd9\u662f\u4e00\u4e2a\u4e09\u6b21\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\u6a21\u578b\u3002\u5176\u4e2d\u5c06\u6c89\u79ef\u8fb9\u754c\u4e0a\u7684\u7535\u6781\u52a8\u529b\u5b66\u5b9a\u4e49\u4e3a\u4e00\u4e2a\u4f7f\u7528\u201c\u6c34\u5e73\u96c6\u201d\u63a5\u53e3\u4e2d\u7684 \u03b4 \u51fd\u6570\u7684\u57df\u9879\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cu_trench_deposition_ls.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
