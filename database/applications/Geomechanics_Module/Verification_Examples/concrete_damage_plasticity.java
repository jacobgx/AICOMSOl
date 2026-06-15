/*
 * concrete_damage_plasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:15 by COMSOL 6.3.0.290. */
public class concrete_damage_plasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").create("cm1", "Concrete", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("cm1")
         .set("materialModel", "coupledDamagePlasticity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Concrete");
    model.component("comp1").material("mat1").set("family", "concrete");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material("mat1").propertyGroup()
         .create("YieldStressParameters", "YieldStressParameters", "Yield_stress_parameters");
    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmaut", new String[]{"2[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmauc", new String[]{"20[MPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("FractureParameters", "FractureParameters", "Fracture_parameters");
    model.component("comp1").material("mat1").propertyGroup("FractureParameters")
         .set("Gft", new String[]{"100[J/m^2]"});

    model.component("comp1").physics("solid").create("tm1", "TestMaterial", -1);
    model.component("comp1").physics("solid").feature("tm1").label("\u5355\u8c03\u8bd5\u9a8c");
    model.component("comp1").physics("solid").feature("tm1").selection("domainSelection").set(1);
    model.component("comp1").physics("solid").feature("tm1").set("testSpecimenSize", "Userdef");
    model.component("comp1").physics("solid").feature("tm1").set("charL", "0.1[m]");
    model.component("comp1").physics("solid").feature("tm1").set("lambda1_min", "1-5e-3");
    model.component("comp1").physics("solid").feature("tm1").set("lambda1_max", "1+1e-3");
    model.component("comp1").physics("solid").feature("tm1").set("biaxialTest", true);
    model.component("comp1").physics("solid").feature("tm1").set("lambda2_min", "1-5e-3");
    model.component("comp1").physics("solid").feature("tm1").set("lambda2_max", 1);
    model.component("comp1").physics("solid").feature("tm1").set("biaxiality_ratio", 0.5);
    model.component("comp1").physics("solid").feature("tm1").set("isotropicTest", true);
    model.component("comp1").physics("solid").feature("tm1").set("lambda4_min", "1-1e-2");
    model.component("comp1").physics("solid").feature("tm1").runCommand("setupTests");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("solidtm1utpg1").run();
    model.result("solidtm1utpg1").run();
    model.result("solidtm1utpg1").run();
    model.result("solidtm1btpg1").run();
    model.result("solidtm1btpg1").feature("ptgr1").set("legend", true);
    model.result("solidtm1btpg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("solidtm1btpg1").feature("ptgr1").setIndex("legends", "\u7eb5\u5411\u5e94\u53d8", 0);
    model.result("solidtm1btpg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("solidtm1btpg1").run();
    model.result("solidtm1btpg1").feature("ptgr2").set("xdataexpr", "solid1.elogyy");
    model.result("solidtm1btpg1").feature("ptgr2").setIndex("legends", "\u6a2a\u5411\u5e94\u53d8", 0);
    model.result("solidtm1btpg1").feature().duplicate("ptgr3", "ptgr2");
    model.result("solidtm1btpg1").run();
    model.result("solidtm1btpg1").feature("ptgr3").set("xdataexpr", "solid1.elogzz");
    model.result("solidtm1btpg1").feature("ptgr3").setIndex("legends", "\u9762\u5916\u5e94\u53d8", 0);
    model.result("solidtm1btpg1").run();
    model.result("solidtm1btpg1").label("\u771f\u5b9e\u7eb5\u5411\u5e94\u529b\uff08\u53cc\u8f74\u8bd5\u9a8c\uff09");
    model.result("solidtm1btpg1").set("xlabelactive", true);
    model.result("solidtm1btpg1").set("xlabel", "\u5e94\u53d8 (1)");
    model.result("solidtm1btpg1").run();
    model.result("solidtm1itpg1").run();
    model.result("solidtm1itpg1").run();
    model.result("solidtm1itpg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 1, 1, 0);
    model.func("int1").setIndex("table", "2e-4", 1, 1);
    model.func("int1").setIndex("table", 2, 2, 0);
    model.func("int1").setIndex("table", "-1.6e-3", 2, 1);
    model.func("int1").setIndex("table", 3, 3, 0);
    model.func("int1").setIndex("table", 0, 3, 1);

    model.component("comp1").physics("solid").create("tm2", "TestMaterial", -1);
    model.component("comp1").physics("solid").feature("tm2").label("\u5faa\u73af\u8bd5\u9a8c 1");
    model.component("comp1").physics("solid").feature("tm2").selection("domainSelection").set(1);
    model.component("comp1").physics("solid").feature("tm2").set("testSpecimenSize", "Userdef");
    model.component("comp1").physics("solid").feature("tm2").set("charL", "0.1[m]");
    model.component("comp1").physics("solid").feature("tm2").set("testSetup", "userDefined");
    model.component("comp1").physics("solid").feature("tm2").set("para_max", 3);
    model.component("comp1").physics("solid").feature("tm2").set("stepN", 300);
    model.component("comp1").physics("solid").feature("tm2").set("stretchFunction1", "1+int1(para)");
    model.component("comp1").physics("solid").feature("tm2").runCommand("setupTests");

    model.result("solidtm1utpg1").run();
    model.result("solidtm2utpg1").run();
    model.result("solidtm2utpg1").feature().copy("ptgr2", "solidtm1utpg1/ptgr1");
    model.result("solidtm2utpg1").feature().copy("ptgr3", "solidtm1utpg1/ptgr2");
    model.result("solidtm2utpg1").run();
    model.result("solidtm2utpg1").feature("ptgr2").set("linestyle", "dotted");
    model.result("solidtm2utpg1").feature("ptgr2").set("linecolor", "black");
    model.result("solidtm2utpg1").run();
    model.result("solidtm2utpg1").feature("ptgr3").set("linestyle", "dotted");
    model.result("solidtm2utpg1").feature("ptgr3").set("linecolor", "black");
    model.result("solidtm2utpg1").run();
    model.result("solidtm2utpg1").feature("ptgr1").set("linewidth", 2);
    model.result("solidtm2utpg1").run();

    model.func().create("int2", "Interpolation");
    model.func("int2").setIndex("table", 0, 0, 0);
    model.func("int2").setIndex("table", 0, 0, 1);
    model.func("int2").setIndex("table", 1, 1, 0);
    model.func("int2").setIndex("table", "-1.5e-3", 1, 1);
    model.func("int2").setIndex("table", 2, 2, 0);
    model.func("int2").setIndex("table", "1e-3", 2, 1);

    model.component("comp1").physics("solid").create("tm3", "TestMaterial", -1);
    model.component("comp1").physics("solid").feature("tm3").label("\u5faa\u73af\u8bd5\u9a8c 2");
    model.component("comp1").physics("solid").feature("tm3").selection("domainSelection").set(1);
    model.component("comp1").physics("solid").feature("tm3").set("testSpecimenSize", "Userdef");
    model.component("comp1").physics("solid").feature("tm3").set("charL", "0.1[m]");
    model.component("comp1").physics("solid").feature("tm3").set("testSetup", "userDefined");
    model.component("comp1").physics("solid").feature("tm3").set("para_max", 2);
    model.component("comp1").physics("solid").feature("tm3").set("stepN", 200);
    model.component("comp1").physics("solid").feature("tm3").set("stretchFunction1", "1+int2(para)");
    model.component("comp1").physics("solid").feature("tm3").runCommand("setupTests");

    model.result("solidtm2utpg1").run();
    model.result("solidtm3utpg1").run();
    model.result("solidtm3utpg1").feature().copy("ptgr2", "solidtm2utpg1/ptgr2");
    model.result("solidtm3utpg1").feature().copy("ptgr3", "solidtm2utpg1/ptgr3");
    model.result("solidtm3utpg1").run();
    model.result("solidtm3utpg1").run();
    model.result("solidtm3utpg1").feature("ptgr1").set("linewidth", 2);
    model.result("solidtm3utpg1").run();
    model.result("solidtm3utpg1").run();
    model.result("solidtm3utpg1").set("xlabelactive", true);
    model.result("solidtm3utpg1").set("ylabelactive", true);
    model.result("solidtm3utpg1").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("solidtm3utpg1").set("xlabel", "\u5e94\u53d8 (1)");
    model.result("solidtm3utpg1").set("xlabelactive", false);
    model.result("solidtm3utpg1").set("ylabelactive", false);

    model.title("\u6df7\u51dd\u571f\u635f\u4f24-\u5851\u6027\u6750\u6599\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u6df7\u51dd\u571f\u635f\u4f24-\u5851\u6027\u8026\u5408\u6a21\u578b\u5728\u4e0d\u540c\u8f7d\u8377\u6761\u4ef6\u4e0b\u7684\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("solidtm1sol").clearSolutionData();
    model.sol("solidtm1sol1").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("solidtm2sol").clearSolutionData();
    model.sol("solidtm2sol1").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("solidtm3sol").clearSolutionData();
    model.sol("solidtm3sol1").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("concrete_damage_plasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
