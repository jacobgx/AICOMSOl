/*
 * plasmonic_wire_grating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class plasmonic_wire_grating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.param().set("na", "1");
    model.param().descr("na", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("nb", "1.2");
    model.param().descr("nb", "\u6298\u5c04\u7387\uff0c\u7535\u4ecb\u8d28");
    model.param().set("d", "400[nm]");
    model.param().descr("d", "\u5149\u6805\u5e38\u6570");
    model.param().set("lam0", "441[nm]");
    model.param().descr("lam0", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("f0", "c_const/lam0");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("alpha", "0[deg]");
    model.param().descr("alpha", "\u5165\u5c04\u89d2");
    model.param().set("beta", "asin(na*sin(alpha)/nb)");
    model.param().descr("beta", "\u6298\u5c04\u89d2");

    model.study("std1").feature("wave").set("plist", "lam0");
    model.study("std1").feature("wave").set("useparam", true);
    model.study("std1").feature("wave").setIndex("pname_aux", "na", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "na", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "alpha", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "range(0[rad],pi/40[rad],(pi/2-pi/40)[rad])", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "deg", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d", "3*d"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d", "3*d"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-3*d"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "d/5");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"d/2", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 6);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"na"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nb"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3")
         .label("Au (Gold) (Rakic et al. 1998: Brendel-Bormann model; n,k 0.248-6.20 um)");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"2.4797e-01", "1.4943e+00"}, 
         {"2.5201e-01", "1.5158e+00"}, 
         {"2.5612e-01", "1.5382e+00"}, 
         {"2.6030e-01", "1.5611e+00"}, 
         {"2.6454e-01", "1.5844e+00"}, 
         {"2.6886e-01", "1.6078e+00"}, 
         {"2.7324e-01", "1.6306e+00"}, 
         {"2.7770e-01", "1.6527e+00"}, 
         {"2.8222e-01", "1.6733e+00"}, 
         {"2.8683e-01", "1.6921e+00"}, 
         {"2.9150e-01", "1.7085e+00"}, 
         {"2.9626e-01", "1.7220e+00"}, 
         {"3.0109e-01", "1.7322e+00"}, 
         {"3.0600e-01", "1.7387e+00"}, 
         {"3.1099e-01", "1.7411e+00"}, 
         {"3.1606e-01", "1.7391e+00"}, 
         {"3.2121e-01", "1.7327e+00"}, 
         {"3.2645e-01", "1.7219e+00"}, 
         {"3.3177e-01", "1.7071e+00"}, 
         {"3.3718e-01", "1.6887e+00"}, 
         {"3.4268e-01", "1.6677e+00"}, 
         {"3.4827e-01", "1.6452e+00"}, 
         {"3.5395e-01", "1.6224e+00"}, 
         {"3.5972e-01", "1.6006e+00"}, 
         {"3.6559e-01", "1.5810e+00"}, 
         {"3.7155e-01", "1.5645e+00"}, 
         {"3.7761e-01", "1.5515e+00"}, 
         {"3.8377e-01", "1.5419e+00"}, 
         {"3.9002e-01", "1.5351e+00"}, 
         {"3.9638e-01", "1.5301e+00"}, 
         {"4.0285e-01", "1.5255e+00"}, 
         {"4.0942e-01", "1.5196e+00"}, 
         {"4.1609e-01", "1.5107e+00"}, 
         {"4.2288e-01", "1.4971e+00"}, 
         {"4.2977e-01", "1.4771e+00"}, 
         {"4.3678e-01", "1.4493e+00"}, 
         {"4.4390e-01", "1.4126e+00"}, 
         {"4.5114e-01", "1.3661e+00"}, 
         {"4.5850e-01", "1.3095e+00"}, 
         {"4.6598e-01", "1.2427e+00"}, 
         {"4.7358e-01", "1.1664e+00"}, 
         {"4.8130e-01", "1.0821e+00"}, 
         {"4.8915e-01", "9.9182e-01"}, 
         {"4.9712e-01", "8.9849e-01"}, 
         {"5.0523e-01", "8.0543e-01"}, 
         {"5.1347e-01", "7.1590e-01"}, 
         {"5.2184e-01", "6.3260e-01"}, 
         {"5.3035e-01", "5.5731e-01"}, 
         {"5.3900e-01", "4.9085e-01"}, 
         {"5.4779e-01", "4.3326e-01"}, 
         {"5.5672e-01", "3.8405e-01"}, 
         {"5.6580e-01", "3.4242e-01"}, 
         {"5.7503e-01", "3.0751e-01"}, 
         {"5.8440e-01", "2.7843e-01"}, 
         {"5.9393e-01", "2.5437e-01"}, 
         {"6.0362e-01", "2.3457e-01"}, 
         {"6.1346e-01", "2.1841e-01"}, 
         {"6.2346e-01", "2.0533e-01"}, 
         {"6.3363e-01", "1.9487e-01"}, 
         {"6.4396e-01", "1.8664e-01"}, 
         {"6.5446e-01", "1.8030e-01"}, 
         {"6.6514e-01", "1.7558e-01"}, 
         {"6.7598e-01", "1.7227e-01"}, 
         {"6.8701e-01", "1.7016e-01"}, 
         {"6.9821e-01", "1.6911e-01"}, 
         {"7.0959e-01", "1.6897e-01"}, 
         {"7.2117e-01", "1.6966e-01"}, 
         {"7.3292e-01", "1.7107e-01"}, 
         {"7.4488e-01", "1.7313e-01"}, 
         {"7.5702e-01", "1.7577e-01"}, 
         {"7.6937e-01", "1.7895e-01"}, 
         {"7.8191e-01", "1.8262e-01"}, 
         {"7.9466e-01", "1.8675e-01"}, 
         {"8.0762e-01", "1.9129e-01"}, 
         {"8.2079e-01", "1.9621e-01"}, 
         {"8.3418e-01", "2.0151e-01"}, 
         {"8.4778e-01", "2.0715e-01"}, 
         {"8.6160e-01", "2.1311e-01"}, 
         {"8.7565e-01", "2.1938e-01"}, 
         {"8.8993e-01", "2.2594e-01"}, 
         {"9.0445e-01", "2.3278e-01"}, 
         {"9.1919e-01", "2.3989e-01"}, 
         {"9.3418e-01", "2.4725e-01"}, 
         {"9.4942e-01", "2.5486e-01"}, 
         {"9.6490e-01", "2.6271e-01"}, 
         {"9.8063e-01", "2.7079e-01"}, 
         {"9.9662e-01", "2.7909e-01"}, 
         {"1.0129e+00", "2.8761e-01"}, 
         {"1.0294e+00", "2.9634e-01"}, 
         {"1.0462e+00", "3.0528e-01"}, 
         {"1.0632e+00", "3.1442e-01"}, 
         {"1.0806e+00", "3.2376e-01"}, 
         {"1.0982e+00", "3.3329e-01"}, 
         {"1.1161e+00", "3.4302e-01"}, 
         {"1.1343e+00", "3.5293e-01"}, 
         {"1.1528e+00", "3.6304e-01"}, 
         {"1.1716e+00", "3.7334e-01"}, 
         {"1.1907e+00", "3.8382e-01"}, 
         {"1.2101e+00", "3.9449e-01"}, 
         {"1.2299e+00", "4.0535e-01"}, 
         {"1.2499e+00", "4.1641e-01"}, 
         {"1.2703e+00", "4.2765e-01"}, 
         {"1.2910e+00", "4.3909e-01"}, 
         {"1.3121e+00", "4.5072e-01"}, 
         {"1.3335e+00", "4.6256e-01"}, 
         {"1.3552e+00", "4.7459e-01"}, 
         {"1.3773e+00", "4.8684e-01"}, 
         {"1.3998e+00", "4.9930e-01"}, 
         {"1.4226e+00", "5.1198e-01"}, 
         {"1.4458e+00", "5.2488e-01"}, 
         {"1.4694e+00", "5.3801e-01"}, 
         {"1.4933e+00", "5.5138e-01"}, 
         {"1.5177e+00", "5.6499e-01"}, 
         {"1.5424e+00", "5.7885e-01"}, 
         {"1.5676e+00", "5.9297e-01"}, 
         {"1.5931e+00", "6.0736e-01"}, 
         {"1.6191e+00", "6.2203e-01"}, 
         {"1.6455e+00", "6.3699e-01"}, 
         {"1.6723e+00", "6.5224e-01"}, 
         {"1.6996e+00", "6.6780e-01"}, 
         {"1.7273e+00", "6.8368e-01"}, 
         {"1.7555e+00", "6.9989e-01"}, 
         {"1.7841e+00", "7.1645e-01"}, 
         {"1.8132e+00", "7.3336e-01"}, 
         {"1.8428e+00", "7.5065e-01"}, 
         {"1.8728e+00", "7.6831e-01"}, 
         {"1.9034e+00", "7.8638e-01"}, 
         {"1.9344e+00", "8.0486e-01"}, 
         {"1.9660e+00", "8.2377e-01"}, 
         {"1.9980e+00", "8.4313e-01"}, 
         {"2.0306e+00", "8.6294e-01"}, 
         {"2.0637e+00", "8.8324e-01"}, 
         {"2.0974e+00", "9.0404e-01"}, 
         {"2.1316e+00", "9.2535e-01"}, 
         {"2.1663e+00", "9.4720e-01"}, 
         {"2.2016e+00", "9.6961e-01"}, 
         {"2.2375e+00", "9.9259e-01"}, 
         {"2.2740e+00", "1.0162e+00"}, 
         {"2.3111e+00", "1.0404e+00"}, 
         {"2.3488e+00", "1.0652e+00"}, 
         {"2.3871e+00", "1.0907e+00"}, 
         {"2.4260e+00", "1.1169e+00"}, 
         {"2.4656e+00", "1.1438e+00"}, 
         {"2.5058e+00", "1.1715e+00"}, 
         {"2.5467e+00", "1.1999e+00"}, 
         {"2.5882e+00", "1.2291e+00"}, 
         {"2.6304e+00", "1.2592e+00"}, 
         {"2.6733e+00", "1.2901e+00"}, 
         {"2.7169e+00", "1.3219e+00"}, 
         {"2.7612e+00", "1.3545e+00"}, 
         {"2.8062e+00", "1.3882e+00"}, 
         {"2.8520e+00", "1.4228e+00"}, 
         {"2.8985e+00", "1.4584e+00"}, 
         {"2.9457e+00", "1.4951e+00"}, 
         {"2.9938e+00", "1.5328e+00"}, 
         {"3.0426e+00", "1.5717e+00"}, 
         {"3.0922e+00", "1.6117e+00"}, 
         {"3.1426e+00", "1.6529e+00"}, 
         {"3.1939e+00", "1.6954e+00"}, 
         {"3.2460e+00", "1.7391e+00"}, 
         {"3.2989e+00", "1.7842e+00"}, 
         {"3.3527e+00", "1.8306e+00"}, 
         {"3.4074e+00", "1.8784e+00"}, 
         {"3.4629e+00", "1.9277e+00"}, 
         {"3.5194e+00", "1.9785e+00"}, 
         {"3.5768e+00", "2.0308e+00"}, 
         {"3.6351e+00", "2.0847e+00"}, 
         {"3.6944e+00", "2.1403e+00"}, 
         {"3.7546e+00", "2.1976e+00"}, 
         {"3.8159e+00", "2.2566e+00"}, 
         {"3.8781e+00", "2.3175e+00"}, 
         {"3.9413e+00", "2.3802e+00"}, 
         {"4.0056e+00", "2.4449e+00"}, 
         {"4.0709e+00", "2.5116e+00"}, 
         {"4.1373e+00", "2.5803e+00"}, 
         {"4.2048e+00", "2.6511e+00"}, 
         {"4.2733e+00", "2.7242e+00"}, 
         {"4.3430e+00", "2.7995e+00"}, 
         {"4.4138e+00", "2.8771e+00"}, 
         {"4.4858e+00", "2.9572e+00"}, 
         {"4.5589e+00", "3.0397e+00"}, 
         {"4.6333e+00", "3.1247e+00"}, 
         {"4.7088e+00", "3.2124e+00"}, 
         {"4.7856e+00", "3.3028e+00"}, 
         {"4.8637e+00", "3.3960e+00"}, 
         {"4.9430e+00", "3.4921e+00"}, 
         {"5.0236e+00", "3.5911e+00"}, 
         {"5.1055e+00", "3.6932e+00"}, 
         {"5.1888e+00", "3.7985e+00"}, 
         {"5.2734e+00", "3.9069e+00"}, 
         {"5.3594e+00", "4.0187e+00"}, 
         {"5.4468e+00", "4.1340e+00"}, 
         {"5.5356e+00", "4.2528e+00"}, 
         {"5.6258e+00", "4.3752e+00"}, 
         {"5.7176e+00", "4.5013e+00"}, 
         {"5.8108e+00", "4.6314e+00"}, 
         {"5.9056e+00", "4.7653e+00"}, 
         {"6.0019e+00", "4.9034e+00"}, 
         {"6.0997e+00", "5.0456e+00"}, 
         {"6.1992e+00", "5.1922e+00"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"2.4797e-01", "1.9575e+00"}, 
         {"2.5201e-01", "1.9594e+00"}, 
         {"2.5612e-01", "1.9605e+00"}, 
         {"2.6030e-01", "1.9604e+00"}, 
         {"2.6454e-01", "1.9587e+00"}, 
         {"2.6886e-01", "1.9551e+00"}, 
         {"2.7324e-01", "1.9494e+00"}, 
         {"2.7770e-01", "1.9414e+00"}, 
         {"2.8222e-01", "1.9312e+00"}, 
         {"2.8683e-01", "1.9188e+00"}, 
         {"2.9150e-01", "1.9042e+00"}, 
         {"2.9626e-01", "1.8879e+00"}, 
         {"3.0109e-01", "1.8700e+00"}, 
         {"3.0600e-01", "1.8512e+00"}, 
         {"3.1099e-01", "1.8319e+00"}, 
         {"3.1606e-01", "1.8128e+00"}, 
         {"3.2121e-01", "1.7946e+00"}, 
         {"3.2645e-01", "1.7784e+00"}, 
         {"3.3177e-01", "1.7648e+00"}, 
         {"3.3718e-01", "1.7548e+00"}, 
         {"3.4268e-01", "1.7490e+00"}, 
         {"3.4827e-01", "1.7479e+00"}, 
         {"3.5395e-01", "1.7516e+00"}, 
         {"3.5972e-01", "1.7597e+00"}, 
         {"3.6559e-01", "1.7714e+00"}, 
         {"3.7155e-01", "1.7856e+00"}, 
         {"3.7761e-01", "1.8007e+00"}, 
         {"3.8377e-01", "1.8152e+00"}, 
         {"3.9002e-01", "1.8276e+00"}, 
         {"3.9638e-01", "1.8366e+00"}, 
         {"4.0285e-01", "1.8413e+00"}, 
         {"4.0942e-01", "1.8411e+00"}, 
         {"4.1609e-01", "1.8362e+00"}, 
         {"4.2288e-01", "1.8268e+00"}, 
         {"4.2977e-01", "1.8140e+00"}, 
         {"4.3678e-01", "1.7988e+00"}, 
         {"4.4390e-01", "1.7829e+00"}, 
         {"4.5114e-01", "1.7681e+00"}, 
         {"4.5850e-01", "1.7567e+00"}, 
         {"4.6598e-01", "1.7509e+00"}, 
         {"4.7358e-01", "1.7532e+00"}, 
         {"4.8130e-01", "1.7661e+00"}, 
         {"4.8915e-01", "1.7916e+00"}, 
         {"4.9712e-01", "1.8312e+00"}, 
         {"5.0523e-01", "1.8852e+00"}, 
         {"5.1347e-01", "1.9530e+00"}, 
         {"5.2184e-01", "2.0328e+00"}, 
         {"5.3035e-01", "2.1222e+00"}, 
         {"5.3900e-01", "2.2188e+00"}, 
         {"5.4779e-01", "2.3201e+00"}, 
         {"5.5672e-01", "2.4245e+00"}, 
         {"5.6580e-01", "2.5305e+00"}, 
         {"5.7503e-01", "2.6370e+00"}, 
         {"5.8440e-01", "2.7434e+00"}, 
         {"5.9393e-01", "2.8493e+00"}, 
         {"6.0362e-01", "2.9545e+00"}, 
         {"6.1346e-01", "3.0587e+00"}, 
         {"6.2346e-01", "3.1621e+00"}, 
         {"6.3363e-01", "3.2645e+00"}, 
         {"6.4396e-01", "3.3662e+00"}, 
         {"6.5446e-01", "3.4671e+00"}, 
         {"6.6514e-01", "3.5675e+00"}, 
         {"6.7598e-01", "3.6674e+00"}, 
         {"6.8701e-01", "3.7669e+00"}, 
         {"6.9821e-01", "3.8661e+00"}, 
         {"7.0959e-01", "3.9653e+00"}, 
         {"7.2117e-01", "4.0644e+00"}, 
         {"7.3292e-01", "4.1635e+00"}, 
         {"7.4488e-01", "4.2629e+00"}, 
         {"7.5702e-01", "4.3625e+00"}, 
         {"7.6937e-01", "4.4624e+00"}, 
         {"7.8191e-01", "4.5627e+00"}, 
         {"7.9466e-01", "4.6635e+00"}, 
         {"8.0762e-01", "4.7649e+00"}, 
         {"8.2079e-01", "4.8669e+00"}, 
         {"8.3418e-01", "4.9695e+00"}, 
         {"8.4778e-01", "5.0729e+00"}, 
         {"8.6160e-01", "5.1770e+00"}, 
         {"8.7565e-01", "5.2820e+00"}, 
         {"8.8993e-01", "5.3878e+00"}, 
         {"9.0445e-01", "5.4946e+00"}, 
         {"9.1919e-01", "5.6024e+00"}, 
         {"9.3418e-01", "5.7111e+00"}, 
         {"9.4942e-01", "5.8210e+00"}, 
         {"9.6490e-01", "5.9319e+00"}, 
         {"9.8063e-01", "6.0440e+00"}, 
         {"9.9662e-01", "6.1573e+00"}, 
         {"1.0129e+00", "6.2718e+00"}, 
         {"1.0294e+00", "6.3875e+00"}, 
         {"1.0462e+00", "6.5046e+00"}, 
         {"1.0632e+00", "6.6231e+00"}, 
         {"1.0806e+00", "6.7429e+00"}, 
         {"1.0982e+00", "6.8642e+00"}, 
         {"1.1161e+00", "6.9869e+00"}, 
         {"1.1343e+00", "7.1112e+00"}, 
         {"1.1528e+00", "7.2370e+00"}, 
         {"1.1716e+00", "7.3644e+00"}, 
         {"1.1907e+00", "7.4935e+00"}, 
         {"1.2101e+00", "7.6242e+00"}, 
         {"1.2299e+00", "7.7566e+00"}, 
         {"1.2499e+00", "7.8908e+00"}, 
         {"1.2703e+00", "8.0268e+00"}, 
         {"1.2910e+00", "8.1646e+00"}, 
         {"1.3121e+00", "8.3044e+00"}, 
         {"1.3335e+00", "8.4460e+00"}, 
         {"1.3552e+00", "8.5896e+00"}, 
         {"1.3773e+00", "8.7353e+00"}, 
         {"1.3998e+00", "8.8829e+00"}, 
         {"1.4226e+00", "9.0327e+00"}, 
         {"1.4458e+00", "9.1847e+00"}, 
         {"1.4694e+00", "9.3388e+00"}, 
         {"1.4933e+00", "9.4951e+00"}, 
         {"1.5177e+00", "9.6538e+00"}, 
         {"1.5424e+00", "9.8147e+00"}, 
         {"1.5676e+00", "9.9780e+00"}, 
         {"1.5931e+00", "1.0144e+01"}, 
         {"1.6191e+00", "1.0312e+01"}, 
         {"1.6455e+00", "1.0483e+01"}, 
         {"1.6723e+00", "1.0656e+01"}, 
         {"1.6996e+00", "1.0832e+01"}, 
         {"1.7273e+00", "1.1010e+01"}, 
         {"1.7555e+00", "1.1192e+01"}, 
         {"1.7841e+00", "1.1376e+01"}, 
         {"1.8132e+00", "1.1563e+01"}, 
         {"1.8428e+00", "1.1752e+01"}, 
         {"1.8728e+00", "1.1945e+01"}, 
         {"1.9034e+00", "1.2140e+01"}, 
         {"1.9344e+00", "1.2339e+01"}, 
         {"1.9660e+00", "1.2541e+01"}, 
         {"1.9980e+00", "1.2745e+01"}, 
         {"2.0306e+00", "1.2953e+01"}, 
         {"2.0637e+00", "1.3164e+01"}, 
         {"2.0974e+00", "1.3379e+01"}, 
         {"2.1316e+00", "1.3597e+01"}, 
         {"2.1663e+00", "1.3818e+01"}, 
         {"2.2016e+00", "1.4042e+01"}, 
         {"2.2375e+00", "1.4271e+01"}, 
         {"2.2740e+00", "1.4502e+01"}, 
         {"2.3111e+00", "1.4738e+01"}, 
         {"2.3488e+00", "1.4977e+01"}, 
         {"2.3871e+00", "1.5219e+01"}, 
         {"2.4260e+00", "1.5466e+01"}, 
         {"2.4656e+00", "1.5717e+01"}, 
         {"2.5058e+00", "1.5971e+01"}, 
         {"2.5467e+00", "1.6229e+01"}, 
         {"2.5882e+00", "1.6492e+01"}, 
         {"2.6304e+00", "1.6758e+01"}, 
         {"2.6733e+00", "1.7029e+01"}, 
         {"2.7169e+00", "1.7304e+01"}, 
         {"2.7612e+00", "1.7584e+01"}, 
         {"2.8062e+00", "1.7867e+01"}, 
         {"2.8520e+00", "1.8156e+01"}, 
         {"2.8985e+00", "1.8448e+01"}, 
         {"2.9457e+00", "1.8746e+01"}, 
         {"2.9938e+00", "1.9048e+01"}, 
         {"3.0426e+00", "1.9354e+01"}, 
         {"3.0922e+00", "1.9666e+01"}, 
         {"3.1426e+00", "1.9982e+01"}, 
         {"3.1939e+00", "2.0304e+01"}, 
         {"3.2460e+00", "2.0630e+01"}, 
         {"3.2989e+00", "2.0962e+01"}, 
         {"3.3527e+00", "2.1299e+01"}, 
         {"3.4074e+00", "2.1640e+01"}, 
         {"3.4629e+00", "2.1988e+01"}, 
         {"3.5194e+00", "2.2340e+01"}, 
         {"3.5768e+00", "2.2699e+01"}, 
         {"3.6351e+00", "2.3062e+01"}, 
         {"3.6944e+00", "2.3432e+01"}, 
         {"3.7546e+00", "2.3807e+01"}, 
         {"3.8159e+00", "2.4188e+01"}, 
         {"3.8781e+00", "2.4575e+01"}, 
         {"3.9413e+00", "2.4967e+01"}, 
         {"4.0056e+00", "2.5366e+01"}, 
         {"4.0709e+00", "2.5771e+01"}, 
         {"4.1373e+00", "2.6182e+01"}, 
         {"4.2048e+00", "2.6600e+01"}, 
         {"4.2733e+00", "2.7023e+01"}, 
         {"4.3430e+00", "2.7454e+01"}, 
         {"4.4138e+00", "2.7890e+01"}, 
         {"4.4858e+00", "2.8334e+01"}, 
         {"4.5589e+00", "2.8784e+01"}, 
         {"4.6333e+00", "2.9241e+01"}, 
         {"4.7088e+00", "2.9705e+01"}, 
         {"4.7856e+00", "3.0176e+01"}, 
         {"4.8637e+00", "3.0653e+01"}, 
         {"4.9430e+00", "3.1139e+01"}, 
         {"5.0236e+00", "3.1631e+01"}, 
         {"5.1055e+00", "3.2130e+01"}, 
         {"5.1888e+00", "3.2637e+01"}, 
         {"5.2734e+00", "3.3152e+01"}, 
         {"5.3594e+00", "3.3674e+01"}, 
         {"5.4468e+00", "3.4204e+01"}, 
         {"5.5356e+00", "3.4741e+01"}, 
         {"5.6258e+00", "3.5287e+01"}, 
         {"5.7176e+00", "3.5840e+01"}, 
         {"5.8108e+00", "3.6401e+01"}, 
         {"5.9056e+00", "3.6971e+01"}, 
         {"6.0019e+00", "3.7548e+01"}, 
         {"6.0997e+00", "3.8134e+01"}, 
         {"6.1992e+00", "3.8728e+01"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat3").selection().set(3);

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 2);
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Rorder_n1_op", "ewfd.Rorder_p1_op", "ewfd.Rtotal", "ewfd.Torder_0", "ewfd.Torder_n1_op", "ewfd.Torder_p1_op", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u53cd\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5916", "\u53cd\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5916", "\u603b\u53cd\u5c04\u7387", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5916", "\u603b\u900f\u5c04\u7387", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg2").feature("glob1").set("xdataunit", "deg");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"ewfd.kIncx_1", "ewfd.kIncy_1"});
    model.result("pg1").feature("arwl1").set("descr", "\u5165\u5c04\u6ce2\u77e2");
    model.result("pg1").feature("arwl1").set("color", "magenta");
    model.result("pg1").feature("arwl1").set("descractive", true);
    model.result("pg1").feature("arwl1").set("descr", "\u5165\u5c04\u6ce2\uff08\u6d0b\u7ea2\u8272\uff09");
    model.result("pg1").feature("arwl1").set("arrowcount", 10);
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", "1e-14");
    model.result("pg1").feature("arwl1").create("sel1", "Selection");
    model.result("pg1").feature("arwl1").feature("sel1").selection().set(5);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arwl2", "arwl1");
    model.result("pg1").run();
    model.result("pg1").feature("arwl2").set("expr", new String[]{"ewfd.kModex_1", "ewfd.kModey_1"});
    model.result("pg1").feature("arwl2").set("color", "blue");
    model.result("pg1").feature("arwl2").set("descr", "\u53cd\u5c04\u6ce2\uff08\u84dd\u8272\uff09");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arwl3", "arwl1");
    model.result("pg1").run();
    model.result("pg1").feature("arwl3").set("expr", new String[]{"ewfd.kModex_2", "ewfd.kModey_2"});
    model.result("pg1").feature("arwl3").set("color", "blue");
    model.result("pg1").feature("arwl3").set("descr", "\u900f\u5c04\u6ce2\uff08\u84dd\u8272\uff09");
    model.result("pg1").run();
    model.result("pg1").feature("arwl3").feature("sel1").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arwl4", "arwl1");
    model.result("pg1").run();
    model.result("pg1").feature("arwl4").set("expr", new String[]{"ewfd.kModex_3", "ewfd.kModey_3"});
    model.result("pg1").feature("arwl4").set("color", "cyan");
    model.result("pg1").feature("arwl4")
         .set("descr", "\u53cd\u5c04\u6a21\u5f0f\uff0cm = -1\uff08\u9752\u8272\uff09");
    model.result("pg1").feature().duplicate("arwl5", "arwl4");
    model.result("pg1").run();
    model.result("pg1").feature("arwl5").set("expr", new String[]{"ewfd.kModex_4", "ewfd.kModey_4"});
    model.result("pg1").feature("arwl5").set("color", "green");
    model.result("pg1").feature("arwl5").set("descr", "\u53cd\u5c04\u6a21\u5f0f\uff0cm = 1\uff08\u7eff\u8272\uff09");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arwl6", "arwl3");
    model.result("pg1").run();
    model.result("pg1").feature("arwl6").setIndex("expr", "ewfd.kModex_5", 0);
    model.result("pg1").feature("arwl6").setIndex("expr", "ewfd.kModey_5", 1);
    model.result("pg1").feature("arwl6")
         .set("descr", "\u900f\u5c04\u6a21\u5f0f\uff0cm = -1\uff08\u9752\u8272\uff09");
    model.result("pg1").feature("arwl6").set("color", "cyan");
    model.result("pg1").feature().duplicate("arwl7", "arwl6");
    model.result("pg1").run();
    model.result("pg1").feature("arwl7").setIndex("expr", "ewfd.kModex_6", 0);
    model.result("pg1").feature("arwl7").setIndex("expr", "ewfd.kModey_6", 1);
    model.result("pg1").feature("arwl7").set("descr", "\u900f\u5c04\u6a21\u5f0f\uff0cm = 1\uff08\u7eff\u8272\uff09");
    model.result("pg1").feature("arwl7").set("color", "green");
    model.result("pg1").run();
    model.result("pg1").label("TE \u7535\u573a");
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("TE \u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "TE \u6ce2\u7684\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>0</sub>", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>-1</sub>", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>1</sub>", 2);
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>tot</sub>", 3);
    model.result("pg2").feature("glob1").setIndex("legends", "T<sub>0</sub>", 4);
    model.result("pg2").feature("glob1").setIndex("legends", "T<sub>-1</sub>", 5);
    model.result("pg2").feature("glob1").setIndex("legends", "T<sub>1</sub>", 6);
    model.result("pg2").feature("glob1").setIndex("legends", "T<sub>tot</sub>", 7);
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>tot</sub>+T<sub>tot</sub>", 8);
    model.result("pg2").feature("glob1").setIndex("legends", "A", 9);
    model.result("pg2").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", false);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("wave").set("plist", "lam0");
    model.study("std2").feature("wave").set("useparam", true);
    model.study("std2").feature("wave").setIndex("pname_aux", "na", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std2").feature("wave").setIndex("pname_aux", "na", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std2").feature("wave").setIndex("pname_aux", "alpha", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "range(0[rad],pi/40[rad],(pi/2-pi/40)[rad])", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "deg", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ewfd)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 20, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", "", ""});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Rorder_n1_ip", "ewfd.Rorder_p1_ip", "ewfd.Rtotal", "ewfd.Torder_0", "ewfd.Torder_n1_ip", "ewfd.Torder_p1_ip", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u53cd\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5185", "\u53cd\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5185", "\u603b\u53cd\u5c04\u7387", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5185", "\u603b\u900f\u5c04\u7387", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg4").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg4").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg4").feature("glob1").set("xdataunit", "deg");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg3").run();
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"ewfd.kIncx_1", "ewfd.kIncy_1"});
    model.result("pg3").feature("arwl1").set("descr", "\u5165\u5c04\u6ce2\u77e2");
    model.result("pg3").feature("arwl1").set("color", "magenta");
    model.result("pg3").feature("arwl1").set("descractive", true);
    model.result("pg3").feature("arwl1").set("descr", "\u5165\u5c04\u6ce2\uff08\u6d0b\u7ea2\u8272\uff09");
    model.result("pg3").feature("arwl1").set("arrowcount", 10);
    model.result("pg3").feature("arwl1").set("scaleactive", true);
    model.result("pg3").feature("arwl1").set("scale", "1e-14");
    model.result("pg3").feature("arwl1").create("sel1", "Selection");
    model.result("pg3").feature("arwl1").feature("sel1").selection().set(5);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arwl2", "arwl1");
    model.result("pg3").run();
    model.result("pg3").feature("arwl2").set("expr", new String[]{"ewfd.kModex_1", "ewfd.kModey_1"});
    model.result("pg3").feature("arwl2").set("color", "blue");
    model.result("pg3").feature("arwl2").set("descr", "\u53cd\u5c04\u6ce2\uff08\u84dd\u8272\uff09");
    model.result("pg3").feature().duplicate("arwl3", "arwl2");
    model.result("pg3").run();
    model.result("pg3").feature("arwl3").set("expr", new String[]{"ewfd.kModex_2", "ewfd.kModey_2"});
    model.result("pg3").feature("arwl3").set("descr", "\u900f\u5c04\u6ce2\uff08\u84dd\u8272\uff09");
    model.result("pg3").run();
    model.result("pg3").feature("arwl3").feature("sel1").selection().set(2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arwl4", "arwl1");
    model.result("pg3").run();
    model.result("pg3").feature("arwl4").set("expr", new String[]{"ewfd.kModex_3", "ewfd.kModey_3"});
    model.result("pg3").feature("arwl4").set("color", "cyan");
    model.result("pg3").feature("arwl4")
         .set("descr", "\u53cd\u5c04\u6a21\u5f0f\uff0cm = -1\uff08\u9752\u8272\uff09");
    model.result("pg3").feature().duplicate("arwl5", "arwl4");
    model.result("pg3").run();
    model.result("pg3").feature("arwl5").set("expr", new String[]{"ewfd.kModex_4", "ewfd.kModey_4"});
    model.result("pg3").feature("arwl5").set("color", "green");
    model.result("pg3").feature("arwl5").set("descr", "\u53cd\u5c04\u6a21\u5f0f\uff0cm = 1\uff08\u7eff\u8272\uff09");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arwl6", "arwl3");
    model.result("pg3").run();
    model.result("pg3").feature("arwl6").setIndex("expr", "ewfd.kModex_5", 0);
    model.result("pg3").feature("arwl6").setIndex("expr", "ewfd.kModey_5", 1);
    model.result("pg3").feature("arwl6")
         .set("descr", "\u900f\u5c04\u6a21\u5f0f\uff0cm = -1\uff08\u9752\u8272\uff09");
    model.result("pg3").feature("arwl6").set("color", "cyan");
    model.result("pg3").feature().duplicate("arwl7", "arwl6");
    model.result("pg3").run();
    model.result("pg3").feature("arwl7").setIndex("expr", "ewfd.kModex_6", 0);
    model.result("pg3").feature("arwl7").setIndex("expr", "ewfd.kModey_6", 1);
    model.result("pg3").feature("arwl7").set("descr", "\u900f\u5c04\u6a21\u5f0f\uff0cm = 1\uff08\u7eff\u8272\uff09");
    model.result("pg3").feature("arwl7").set("color", "green");
    model.result("pg3").run();
    model.result("pg3").label("TM \u7535\u573a");
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("TM \u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "TM \u6ce2\u7684\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "R<sub>0</sub>", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "R<sub>-1</sub>", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "R<sub>1</sub>", 2);
    model.result("pg4").feature("glob1").setIndex("legends", "R<sub>tot</sub>", 3);
    model.result("pg4").feature("glob1").setIndex("legends", "T<sub>0</sub>", 4);
    model.result("pg4").feature("glob1").setIndex("legends", "T<sub>-1</sub>", 5);
    model.result("pg4").feature("glob1").setIndex("legends", "T<sub>1</sub>", 6);
    model.result("pg4").feature("glob1").setIndex("legends", "T<sub>tot</sub>", 7);
    model.result("pg4").feature("glob1").setIndex("legends", "R<sub>tot</sub>+T<sub>tot</sub>", 8);
    model.result("pg4").feature("glob1").setIndex("legends", "A", 9);
    model.result("pg4").run();

    model.title("\u8868\u9762\u7b49\u79bb\u6fc0\u5143\u7ebf\u5149\u6805");

    model
         .description("\u5e73\u9762\u6ce2\u5165\u5c04\u5230\u4ecb\u7535\u57fa\u677f\u7684\u7ebf\u5149\u6805\u4e0a\u3002\u672c\u4f8b\u8ba1\u7b97\u900f\u5c04\u548c\u53cd\u5c04\u7684\u96f6\u7ea7\u548c\u4e00\u7ea7\u884d\u5c04\u7684\u884d\u5c04\u6548\u7387\u968f\u5165\u5c04\u89d2\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("plasmonic_wire_grating.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
