/*
 * cu_electroless_deposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:57 by COMSOL 6.3.0.290. */
public class cu_electroless_deposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cCuOH2L2");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cCuOH2L2", "cHCHO", "cOH", "cL"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("i0_Cu", "3.4e-5 [A/cm^2]", "\u94dc\u8fd8\u539f\u53cd\u5e94\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_HCHO", "1.4e-4 [A/cm^2]", "\u7532\u919b\u6c27\u5316\u53cd\u5e94\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("Eeq0_Cu", "-0.47[V]", "\u94dc\u8fd8\u539f\u53cd\u5e94\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("Eeq0_HCHO", "-1.0 [V]", "\u7532\u919b\u6c27\u5316\u53cd\u5e94\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("Aa_HCHO", "-(Eeq0_HCHO+0.65[V])/(log10(1.9e-3[A/cm^2]/i0_HCHO))", "\u7532\u919b\u6c27\u5316\u53cd\u5e94\u7684 Tafel \u659c\u7387");
    model.param()
         .set("Ac_Cu", "-(Eeq0_Cu+0.65[V])/(log10(1.9e-3[A/cm^2]/i0_Cu))", "\u94dc\u8fd8\u539f\u53cd\u5e94\u7684 Tafel \u659c\u7387");
    model.param().set("alphac_Cu", "-log(10)*R_const*T/F_const/Ac_Cu", "\u9634\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("DCuOH2L2", "0.7e-9[m^2/s]", "CuOH2L2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DHCHO", "1.2e-9[m^2/s]", "HCHO \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DOH", "5.273e-9[m^2/s]", "OH \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DL", "0.794e-9[m^2/s]", "L \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("cCuOH2L20", "0.1[M]", "CuOH2L2 \u7684\u6d53\u5ea6");
    model.param().set("cHCHO0", "0.05[M]", "HCHO \u7684\u6d53\u5ea6");
    model.param().set("cOH0", "10^-(14-12.5)[M]", "OH \u7684\u6d53\u5ea6");
    model.param().set("cL0", "0.075[M]", "L \u7684\u6d53\u5ea6");
    model.param().set("MW_Cu", "0.06355[kg/mol]", "\u94dc\u7684\u5206\u5b50\u8d28\u91cf");
    model.param().set("rho_Cu", "8960[kg/m^3]", "\u94dc\u7684\u5bc6\u5ea6");
    model.param().set("T", "25[degC]", "\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "2[mm]", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCuOH2L2", new String[]{"DCuOH2L2", "0", "0", "0", "DCuOH2L2", "0", "0", "0", "DCuOH2L2"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cHCHO", new String[]{"DHCHO", "0", "0", "0", "DHCHO", "0", "0", "0", "DHCHO"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cOH", new String[]{"DOH", "0", "0", "0", "DOH", "0", "0", "0", "DOH"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cL", new String[]{"DL", "0", "0", "0", "DL", "0", "0", "0", "DL"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCuOH2L20", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cHCHO0", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cOH0", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cL0", 3);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cCuOH2L20", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cHCHO0", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cOH0", 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cL0", 3);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "MW_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "TotalCurrent");
    model.component("comp1").physics("tcd").feature("es1").set("Itl", 0);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0init", "-0.65[V]");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", "-1/2", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", "1/2", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq0_Cu");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "cCuOH2L20", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "cOH0", 2, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "cL0", 3, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_Cu");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "1-alphac_Cu");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", 1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", 2, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq", "Eeq0_HCHO");
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("i0", "i0_HCHO*(cOH/cOH0)^2*(cHCHO/cHCHO0)");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Aa", "Aa_HCHO");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "2E-7");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,10,3600)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6df7\u5408\u7535\u4f4d");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").feature("ptgr1").set("expr", "tcd.phisext");
    model.result("pg1").feature("ptgr1").set("descractive", true);
    model.result("pg1").feature("ptgr1").set("descr", "\u6df7\u5408\u7535\u4f4d");
    model.result("pg1").feature("ptgr1").set("titletype", "manual");
    model.result("pg1").feature("ptgr1").set("title", "\u70b9\u7ed3\u679c\u56fe\uff1a\u6df7\u5408\u7535\u4f4d");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6c89\u79ef\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("expr", "abs(tcd.iloc_er1)");
    model.result("pg2").feature("ptgr1").set("descr", "\u6c89\u79ef\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr1")
         .set("title", "\u70b9\u7ed3\u679c\u56fe\uff1a\u6c89\u79ef\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u6c89\u79ef\u539a\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("expr", "tcd.sbtot");
    model.result("pg3").feature("ptgr1").set("unit", "\u00b5m");
    model.result("pg3").feature("ptgr1").set("descr", "\u6c89\u79ef\u539a\u5ea6");
    model.result("pg3").feature("ptgr1").set("title", "\u70b9\u7ed3\u679c\u56fe\uff1a\u6c89\u79ef\u539a\u5ea6");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("\u5f52\u4e00\u5316\u6d53\u5ea6");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5f52\u4e00\u5316\u6d53\u5ea6");
    model.result("pg4").set("ylog", true);
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "cCuOH2L2/cCuOH2L20");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "Cu(OH)<sub>2</sub>L<sub>2</sub>", 0);
    model.result("pg4").feature("ptgr1")
         .set("title", "\u70b9\u7ed3\u679c\u56fe\uff1a\u5f52\u4e00\u5316\u6d53\u5ea6");
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("expr", "cHCHO/cHCHO0");
    model.result("pg4").feature("ptgr2").setIndex("legends", "HCHO", 0);
    model.result("pg4").feature("ptgr2").set("titletype", "none");
    model.result("pg4").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("expr", "cL/cL0");
    model.result("pg4").feature("ptgr3").setIndex("legends", "L", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerleft");

    model.title("\u94dc\u7684\u65e0\u7535\u6c89\u79ef");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u6df7\u5408\u7535\u4f4d\u7406\u8bba\u6765\u6a21\u62df\u94dc\u7684\u77ac\u6001\u65e0\u7535\u6c89\u79ef\u8fc7\u7a0b\u3002\u6a21\u578b\u4e2d\u901a\u8fc7\u6269\u6563\u548c\u7535\u6781\u8868\u9762\u7684\u7535\u5316\u5b66\u53cd\u5e94\u6765\u8bf4\u660e\u8d28\u91cf\u4f20\u9012\u3002\u90e8\u5206\u7535\u5316\u5b66\u53cd\u5e94\u7684\u5e73\u8861\u7535\u4f4d\u4e0e\u6d53\u5ea6\u76f8\u5173\uff0c\u5e76\u901a\u8fc7\u5c06\u603b\u7535\u6d41\u8bbe\u4e3a 0 \u6765\u8ba1\u7b97\u6df7\u5408\u7535\u4f4d\u3002\n\n\u8be5\u6a21\u578b\u8ba1\u7b97\u7535\u6d41\u5bc6\u5ea6\u3001\u6c89\u79ef\u539a\u5ea6\u3001\u79bb\u5b50\u7269\u8d28\u7684\u6d53\u5ea6\u5728\u65e0\u7535\u6c89\u79ef\u8fc7\u7a0b\u4e2d\u968f\u65f6\u95f4\u7684\u53d8\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("cu_electroless_deposition.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
